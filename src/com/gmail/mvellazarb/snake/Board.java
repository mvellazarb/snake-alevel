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

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import java.util.Random;

public class Board extends JPanel {
    private final int TILE_SIZE = 15;
    private final int TILES = 25;
    private final int WIDTH = TILE_SIZE * TILES;
    private final int HEIGHT = TILE_SIZE * TILES;
    private boolean isRunning = true;

    private Random random = new Random();

    private int score;
    private Point fruit = new Point(random.nextInt(25), random.nextInt(25));
    private Snake snake = new Snake();
    public Direction requestedDirection = snake.getDirection();


    public Board() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(new Controls());
        setFocusable(true);
        requestFocus();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // super paintComponent knows how to handle setBackground

        // drawGrid(g);
        drawSnake(g);
        drawFruit(g);
        
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
    // TODO snake eyes (seven, eleven!)
    private void drawSnake(Graphics g) {
        g.setColor(Color.GREEN);
        for (Point segment : snake.getSegments())
            g.fillRect(segment.x * TILE_SIZE, segment.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    private void drawFruit(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(fruit.x * TILE_SIZE, fruit.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    // TODO must not spawn fruit on top of a snake segment
    private void spawnFruit() {
        fruit = new Point(random.nextInt(25), random.nextInt(25));
    }
    
    private void checkCollision() {
        for (int i = 0; i < snake.getSegments().size() - 1; i++) {
            Point segment = snake.getSegments().get(i);
            if (snake.getHead().equals(segment)) {
                isRunning = false;
            }
        }
    }

    private void checkFruitCollision() {
        if (snake.getHead().equals(fruit)) { // if the head's point and the fruit's point match up
            score += 10;
            snake.getSegments().add(0, new Point(snake.getSegments().get(0)));
            spawnFruit();
        }
    }

    private void update() {
        move();
        checkCollision();
        checkFruitCollision();
        repaint();
    }
    
    private void move() {
        Point head = new Point(snake.getHead());  // make a local 'copy', don't want a reference

        if (requestedDirection.getOppositeDirection() != snake.getDirection())
            snake.setDirection(requestedDirection);

        switch (snake.getDirection()) {
            case UP:
                head.y--;
                if (head.y < 0)
                    head.y = TILES - 1; // wrap around to bottom of the board
                break;
            case DOWN:
                head.y++;
                if (head.y > TILES - 1)
                    head.y = 0;
                break;
            case LEFT:
                head.x--;
                if (head.x < 0)
                    head.x = TILES - 1;
                break;
            case RIGHT:
                head.x++;
                if (head.x > TILES - 1)
                    head.x = 0;
                break;
        }

        snake.getSegments().add(head);
        snake.getSegments().remove(0);
    }

    public void gameLoop() {
        while (isRunning) {
            update();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("GAME OVER");  // TODO: proper menu
    }

    private class Controls extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    requestedDirection = Direction.UP;
                    break;
                case KeyEvent.VK_RIGHT:
                    requestedDirection = Direction.RIGHT;
                    break;
                case KeyEvent.VK_DOWN:
                    requestedDirection = Direction.DOWN;
                    break;
                case KeyEvent.VK_LEFT:
                    requestedDirection = Direction.LEFT;
                    break;
            }
        }
    }
}
