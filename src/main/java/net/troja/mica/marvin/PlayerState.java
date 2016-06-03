package net.troja.mica.marvin;

public class PlayerState {
    private String name;
    private int score;
    private int posX;
    private int posY;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public String toString() {
        return "PlayerState [name=" + name + ", score=" + score + ", posX=" + posX + ", posY=" + posY + "]";
    }
}
