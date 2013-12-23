package com.gmail.mvellazarb.snake;

import java.util.ArrayList;
import java.awt.Point;

public class Snake {
    private ArrayList<Point> segments = new ArrayList<Point>();
    private Direction direction = Direction.RIGHT;

    public Snake() {
        segments.add(new Point(9, 12));
        segments.add(new Point(10, 12));
        segments.add(new Point(11, 12));
        segments.add(new Point(12, 12));
    }
    
    public ArrayList<Point> getSegments() {
        return segments;
    }
    
    public Point getHead() {
        return segments.get(segments.size() - 1);
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
