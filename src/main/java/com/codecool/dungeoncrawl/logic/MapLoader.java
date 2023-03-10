package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Door;
import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.actors.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String txtMapFile) {

        InputStream is = MapLoader.class.getResourceAsStream(txtMapFile);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int skeletonNumber = 0;

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            skeletonNumber ++;
                            map.addSkeleton(new Skeleton(cell, skeletonNumber));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'i':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new ItemKey(cell);
                            break;
                        case 'd':
                            cell.setType(CellType.FLOOR);
                            new DeadSkeleton(cell);
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            new Cactus(cell);
                            break;
                        case 'u':
                            cell.setType(CellType.FLOOR);
                            new Door(cell);
                            break;
                        case 'f':
                            cell.setType(CellType.FLOOR);
                            new Cheese(cell);
                            break;
                        case 'm':
                            cell.setType(CellType.FLOOR);
                            map.addBigMonster(new BigMonster(cell));
                            break;
                        case 'q':
                            cell.setType(CellType.FLOOR);
                            map.addPrincess(new Princess(cell));
                            break;
                        case 'h':
                            cell.setType(CellType.FLOOR);
                            new Heart(cell);
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
