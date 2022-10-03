package oop.bomberman.graphics;

import oop.bomberman.entities.Entity;
import oop.bomberman.entities.EntityList;
import oop.bomberman.entities.block.Brick;
import oop.bomberman.entities.block.Grass;
import oop.bomberman.entities.block.Wall;
import oop.bomberman.entities.character.Bomber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CreateMap {
    private static char[][] gird = new char[15][25];
    public static void setGrid(int row,int col,char c) {
        CreateMap.gird[row][col] = c;
    }
    public static char[][] getGird() {
        return gird;
    }
    public static void importData(char[][] arr, int stage) throws IOException {
        String path = "res/Levels/Level" + 1 +".txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;
        for(int i = 0;i <15;i++) {
            line = bufferedReader.readLine();
            for(int j = 0;j < 25;j++) {
                arr[i][j] = line.charAt(j);
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
        for(int i = 0;i<15;i++) {
            for(int j = 0;j<25;j++) {
                //EntityList.block.add(new Wall(j,i,Sprite.wall.getFxImage()));
                switch (gird[i][j]) {

                    case '0':
                        EntityList.block.add(new Grass(i,j,Sprite.grass.getFxImage()));
                        break;
                    case '2':
                        EntityList.block.add(new Wall(j,i,Sprite.wall.getFxImage()));
                        break;
                    case '8':
                        EntityList.block.add(new Brick(j,i,Sprite.brick.getFxImage()));
                        break;

                }
                //EntityList.block.add(new Grass(j,i,Sprite.grass.getFxImage()));
            }
        }
    }

}
