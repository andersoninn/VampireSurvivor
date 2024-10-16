package gameManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameWorld {
    private Player player;
    private List<Enemy> enemies;
    private HealthBar healthBar;

    public GameWorld() {
        player = new Player(500, 500); // Centraliza o jogador no canvas
        enemies = new ArrayList<>();
        healthBar = new HealthBar(100);

        spawnEnemies(); // Chama o método para criar inimigos
    }

    public void update() {
        for (Enemy enemy : enemies) {
            enemy.update(); // Atualiza a posição do inimigo

            // Verifica se o jogador colidiu com o inimigo
            if (player.collidesWith(enemy)) {
                player.decreaseHealth(); // Reduz a saúde do jogador se houver colisão
                healthBar.update(healthBar.getCurrentHealth() -1);
            }
        }
    }

    private void spawnEnemies() {
        Random random = new Random();
        int canvasWidth = 2000;
        int canvasHeight = 1000;

        // Cria 5 inimigos nas bordas do canvas
        for (int i = 0; i < 10; i++) { // Cria 5 inimigos
            int x = random.nextInt(canvasWidth);
            int y = random.nextInt(canvasHeight);
            enemies.add(new Enemy(x, y, player, 1.0)); // Adiciona o inimigo à lista
        }
    }
}
