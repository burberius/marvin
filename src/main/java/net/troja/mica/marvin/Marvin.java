package net.troja.mica.marvin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Marvin {
    public final static String MY_NAME = "Marvin";
    private final GameStateParser fieldParser = new GameStateParser();
    private final PathFinder pathFinder = new PathFinder();

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
                final GameState gameState = fieldParser.parse(in);
                if (gameState.isIamFox()) {
                    char closestDirection = 0;
                    int closestDistance = Integer.MAX_VALUE;
                    for (final PlayerState enemyState : gameState.getEnemies()) {
                        final char[] path = pathFinder.calculatePath(gameState.getMyState(), enemyState, gameState.getField());
                        if (path.length < closestDistance) {
                            closestDirection = path[0];
                            closestDistance = path.length;
                        }
                    }
                    final Location myLocation = new Location(gameState.getMyState().getPosX(), gameState.getMyState().getPosY());
                    for (final char direction : getOtherDirections(closestDirection)) {
                        final Location newLocation = PathFinder.getNextLocation(myLocation, direction);
                        System.out.println(myLocation + " direction: " + direction + " " + newLocation);
                        if (!PathFinder.isWall(gameState.getField(), newLocation.getX(), newLocation.getY())) {
                            out.println(direction);
                            break;
                        }
                    }
                } else {
                    final char[] path = pathFinder.calculatePath(gameState.getMyState(), gameState.getFox(), gameState.getField());
                    out.println(path[0]);
                }
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

    private char[] getOtherDirections(char direction) {
        switch (direction) {
        case 'a':
            return "dws".toCharArray();
        case 'd':
            return "asw".toCharArray();
        case 'w':
            return "sad".toCharArray();
        case 's':
            return "wda".toCharArray();
        }
        return null;
    }

    public static void main(String... args) {
        new Marvin();
    }
}
