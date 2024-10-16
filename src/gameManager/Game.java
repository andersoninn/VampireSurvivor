package gameManager;

public class Game {
    private GameWorld gameWorld;

    public Game() {
        gameWorld = new GameWorld();
    }

    public void start() {
        while (true) {
            gameWorld.update(); // Atualiza o mundo do jogo
            // Adicione aqui um delay ou outra lógica para controlar a taxa de quadros
            try {
                Thread.sleep(16); // Aproximadamente 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
