package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.BigMonster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;

    private List<Actor> enemyList = new ArrayList<>();

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public Player getPlayer() {
        return player;
    }

    public void addSkeleton(Skeleton skeleton){
        enemyList.add(skeleton);
    }

    public void addBigMonster(BigMonster bigMonster){
        enemyList.add(bigMonster);
    }
    public List<Actor> getEnemyList() {
        return enemyList;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
