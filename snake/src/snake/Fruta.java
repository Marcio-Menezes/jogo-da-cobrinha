/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.awt.Point;

public class Fruta {

    private Point posicao;

    public Fruta(int boardWidth, int boardHeight) {
        this.posicao = getPosicaoRandom(boardWidth, boardHeight);
    }

    public Point getPosicao() {
        return posicao;
    }

    public void setPosicao(Point posicao) {
        this.posicao = posicao;
    }

    public void respawn(int boardWidth, int boardHeight) {
        posicao = getPosicaoRandom(boardWidth, boardHeight);
    }

    private Point getPosicaoRandom(int boardWidth, int boardHeight) {
        int x = (int) (Math.random() * boardWidth);
        int y = (int) (Math.random() * boardHeight);
        return new Point(x, y);
    }

}
