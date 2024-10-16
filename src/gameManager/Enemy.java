package gameManager;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

// Classe Enemy que representa os inimigos
public class Enemy extends GameObject {
    private Rectangle rectangle;
    private double speed;
    private Player player; // Referência ao jogador

    public Enemy(int x, int y, Player player, double speed) {
        super(x, y, 50, 50);
        this.player = player; // Inicializa a referência ao jogador
        this.speed = speed;

        // Criação do inimigo
        rectangle = new Rectangle(x, y, 50, 50);
        rectangle.setColor(Color.RED);
        rectangle.fill();  // Desenha o inimigo
    }

    public void update() {
        // Lógica de movimento do inimigo (exemplo simples)
        int dx = (int) (speed * Math.signum(player.getX() - getX()));
        int dy = (int) (speed * Math.signum(player.getY() - getY()));
        translate(dx, dy);
    }

    private void translate(int dx, int dy) {
        rectangle.translate(dx, dy); // Move a imagem do inimigo
        // Atualiza a posição do GameObject
        setX(getX() + dx);
        setY(getY() + dy);
    }
}
