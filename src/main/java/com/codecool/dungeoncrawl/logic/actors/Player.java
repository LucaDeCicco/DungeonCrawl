package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor implements Inventory {

    public static boolean nextLevel = false;
//    List<Items> itemsList = new ArrayList<>();
    private boolean hasSword;
    public Player(Cell cell) {
        super(cell);
        damage = 5;
        hasSword = false;
    }

    public String getTileName() {
        return "player";
    }

    public void addItem(Items item){
        listItems.add(item);
    }

    public List<Items> getListOfItems(){
        return listItems;
    }

    @Override
    public void move(int dx, int dy) {
        int hasSwordCounter = 0;
        for (Items item : listItems) {
            if (item instanceof Sword){
                hasSwordCounter ++;
            }
        }
        if (hasSwordCounter == 1){
            damage = 10;
        } else if (hasSwordCounter==2) {
            damage = 30;
        }
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType()!= CellType.WALL&&
                (nextCell.getActor()==null||
                        nextCell.getActor().getTileName().equals("deadSkeleton"))){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        else if (nextCell.getType()!= CellType.WALL&& nextCell.getActor()!=null) {
            health = health - nextCell.getActor().damage;
            nextCell.getActor().health = nextCell.getActor().health - damage;
            if (nextCell.getActor().getTileName().equals("door")){
                for (Items item : listItems){
                    if (item instanceof ItemKey){
                        nextLevel = true;

                    }
                }
            }
        }
    }
}
