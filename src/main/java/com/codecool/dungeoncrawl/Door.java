package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Door extends Actor {

    public Door(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "door";
    }

    @Override
    public void move(int dx, int dy) {
        dx=0;
        dy=0;
        super.move(dx, dy);
    }
}
