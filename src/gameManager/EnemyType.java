package gameManager;

import org.academiadecodigo.simplegraphics.graphics.Color;

public class EnemyType {
    private double speed;
    private int health;
    private Color color;

    public EnemyType(double speed, int health, Color color) {
        this.speed = speed;
        this.health = health;
        this.color = color;
    }

    public double getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public Color getColor() {
        return color;
    }
}
