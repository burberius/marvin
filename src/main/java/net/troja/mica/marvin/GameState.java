package net.troja.mica.marvin;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private int currentCame;
    private int maxGames;
    private int currentRound;
    private int maxRound;
    private int players;
    private int sizeX;
    private int sizeY;
    private char[][] field;
    private PlayerState myState;
    private PlayerState fox;
    private boolean iamFox;
    private final List<PlayerState> enemies = new ArrayList<PlayerState>();

    public int getCurrentCame() {
        return currentCame;
    }

    public void setCurrentCame(int currentCame) {
        this.currentCame = currentCame;
    }

    public int getMaxGames() {
        return maxGames;
    }

    public void setMaxGames(int maxGames) {
        this.maxGames = maxGames;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public int getMaxRound() {
        return maxRound;
    }

    public void setMaxRound(int maxRound) {
        this.maxRound = maxRound;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public char[][] getField() {
        return field;
    }

    public void setField(char[][] field) {
        this.field = field;
    }

    public PlayerState getMyState() {
        return myState;
    }

    public void setMyState(PlayerState myState) {
        this.myState = myState;
    }

    public PlayerState getFox() {
        return fox;
    }

    public void setFox(PlayerState fox) {
        this.fox = fox;
    }

    public boolean isIamFox() {
        return iamFox;
    }

    public void setIamFox(boolean iamFox) {
        this.iamFox = iamFox;
    }

    public List<PlayerState> getEnemies() {
        return enemies;
    }

    public void addEnemy(PlayerState enemy) {
        enemies.add(enemy);
    }

    public void clearEnemies() {
        enemies.clear();
    }

    @Override
    public String toString() {
        return "GameState [currentCame=" + currentCame + ", maxGames=" + maxGames + ", currentRound=" + currentRound + ", maxRound=" + maxRound + ", players=" + players
                + ", sizeX=" + sizeX + ", sizeY=" + sizeY + ", myState=" + myState + ", fox=" + fox + ", iamFox=" + iamFox + ", enemies=" + enemies + "]\n" + fieldToString();
    }

    private String fieldToString() {
        final StringBuilder build = new StringBuilder();
        for (int row = 0; row < field.length; row++) {
            build.append(field[row]).append("\n");
        }
        return build.toString();
    }
}
