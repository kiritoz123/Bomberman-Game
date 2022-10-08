package oop.bomberman.graphics;

import oop.bomberman.entities.EntityList;
import oop.bomberman.entities.block.Brick;
import oop.bomberman.entities.block.Grass;
import oop.bomberman.entities.block.Wall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CreateMap {
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
        try {
            importData(gird, level);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < HEIGHTMAP; i++) {
            for (int j = 0; j < WIDTHMAP; j++) {
                //EntityList.block.add(new Wall(j,i,Sprite.wall.getFxImage()));
                switch (gird[i][j]) {

                    case 0:
                        EntityList.block.add(new Wall(j, i, Sprite.wall.getFxImage()));
                        EntityList.block.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        //EntityList.block.add(new Wall(j,i,Sprite.wall.getFxImage()));
                        break;
                    case 2:
                        EntityList.block.add(new Wall(j, i, Sprite.wall.getFxImage()));
                        break;
                    case 1:
                        EntityList.block.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    default:
                        EntityList.block.add(new Grass(j, i, Sprite.grass.getFxImage()));

                }
                //EntityList.block.add(new Grass(j,i,Sprite.grass.getFxImage()));
            }
        }
    }


}
