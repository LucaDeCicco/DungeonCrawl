package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class BigMonster extends Actor{
    public BigMonster(Cell cell) {
        super(cell);
        damage = 7;
        health = 60;
    }

    @Override
    public String getTileName() {
        return "bigMonster";
    }

    @Override
    public void move(int dx, int dy) {
        dx = 0;
        dy = 0;
        super.move(dx, dy);
    }
}
