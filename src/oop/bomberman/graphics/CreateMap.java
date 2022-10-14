package oop.bomberman.graphics;

import oop.bomberman.BombermanGame;
import oop.bomberman.Item.BombItem;
import oop.bomberman.entities.Entity;
import oop.bomberman.entities.EntityList;
import oop.bomberman.entities.block.Brick;
import oop.bomberman.entities.block.Grass;
import oop.bomberman.entities.block.Wall;
import oop.bomberman.entities.character.enemy.Balloom;
import oop.bomberman.level.Layer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static oop.bomberman.BombermanGame.*;
import static oop.bomberman.entities.EntityList.block;
import static oop.bomberman.entities.EntityList.enemies;

public class CreateMap {
    public static int mapStartX = 0;// Coordinates of Map relative to Window.
    public static int mapStartY = 0;
    public static int WIDTHMAP = 30;
    public static int HEIGHTMAP = 15;

    private static final int[][] gird = new int[HEIGHTMAP][WIDTHMAP];

    public static void setGrid(int row, int col, char c) {
        CreateMap.gird[row][col] = c;
    }

    public static int[][] getGird() {
        return gird;
    }

    public static void importData(int[][] arr, int stage) throws IOException {
        String path = "res/Levels/Level" + 1 + ".txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String[] line;
        for (int i = 0; i < HEIGHTMAP; i++) {
            line = bufferedReader.readLine().split("\\s+");
            for (int j = 0; j < WIDTHMAP; j++) {
                arr[i][j] = Integer.parseInt(line[j]);
            }
        }
        bufferedReader.close();
    }

    public static void createMapLevel(int level) {
        EntityList.clearList();
        enemies.add(new Balloom(2,2,Sprite.balloom_left1.getFxImage()));
        try {
            importData(gird, level);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < HEIGHTMAP; i++) {
            for (int j = 0; j < WIDTHMAP; j++) {
                //EntityList.block.add(new Wall(j,i,Sprite.wall.getFxImage()));
                if(gird[i][j] == 2) {
                    block.add(new Wall(j,i,Sprite.wall.getFxImage()));
                }
                else {
                    block.add(new Grass(j,i,Sprite.grass.getFxImage()));
                    if(gird[i][j] == 1) {
                        block.add(new BombItem(j,i,Sprite.powerup_bombs.getFxImage()));
                        block.add(new Brick(j,i,Sprite.brick.getFxImage()));
                    }
                }

            }
        }
    }

}
