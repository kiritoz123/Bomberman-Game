package oop.bomberman.entities.block;

import javafx.scene.image.Image;
import oop.bomberman.entities.Entity;
import oop.bomberman.entities.EntityList;
import oop.bomberman.graphics.Sprite;

import static oop.bomberman.BombermanGame.bomberman;

public class Bomb extends Entity {
    private static Entity bomb;
    private static int x;
    private static int y;
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public static void putBomb() {
        x = bomberman.getX() / 32;
        if(bomberman.getY() % 32 == 0) {
            y = bomberman.getY() / 32;
        }
        else y = bomberman.getY() / 32 + 1;

        bomb = new Bomb(x,y,Sprite.bomb.getFxImage());
        EntityList.block.add(bomb);
    }
    @Override
    public void update() {

    }
}
