package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

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

}
