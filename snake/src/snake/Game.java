package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements ActionListener {

    //Define o tamanho da tela 
    private static final int BOARD_WIDTH = 40;
    private static final int BOARD_HEIGHT = 40;
    private static final int CELL_SIZE = 10;
    // Tempo em ms
    private static final int TIMER_DELAY = 100;

    private Snake cobra;
    private Fruta fruta;
    private Timer tempo;
    private int pontos;
    private int direcao;

    // Inicializador do game, setup ao iniciar o jogo
    public Game() {
        setPreferredSize(new Dimension(BOARD_WIDTH * CELL_SIZE, BOARD_HEIGHT * CELL_SIZE));
        setFocusable(true);
        requestFocus();
        addKeyListener(new SnakeControl());

        cobra = new Snake(new Point(BOARD_WIDTH / 2, BOARD_HEIGHT / 2), 3);
        fruta = new Fruta(BOARD_WIDTH, BOARD_HEIGHT);
        direcao = 0;
        pontos = 0;
        tempo = new Timer(TIMER_DELAY, this);
        tempo.start();
    }

    // Método que verifica ações do jogo
    public void actionPerformed(ActionEvent e) {
        cobra.move();
        /* Se a cobra comer a fruta, a cobra aumenta seu tamanho em 1 pixel, 
        os pontos aumentam em 10 e a fruta respawna em outro local*/
        if (cobra.getHead().equals(fruta.getPosicao())) {
            cobra.grow();
            pontos += 10;
            fruta.respawn(BOARD_WIDTH, BOARD_HEIGHT);
        }
        // Cada nova ação no jogo é checado se algo ocorreu que cause o game over
        checaGameOver();
        // Atualiza a tela, não delete esse método
        repaint();
    }

    // Imprime os componentes do jogo, não mude o nome desse método
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenhaBoard(g);
        desenhaSnake(g);
        desenhaFruta(g);
    }

    private void desenhaBoard(Graphics g) {
        // Cor do fundo da tela, brinque mudando como quiser
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, BOARD_WIDTH * CELL_SIZE, BOARD_HEIGHT * CELL_SIZE);
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void desenhaSnake(Graphics g) {
        // Define a cor para a cobra
        g.setColor(Color.LIGHT_GRAY);
        for (Point p : cobra.getBody()) {
            g.fillRect(p.x * CELL_SIZE, p.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
        g.fillRect(cobra.getHead().x * CELL_SIZE, cobra.getHead().y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    private void desenhaFruta(Graphics g) {
        // Define a cor para a fruta
        g.setColor(Color.LIGHT_GRAY);
        // Mude fillOval para fillRect caso queira que a fruta seja um quadrado e não um circulo
        g.fillOval(fruta.getPosicao().x * CELL_SIZE, fruta.getPosicao().y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    private class SnakeControl extends KeyAdapter {

        // Metódo que identifica qual botão foi pressionado, não mude o nome desse método
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    cobra.setDirecao(0);
                    break;
                case KeyEvent.VK_RIGHT:
                    cobra.setDirecao(1);
                    break;
                case KeyEvent.VK_DOWN:
                    cobra.setDirecao(2);
                    break;
                case KeyEvent.VK_LEFT:
                    cobra.setDirecao(3);
                    break;
                case KeyEvent.VK_ENTER:
                    if (tempo.isRunning()) {
                        tempo.stop();
                    } else {
                        tempo.start();
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;

            }
        }
    }

    private void checaGameOver() {
        Point head = cobra.getHead();
        /* Se a cobra bater em um dos cantos da tela retorna gameover
        Se quiser tornar o jogo mais facil pode-se comentar o gameover do if abaixo
        e tirar o comentário do método transportarCobra(head) que faz com que 
        a cobrinha se teleporte pro outro lado da tela. */
        if (head.x < 0 || head.x >= BOARD_WIDTH || head.y < 0 || head.y >= BOARD_HEIGHT) {
            gameOver();
            //transportaCobra(head);
        } else {
            //Verifica se a cabeça tocou o corpo, se sim, game over
            for (int i = 1; i < cobra.getBody().size(); i++) {
                if (head.equals(cobra.getBody().get(i))) {
                    gameOver();
                    break;
                }
            }
        }
        // Impede que a cobrinha vá para direção oposta sem se virar
        if (cobra.getDirecao() == 0 && direcao == 2
                || cobra.getDirecao() == 2 && direcao == 0
                || cobra.getDirecao() == 3 && direcao == 1
                || cobra.getDirecao() == 1 && direcao == 3) {
            int temp = direcao;
            direcao = cobra.getDirecao();
            cobra.setDirecao(temp);
        }
    }

    private void transportaCobra(Point head) {
        // Transporta para o lado oposto
        if (head.x < 0) {
            head.x = BOARD_WIDTH - 1;
        } else if (head.x >= BOARD_WIDTH) {
            head.x = 0;  
        } else if (head.y < 0) {
            head.y = BOARD_HEIGHT - 1;  
        } else if (head.y >= BOARD_HEIGHT) {
            head.y = 0; 
        }
    }

    private void gameOver() {
        // Para o timer, mostra um pop-up de gameover junto com a pontuação do jogador
        tempo.stop();
        JOptionPane.showMessageDialog(this, "Game Over! \nPontuação: " + pontos);
        System.exit(0);
    }

}
