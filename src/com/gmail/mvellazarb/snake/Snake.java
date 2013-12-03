package com.gmail.mvellazarb.snake;

import java.util.ArrayList;
import java.awt.Point;

public class Snake {
    public ArrayList<Point> segments = new ArrayList<Point>();
    public Direction direction = Direction.RIGHT;

    public Snake() {
        segments.add(new Point(9, 12));
        segments.add(new Point(10, 12));
        segments.add(new Point(11, 12));
        segments.add(new Point(12, 12));
    }
}
