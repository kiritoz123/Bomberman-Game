package oop.bomberman.entities.block;

import javafx.scene.image.Image;
import oop.bomberman.entities.Entity;
import oop.bomberman.graphics.Sprite;


import static oop.bomberman.entities.EntityList.block;

public class Brick extends Entity {
    public Brick(int x, int y, Image img) {

        super(x,y,img);
        setLayer(2);
    }

    @Override
    public void update() {

    }
}
