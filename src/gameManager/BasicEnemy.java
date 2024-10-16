package gameManager;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class BasicEnemy extends GameObject implements Enemy {
    private Rectangle rectangle;
    private double speed;
    private Player player; // Referência ao jogador

    public BasicEnemy(int x, int y, Player player, double speed) {
        super(x, y, 50, 50);
        this.player = player;
        this.speed = speed;

        // Criação do inimigo
        rectangle = new Rectangle(x, y, 50, 50);
        rectangle.setColor(Color.RED);
        rectangle.fill();  // Desenha o inimigo
    }

    @Override
    public void update() {
        // Lógica de movimento do inimigo
        int dx = (int) (speed * Math.signum(player.getX() - getX()));
        int dy = (int) (speed * Math.signum(player.getY() - getY()));
        translate(dx, dy);
    }

    @Override
    public void translate(int dx, int dy) {
        rectangle.translate(dx, dy); // Move a imagem do inimigo
        // Atualiza a posição do GameObject
        setX(getX() + dx);
        setY(getY() + dy);
    }

    @Override
    public int getX() {
        return super.getX();  // Usa o método da superclasse (GameObject)
    }

    @Override
    public int getY() {
        return super.getY();  // Usa o método da superclasse (GameObject)
    }

    @Override
    public int getWidth() {
        return rectangle.getWidth();  // Retorna a largura do retângulo
    }

    @Override
    public int getHeight() {
        return rectangle.getHeight();  // Retorna a altura do retângulo
    }
}
