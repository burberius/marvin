package net.troja.mica.marvin;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldParser {
    private final GameState gameState = new GameState();

    public FieldParser() {

    }

    public void parse(BufferedReader in) throws IOException {
        getInfo(in);
        getField(in);
        getScoreTable(in);
        System.out.println(gameState);
    }

    private void getInfo(BufferedReader in) throws IOException {
        String line;
        do {
            line = in.readLine();
        } while (!line.startsWith("game:"));
        final String[] split = line.split(",");

        Pattern pattern = Pattern.compile("game:(\\d*)/(\\d*)");
        Matcher matcher = pattern.matcher(split[0]);
        if (matcher.matches()) {
            gameState.setCurrentCame(Integer.parseInt(matcher.group(1)));
            gameState.setMaxGames(Integer.parseInt(matcher.group(2)));
        }

        pattern = Pattern.compile("round:(\\d*)/(\\d*)");
        matcher = pattern.matcher(split[1]);
        if (matcher.matches()) {
            gameState.setCurrentRound(Integer.parseInt(matcher.group(1)));
            gameState.setMaxRound(Integer.parseInt(matcher.group(2)));
        }

        pattern = Pattern.compile("players:(\\d*)");
        matcher = pattern.matcher(split[2]);
        if (matcher.matches()) {
            gameState.setPlayers(Integer.parseInt(matcher.group(1)));
        }

        pattern = Pattern.compile("mapsize:x(\\d*)y(\\d*)");
        matcher = pattern.matcher(split[3]);
        if (matcher.matches()) {
            gameState.setSizeX(Integer.parseInt(matcher.group(1)));
            gameState.setSizeY(Integer.parseInt(matcher.group(2)));
        }
    }

    private void getField(BufferedReader in) throws IOException {
        final char[][] field = new char[gameState.getSizeY()][gameState.getSizeX()];
        int y = 0;
        String line;
        do {
            line = in.readLine();
        } while (!line.startsWith("map:"));
        do {
            line = in.readLine();
            if (line.length() > gameState.getSizeX()) {
                final String[] split = line.split("\\|");
                for (int x = 0; x < gameState.getSizeX(); x++) {
                    char pos = split[x].charAt(0);
                    if (pos == '_') {
                        pos = ' ';
                    } else if (pos == 'W') {
                        pos = '#';
                    }
                    field[y][x] = pos;
                }
                y++;
            }
        } while (line.length() > gameState.getSizeX());
        gameState.setField(field);
    }

    private void getScoreTable(BufferedReader in) throws IOException {
        gameState.clearEnemies();
        String line;
        do {
            line = in.readLine();
        } while (!line.startsWith("scoretable:"));
        do {
            line = in.readLine();
            if (line.startsWith("name:")) {
                final PlayerState playerState = parsePlayerState(line);
                final boolean isFox = gameState.getField()[playerState.getPosY()][playerState.getPosX()] == 'f';
                if (playerState.getName().equals(Marvin.MY_NAME)) {
                    gameState.setMyState(playerState);
                    gameState.setIamFox(isFox);
                } else {
                    if (isFox) {
                        gameState.setFox(playerState);
                    } else {
                        gameState.addEnemy(playerState);
                    }
                }
            } else {
                break;
            }
        } while (true);
    }

    private PlayerState parsePlayerState(String line) {
        final PlayerState state = new PlayerState();
        final String[] split = line.split(",");

        Pattern pattern = Pattern.compile("name:(.*)");
        Matcher matcher = pattern.matcher(split[0]);
        if (matcher.matches()) {
            state.setName(matcher.group(1));
        }

        pattern = Pattern.compile("score:(\\d*)");
        matcher = pattern.matcher(split[1]);
        if (matcher.matches()) {
            state.setScore(Integer.parseInt(matcher.group(1)));
        }

        pattern = Pattern.compile("x:(\\d*)");
        matcher = pattern.matcher(split[2]);
        if (matcher.matches()) {
            state.setPosX(Integer.parseInt(matcher.group(1)));
        }

        pattern = Pattern.compile("y:(\\d*);");
        matcher = pattern.matcher(split[3]);
        if (matcher.matches()) {
            state.setPosY(Integer.parseInt(matcher.group(1)));
        }

        return state;
    }
}
