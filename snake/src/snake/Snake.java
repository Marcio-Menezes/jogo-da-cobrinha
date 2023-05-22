package snake;

import java.awt.Point;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Point> corpo;
    private Point cabeca;
    private int tamanho;
    private int direcao;
    private int direcaoAtual;

    public Snake(Point cabeca, int tamanho) {
        this.cabeca = cabeca;
        this.tamanho = tamanho;
        this.direcao = 0;
        this.direcaoAtual = 0;
        this.corpo = new ArrayList<Point>();
        for (int i = 1; i <= tamanho; i++) {
            Point p = new Point(cabeca.x - i, cabeca.y);
            corpo.add(p);
        }
    }

    public Point getHead() {
        return cabeca;
    }

    public ArrayList<Point> getBody() {
        return corpo;
    }

    public int getDirecao() {
        return direcao;
    }

    public void setDirecao(int direcao) {
        // Verifica se a nova direção é oposta à direção atual
        if (this.direcao == 0 && direcao == 2 ||
                this.direcao == 1 && direcao == 3 ||
                this.direcao == 2 && direcao == 0 ||
                this.direcao == 3 && direcao == 1) {
            return;
        }
        this.direcao = direcao;
    }

    public int getDirecaoAtual() {
        return direcaoAtual;
    }

    public void setDirecaoAtual(int direcaoAtual) {
        this.direcaoAtual = direcaoAtual;
    }

    public void move() {
        // Move the snake's cabeca in the current direcao
        Point newHead = new Point(cabeca);
        switch (direcao) {
            case 0: // Up
                newHead.y -= 1;
                break;
            case 1: // Right
                newHead.x += 1;
                break;
            case 2: // Down
                newHead.y += 1;
                break;
            case 3: // Left
                newHead.x -= 1;
                break;
        }
        cabeca = newHead;

        // Move the rest of the snake's corpo
        for (int i = corpo.size() - 1; i > 0; i--) {
            Point prev = corpo.get(i - 1);
            Point curr = corpo.get(i);
            curr.x = prev.x;
            curr.y = prev.y;
        }
        Point first = corpo.get(0);
        first.x = cabeca.x;
        first.y = cabeca.y;

        // Atualiza a direção atual da cobra
        direcaoAtual = direcao;
    }

    public void grow() {
        // Add a new segment to the end of the snake's corpo
        Point last = corpo.get(corpo.size() - 1);
        Point newSegment = new Point(last);
        corpo.add(newSegment);
        tamanho++;
    }
}
