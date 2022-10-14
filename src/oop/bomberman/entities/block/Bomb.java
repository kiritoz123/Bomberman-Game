package oop.bomberman.entities.block;

import javafx.scene.image.Image;
import oop.bomberman.entities.Entity;
import oop.bomberman.entities.EntityList;
import oop.bomberman.entities.character.Bomber;
import oop.bomberman.entities.character.character;
import oop.bomberman.graphics.Sprite;

import java.awt.*;

import static oop.bomberman.BombermanGame.bomberman;

public class Bomb extends Entity {
    private int animate = 0;

    public void setAnimate(int animate) {
        this.animate = animate;
    }

    private int radius;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(2);
        this.radius = 2;
    }

    public Bomb(int xUnit, int yUnit, Image img, int radius) {
        super(xUnit, yUnit, img);
        setLayer(2);
        this.radius = radius;
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

