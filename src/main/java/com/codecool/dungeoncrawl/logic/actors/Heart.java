package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Heart extends Actor{
    public Heart(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "heart";
    }
}
