package gameManager;

public interface Enemy {
    void update();   // Atualiza o movimento e a lógica do inimigo
    void translate(int dx, int dy);  // Método para movimentar o inimigo

    // Métodos que serão usados para checar a colisão
    int getX();
    int getY();
    int getWidth();
    int getHeight();
}
