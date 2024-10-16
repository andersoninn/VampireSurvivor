package gameManager;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

// Classe Player que representa o jogador e suas interações
public class Player extends GameObject implements KeyboardHandler {
    private int health;
    private Keyboard keyboard;
    private Rectangle rectangle;

    public Player(int x, int y) {
        super(x, y, 50, 50);
        this.health = 100; // Saúde inicial do jogador

        // Criação do jogador
        rectangle = new Rectangle(x, y, 50, 50);
        rectangle.setColor(Color.BLUE);
        rectangle.fill();  // Desenha o jogador

        initKeyboard(); // Iniciar o teclado para o movimento do personagem
    }

    private void initKeyboard() {
        this.keyboard = new Keyboard(this);

        // Configuração dos eventos do teclado
        KeyboardEvent moveRight = new KeyboardEvent();
        moveRight.setKey(KeyboardEvent.KEY_RIGHT);
        moveRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveRight);

        KeyboardEvent moveLeft = new KeyboardEvent();
        moveLeft.setKey(KeyboardEvent.KEY_LEFT);
        moveLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveLeft);

        KeyboardEvent moveUp = new KeyboardEvent();
        moveUp.setKey(KeyboardEvent.KEY_UP);
        moveUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveUp);

        KeyboardEvent moveDown = new KeyboardEvent();
        moveDown.setKey(KeyboardEvent.KEY_DOWN);
        moveDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveDown);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        int moveAmount = 10; // Quantidade de movimento por tecla pressionada

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                translate(moveAmount, 0);  // Move o jogador para a direita
                break;
            case KeyboardEvent.KEY_LEFT:
                translate(-moveAmount, 0);  // Move o jogador para a esquerda
                break;
            case KeyboardEvent.KEY_UP:
                translate(0, -moveAmount);  // Move o jogador para cima
                break;
            case KeyboardEvent.KEY_DOWN:
                translate(0, moveAmount);  // Move o jogador para baixo
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        // Pode ser implementado se necessário
    }

    public void decreaseHealth() {
        this.health -= 10; // Decrementa a saúde em 10
        if (health <= 0) {
            // Lógica para quando o jogador morre, se necessário
            System.out.println("Player has died.");
        }
    }

    public boolean collidesWith(Enemy enemy) {
        // Checa a colisão entre o jogador e um inimigo
        return (getX() < enemy.getX() + enemy.getWidth() &&
                getX() + getWidth() > enemy.getX() &&
                getY() < enemy.getY() + enemy.getHeight() &&
                getY() + getHeight() > enemy.getY());
    }

    // Implementa métodos para mover e atualizar a posição do jogador
    private void translate(int dx, int dy) {
        rectangle.translate(dx, dy); // Move a imagem do jogador
        // Atualiza a posição do GameObject
        setX(getX() + dx);
        setY(getY() + dy);
    }
}
