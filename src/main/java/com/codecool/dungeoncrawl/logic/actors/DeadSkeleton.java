package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class DeadSkeleton extends Actor{
    public DeadSkeleton(Cell cell) {
        super(cell);
    }

    @Override
    public void move(int dx, int dy) {
        dx=0;
        dy=0;
        super.move(dx, dy);
    }

    @Override
    public String getTileName() {
        return "deadSkeleton";
    }
}
