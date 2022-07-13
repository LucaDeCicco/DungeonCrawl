package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.awt.*;
import java.util.Objects;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
//        System.out.println(nextCell.getActor().getTileName());
        if (nextCell.getType()!= CellType.WALL&& nextCell.getActor()==null){

            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        else if (nextCell.getType()!= CellType.WALL&& nextCell.getActor()!=null) {
            System.out.println(nextCell.getActor().getTileName());
        }

    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
