package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor implements Inventory {

//    List<Items> itemsList = new ArrayList<>();
    public Player(Cell cell) {
        super(cell);
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
        super.move(dx, dy);
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
}
