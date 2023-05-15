
package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements ActionListener {

    private static final int BOARD_WIDTH = 40;
    private static final int BOARD_HEIGHT = 40;
    private static final int CELL_SIZE = 10;
    private static final int TIMER_DELAY = 100;

    private Snake cobra;
    private Fruta fruta;
    private Timer tempo;
    private int pontos;
    private int direcao;


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

    public void actionPerformed(ActionEvent e) {
        cobra.move();
        if (cobra.getHead().equals(fruta.getPosition())) {
            cobra.grow();
            pontos +=10;
            fruta.respawn(BOARD_WIDTH, BOARD_HEIGHT);
        }
        checkGameOver();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawSnake(g);
        drawFruit(g);
    }

    private void drawBoard(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, BOARD_WIDTH * CELL_SIZE, BOARD_HEIGHT * CELL_SIZE);
        g.setColor(Color.DARK_GRAY);
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void drawSnake(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for (Point p : cobra.getBody()) {
            g.fillRect(p.x * CELL_SIZE, p.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(cobra.getHead().x * CELL_SIZE, cobra.getHead().y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    private void drawFruit(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(fruta.getPosition().x * CELL_SIZE, fruta.getPosition().y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    private class SnakeControl extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    cobra.setDirection(0);
                    break;
                case KeyEvent.VK_RIGHT:
                    cobra.setDirection(1);
                    break;
                case KeyEvent.VK_DOWN:
                    cobra.setDirection(2);
                    break;
                case KeyEvent.VK_LEFT:
                    cobra.setDirection(3);
                    break;
                case KeyEvent.VK_ENTER:
                    if(tempo.isRunning())
                        tempo.stop();
                    else
                        tempo.start();
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
                    
            }
        }
    }

    private void checkGameOver() {
        Point head = cobra.getHead();
        if (head.x < 0 || head.x >= BOARD_WIDTH || head.y < 0 || head.y >= BOARD_HEIGHT) {
            gameOver();
        } else {
            for (int i = 1; i < cobra.getBody().size(); i++) {
                if (head.equals(cobra.getBody().get(i))) {
                    gameOver();
                    break;
                }
            }
        }

        if (cobra.getDirection() == 0 && direcao == 2
                || cobra.getDirection() == 2 && direcao == 0
                || cobra.getDirection() == 3 && direcao == 1
                || cobra.getDirection() == 1 && direcao == 3) {
            int temp = direcao;
            direcao = cobra.getDirection();
            cobra.setDirection(temp);
        }
    }

    private void gameOver() {
        tempo.stop();
        JOptionPane.showMessageDialog(this, "Game Over! \nPontuação: " + pontos);
        System.exit(0);
    }

}
