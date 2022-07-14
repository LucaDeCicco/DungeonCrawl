package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.*;
import com.sun.javafx.iio.gif.GIFImageLoaderFactory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;

public class Main extends Application {
     String txtMapFile= "/map.txt" ;
     Scene scene;

    GameMap map = MapLoader.loadMap(txtMapFile);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();

    Label damageLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        ui.add(new Label("Damage: "), 0, 2);
        ui.add(damageLabel, 1, 2);


        ui.add(new Label("Inventory: "),0,3);



        Button button = new Button("Pick item");
        ui.add(button,0,1);

        button.setFocusTraversable(false);
        button.setOnAction((event) -> {
            int x = map.getPlayer().getX();
            int y = map.getPlayer().getY();
            Items itemToPick = map.getCell(x,y).getItem();
            if (itemToPick!=null){
                if (itemToPick.getClass().getSimpleName().equals("Cheese")){
                    map.getPlayer().setHealth(30);
                }
            }
            if (itemToPick!=null){
                if (itemToPick.getClass().getSimpleName().equals("Sword")){
                    int swordsCounter = 0;
                    for (Items item: map.getPlayer().getListOfItems()){
                        if (item instanceof Sword){
                            swordsCounter++;
                        }
                    }
                    if (swordsCounter>0){
                        Tiles.getTileMap().replace("player", new Tiles.Tile(30,2));
                        map.getPlayer().setDamage(30);
                        System.out.println(map.getPlayer().getDamage());
                    }else{
                        Tiles.getTileMap().replace("player", new Tiles.Tile(27,0));
                    }
                }
                map.getPlayer().addItem(itemToPick);
                map.getCell(x,y).setItem(null);
                String inventory = "";
                for(Items item:map.getPlayer().getListOfItems()){
                    inventory+=item.getTileName()+" ";
                }
                ui.add(new Label(inventory),0,4);
            }
        });

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                refresh();
                break;
        }
        int[] dxList = new int[]{0,1,-1};
        Random random = new Random();
        List<Actor> newEnemyList = List.copyOf(map.getEnemyList());
        for (Actor enemy:newEnemyList){
            System.out.println(enemy.getTileName());
            if (enemy.getHealth()<=0){
                int enemyX = enemy.getX();
                int enemyY = enemy.getY();
                map.getEnemyList().remove(enemy);
                DeadSkeleton deadEnemy = new DeadSkeleton(map.getCell(enemyX,enemyY));
                map.getEnemyList().add(deadEnemy);
            }
            else {
                enemy.move(dxList[random.nextInt(3)],dxList[random.nextInt(3)]);
            }
        }
        if (map.getPlayer().getHealth() <= 0){
            System.exit(0);
        }

        refresh();


    }

    private void refresh() {
//        System.out.println(map.getPlayer().getDamage());
        if (Player.nextLevel){
            map = MapLoader.loadMap("/map2.txt");
            Player.nextLevel = false;
            scene.setOnKeyPressed(this::onKeyPressed);
        }
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        damageLabel.setText("" + map.getPlayer().getDamage());
    }
}
