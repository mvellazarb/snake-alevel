package com.gmail.mvellazarb.snake;

import javax.swing.JFrame;

public class Window extends JFrame{
    public Window() {
        setTitle("Snake");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Board board = new Board();
        add(board);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        board.gameLoop();
    }

    public static void main(String[] args) {
        new Window();
    }
}
