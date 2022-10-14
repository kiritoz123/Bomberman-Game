package oop.bomberman.entities.block;

import javafx.scene.image.Image;
import oop.bomberman.entities.Entity;
import oop.bomberman.graphics.Sprite;


import static oop.bomberman.entities.EntityList.block;

public class Brick extends Entity {
    private int time = 0;
    public Brick(int x, int y, Image img) {

        super(x,y,img);
        setLayer(2);
    }

    @Override
    public void update() {
        if(!isAlive()) {
            if(time < 80) {
                time++;
                img = Sprite.movingSprite(Sprite.brick_exploded,Sprite.brick_exploded1,Sprite.brick_exploded2,time++,20).getFxImage();
            }
            else block.remove(this);
        }
    }
}
