package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {

    public Skeleton(Cell cell, int skeletonNumber) {
        super(cell);
        damage = 2;
        actorId = skeletonNumber;
    }


    @Override
    public String getTileName() {
        return "skeleton";
    }


}
