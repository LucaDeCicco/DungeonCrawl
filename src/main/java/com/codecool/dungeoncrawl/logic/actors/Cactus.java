package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Cactus extends Actor{
    public Cactus(Cell cell) {
        super(cell);
        damage=5;
    }

    @Override
    public String getTileName() {
        return "cactus";
    }

    @Override
    public void move(int dx, int dy) {
        dx=0;
        dy=0;
        super.move(dx, dy);

    }
}
