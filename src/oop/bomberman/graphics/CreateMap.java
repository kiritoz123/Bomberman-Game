package oop.bomberman.graphics;

import oop.bomberman.Item.*;
import oop.bomberman.entities.EntityList;
import oop.bomberman.entities.block.Brick;
import oop.bomberman.entities.block.Grass;
import oop.bomberman.entities.block.Portal;
import oop.bomberman.entities.block.Wall;
import oop.bomberman.entities.character.enemy.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static oop.bomberman.entities.EntityList.block;
import static oop.bomberman.entities.EntityList.enemies;

public class CreateMap {
    public static int WIDTHMAP = 25;
    public static int HEIGHTMAP = 15;

    private static final int[][] gird = new int[HEIGHTMAP][WIDTHMAP];

    public static void setGrid(int row, int col, int c) {
        CreateMap.gird[row][col] = c;
    }

    public static int[][] getGird() {
        return gird;
    }

    public static void importData(int[][] arr, int stage) throws IOException {
        String path = "res/Levels/Level" + stage + ".txt";
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
        block.clear();
        try {
            importData(gird, level);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < HEIGHTMAP; i++) {
            for (int j = 0; j < WIDTHMAP; j++) {
                //EntityList.block.add(new Wall(j,i,Sprite.wall.getFxImage()));
                if (gird[i][j] == 2) {
                    block.add(new Wall(j, i, Sprite.wall.getFxImage()));
                } else {
                    block.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    if (gird[i][j] == 1) {
                        block.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                        block.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (gird[i][j] == 3) {
                        block.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                        block.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (gird[i][j] == 4) {
                        block.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        block.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (gird[i][j] == 5) {
                        block.add(new WallItem(j, i, Sprite.powerup_wallpass.getFxImage()));
                        block.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (gird[i][j] == 6) {
                        block.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                        block.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (gird[i][j] == 7) {
                        block.add(new FlamePass(j, i, Sprite.powerup_flamepass.getFxImage()));
                        block.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (gird[i][j] == 8) {
                        enemies.add(new Balloom(j, i, Sprite.balloom_left1.getFxImage()));
                    }
                    if (gird[i][j] == 9) {
                        enemies.add(new Oneal(j,i,Sprite.oneal_left1.getFxImage()));
                    }
                    if (gird[i][j] == 10) {
                        enemies.add(new Doll(j,i,Sprite.doll_left1.getFxImage()));
                    }
                    if (gird[i][j] == 11) {
                        enemies.add(new Kondoria(j,i,Sprite.kondoria_left1.getFxImage()));
                    }
                    if(gird[i][j] == 12) {
                        enemies.add(new Minvo(j,i,Sprite.minvo_left1.getFxImage()));
                    }
                    if(gird[i][j] == 13) {
                        block.add(new Brick(j,i,Sprite.brick.getFxImage()));
                    }
                }
            }
        }
    }

}
