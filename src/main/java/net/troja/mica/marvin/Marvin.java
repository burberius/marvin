package net.troja.mica.marvin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Marvin {
    public final static String MY_NAME = "Marvin";
    private final FieldParser fieldParser = new FieldParser();

    public Marvin() {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            echoSocket = new Socket("localhost", 5000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

            out.println("name:" + MY_NAME);
            while (true) {
                fieldParser.parse(in);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (echoSocket != null) {
                    echoSocket.close();
                }
                in.close();
                out.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String... args) {
        new Marvin();
    }
}
