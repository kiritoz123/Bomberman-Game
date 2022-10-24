package oop.bomberman.entities.block;

import javafx.scene.image.Image;
import oop.bomberman.entities.Entity;
import oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private final int radius;
    private int animate = 0;

    public Bomb(int xUnit, int yUnit, Image img, int radius) {
        super(xUnit, yUnit, img);
        this.radius = radius;
    }

    public void setAnimate(int animate) {
        this.animate = animate;
    }

    @Override
    public void update() {
        if (animate++ == 120) { //cho bom no
            explodeUpgrade();
        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 60).getFxImage();
    }

    public void explodeUpgrade() {
        Flame e = new Flame(x, y);
        e.setRadius(radius);
        e.renderExplosion();
        alive = false;
    }
}

