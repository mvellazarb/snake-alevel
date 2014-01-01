/* TODO
 * menu (play, select speed, high scores, quit)
 * pause
 * select speed
 * score display
 * game over screen, play again?
 * high scores saved to disk
 */

/* TODO BUGS
 * collision stuff probably not efficient
 * (maybe Tile extends Point class with isOccupied?)
 * (something like that)
 * game loop could be way better
 * fruit still spawning on snake segments
 * (use collision for this too)
 * maybe I should have some buffer so that if the player presses two keys in quick succession,
 * faster than the movement of the snake, it still registers both key inputs
 */

package com.gmail.mvellazarb.snake;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public class Board extends JPanel {
    private final int TILE_SIZE = 15;
    public static final int TILES = 25;
    private final int WIDTH = TILE_SIZE * TILES;
    private final int HEIGHT = TILE_SIZE * TILES;

    private int score;
    private Point food;
    private Snake snake;

    public Board() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // super paintComponent knows how to handle setBackground

        // drawGrid(g);
        drawSnake(g);
        drawFood(g);
        
        g.setColor(Color.WHITE);
        g.drawString("SCORE: " + score, 0, 10);
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.DARK_GRAY);

        for (int i = 0; i <= getHeight(); i += TILE_SIZE) {  // draw horizontal grid lines
            g.drawLine(0, i, getWidth(), i);
        }

        for (int i = 0; i <= getWidth(); i += TILE_SIZE) {  // draw vertical grid lines
            g.drawLine(i, 0, i, getHeight());
        }
    }

    private void drawSnake(Graphics g) {
        g.setColor(Color.GREEN);
        for (Point segment : snake.getSegments())
            g.fillRect(segment.x * TILE_SIZE, segment.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    private void drawFood(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setFood(Point food) {
        this.food = food;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }
}
