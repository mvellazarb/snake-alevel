package com.gmail.mvellazarb.snake.state;

import javax.swing.JPanel;

public interface State {
/*  Possible states:
    TITLE,
    NEW_GAME,
    LOAD_GAME,
    HIGH_SCORES,
    RUNNING,
    PAUSED,
    GAME_OVER
*/
    public JPanel getStatePanel();
    public void gameLoop();
}