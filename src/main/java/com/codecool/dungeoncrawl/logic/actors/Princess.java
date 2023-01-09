package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Princess extends Actor{
    public Princess(Cell cell) {
        super(cell);
        health = 1;
    }

    @Override
    public String getTileName() {
        return "princess";
    }

    @Override
    public void move(int dx, int dy) {
        dx = 0;
        dy = 0;
        super.move(dx, dy);
    }
}
