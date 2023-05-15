/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.awt.Point;

public class Fruta {

    private Point posicao;

    public Fruta(int boardWidth, int boardHeight) {
        this.posicao = getRandomPosition(boardWidth, boardHeight);
    }

    public Point getPosition() {
        return posicao;
    }

    public void setPosition(Point posicao) {
        this.posicao = posicao;
    }

    public void respawn(int boardWidth, int boardHeight) {
        posicao = getRandomPosition(boardWidth, boardHeight);
    }

    private Point getRandomPosition(int boardWidth, int boardHeight) {
        int x = (int) (Math.random() * boardWidth);
        int y = (int) (Math.random() * boardHeight);
        return new Point(x, y);
    }

}
