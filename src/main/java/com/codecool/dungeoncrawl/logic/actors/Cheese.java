package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Cheese extends Items{
    public Cheese(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "cheese";
    }
}
