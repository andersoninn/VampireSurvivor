package gameManager;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameWorld {
    private Player player;
    private List<Enemy> enemies;
    private HealthBar healthBar;
    private ScheduledExecutorService enemySpawner;
    private static final int CANVAS_WIDTH = 2000;
    private static final int CANVAS_HEIGHT = 1000;

    private Rectangle choice;
    private Text choicetext;


    public GameWorld() {
        choice = new Rectangle(200, 200, 200,200);
        choice.setColor(Color.GREEN);
        choice.draw();

        choicetext = new Text(220, 220, "Weapon1");
        choicetext.setColor(Color.GREEN);
        choicetext.grow(20,20);
        choicetext.draw();



        player = new Player(500, 500); // Centraliza o jogador no canvas
        enemies = new ArrayList<>();
        healthBar = new HealthBar(100);

        // Inicializa o agendador de spawn de inimigos
        enemySpawner = Executors.newScheduledThreadPool(1);
        startSpawningEnemies(); // Inicia o agendamento de spawn de inimigos
    }

    public void update() {
        // Use um Iterator para evitar ConcurrentModificationException
        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.update(); // Atualiza a posição do inimigo

            // Verifica se o jogador colidiu com o inimigo
            if (player.collidesWith(enemy)) {
                player.decreaseHealth(); // Reduz a saúde do jogador se houver colisão
                healthBar.update(healthBar.getCurrentHealth() - 1);
                // Aqui você pode decidir o que fazer com a colisão,
                // mas não está removendo inimigos.
            }
        }
    }

    // Método para iniciar o spawn de inimigos a cada 1 segundo
    private void startSpawningEnemies() {
        enemySpawner.scheduleAtFixedRate(() -> spawnEnemy(), 0, 1, TimeUnit.SECONDS);
    }

    // Método que cria e adiciona um inimigo aleatoriamente nas bordas
    private void spawnEnemy() {
        Random random = new Random();

        // Gera uma posição aleatória nas bordas do canvas
        int x = random.nextInt(CANVAS_WIDTH);
        int y = random.nextInt(CANVAS_HEIGHT);

        // Adiciona o inimigo à lista com velocidade fixa (ou você pode variar a velocidade)
        enemies.add(new BasicEnemy(x, y, player, 1.0));

        System.out.println("Novo inimigo criado em x=" + x + ", y=" + y);
    }

    // Método para interromper o spawn de inimigos, se necessário
    public void stopSpawningEnemies() {
        enemySpawner.shutdown();
    }
}
