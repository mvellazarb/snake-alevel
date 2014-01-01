package com.gmail.mvellazarb.snake.state;

import javax.swing.JFrame;

public class StateManager {
    public static void main(String[] args) {
        State state = new GameState(); // for now

        JFrame window = new JFrame();

        window.setTitle("Snake");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.add(state.getStatePanel());

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        state.gameLoop();
    }
}
