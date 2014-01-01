package com.gmail.mvellazarb.snake.state;

import com.gmail.mvellazarb.snake.Board;
import com.gmail.mvellazarb.snake.Direction;
import com.gmail.mvellazarb.snake.Snake;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.awt.Point;


public class GameState implements State {
    private Random random = new Random();
    private boolean isRunning = true;
    private int score = 0;

    private Point food = new Point(random.nextInt(Board.TILES), random.nextInt(Board.TILES));
    private Snake snake = new Snake();
    private Direction requestedDirection = snake.getDirection();

    private Board board = new Board();

    private void checkCollision() {
        for (int i = 0; i < snake.getSegments().size() - 1; i++) {
            Point segment = snake.getSegments().get(i);
            if (snake.getHead().equals(segment)) {
                isRunning = false;
            }
        }
    }

    private void checkFoodCollision() {
        if (snake.getHead().equals(food)) {
            score += 10;
            snake.getSegments().add(0, new Point(snake.getSegments().get(0)));
            spawnFood();
        }
    }

    // TODO must not spawn fruit on top of snake segment
    private void spawnFood() {
        food.setLocation(random.nextInt(Board.TILES), random.nextInt(Board.TILES));
    }

    private void update() {
        move();
        checkCollision();
        checkFoodCollision();

        board.setScore(score);
        board.setFood(food);
        board.setSnake(snake);
        board.repaint();
    }

    private void move() {
        Point head = new Point(snake.getHead()); // make a local 'copy', don't want a reference

        if (requestedDirection.getOppositeDirection() != snake.getDirection()) {
            snake.setDirection(requestedDirection);
        }

        switch (snake.getDirection()) {
            case UP:
                head.y--;
                if (head.y < 0)
                    head.y = Board.TILES - 1; // wrap around to the bottom of the board
                break;
            case DOWN:
                head.y++;
                if (head.y > Board.TILES - 1)
                    head.y = 0;
                break;
            case LEFT:
                head.x--;
                if (head.x < 0)
                    head.x = Board.TILES - 1;
                break;
            case RIGHT:
                head.x++;
                if (head.x > Board.TILES - 1)
                    head.x = 0;
                break;
        }

        snake.getSegments().add(head);
        snake.getSegments().remove(0);
    }

    public void gameLoop() {
        board.addKeyListener(new Controls());

        while(isRunning) {
            update();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("GAME OVER"); // TODO this should change the state
    }

    public JPanel getStatePanel() {
        return board;
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
