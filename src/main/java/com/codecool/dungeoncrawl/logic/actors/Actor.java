package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.awt.*;
import java.util.Objects;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected int health = 10;

    protected int actorId ;
    protected int damage;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);

    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType()!= CellType.WALL && nextCell.getActor()==null){

            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }


    }

    public int getHealth() {
        return health;
    }

    public int getDamage(){return damage;}
    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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

    public int getActorId() {
        return actorId;
    }
}
