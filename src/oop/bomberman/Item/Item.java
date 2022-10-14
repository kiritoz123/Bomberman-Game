package oop.bomberman.Item;

import javafx.scene.image.Image;
import oop.bomberman.entities.Entity;

public abstract class Item extends Entity {
    public Item(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1);
    }
    @Override
    public void update() {

    }
}
