package oop.bomberman.entities.block;

import javafx.scene.image.Image;
import oop.bomberman.entities.Entity;
import oop.bomberman.graphics.Sprite;

import static oop.bomberman.BombermanGame.list_kill;
import static oop.bomberman.entities.EntityList.block;

public class Brick extends Entity {
    public Brick(int x, int y, Image img) {
        super(x,y,img);
    }

    @Override
    public void update() {
        for (Entity entity : block) {
            if (entity instanceof Brick) {
                if (list_kill[entity.getX() / 32][entity.getY() / 32] == 4) {
                    entity.setImg(Sprite.grass.getFxImage());
                }
            }
        }
    }
}
