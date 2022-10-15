package oop.bomberman.entities.block;

import javafx.scene.image.Image;
import oop.bomberman.entities.Entity;



public class Portal extends Entity {
    public Portal(int x, int y, Image img) {
        super(x,y,img);
        setLayer(1);
    }
    @Override
    public void update() {

    }
}
