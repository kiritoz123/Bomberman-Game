package oop.bomberman.entities.block;

import javafx.scene.image.Image;
import oop.bomberman.entities.Entity;

import static oop.bomberman.BombermanGame.HEIGHT;
import static oop.bomberman.BombermanGame.WIDTH;

public class Wall extends Entity {
    public Wall(int x, int y, Image img) {
        super(x, y, img);
        if (x == 0 || y == 0 || x == WIDTH - 1 || y == HEIGHT - 1) {
            setLayer(5);
        } else setLayer(4);
    }

    @Override
    public void update() {

    }
}
