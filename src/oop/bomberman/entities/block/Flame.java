package oop.bomberman.entities.block;

import javafx.scene.image.Image;
import oop.bomberman.Sound.SoundPlay;
import oop.bomberman.entities.Entity;
import oop.bomberman.graphics.Sprite;

import java.awt.*;

import static oop.bomberman.entities.EntityList.block;
import static oop.bomberman.entities.EntityList.flame;

public class Flame extends Entity {
    private final int size = Sprite.SCALED_SIZE;
    private int left;
    private int right;
    private int top;
    private int down;
    private int direction;
    private int radius;
    private int time = 0;

    public Flame(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public Flame(int x, int y, Image img) {
        super(x, y, img);
    }

    public Flame(int x, int y, Image image, int direction) {
        this.x = x;
        this.y = y;
        this.img = image;
        this.direction = direction;
    }

    @Override
    public void update() {
        if (time < 20) {
            time++;
            setImg(); //set animation

        } else flame.remove(this);

    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void renderExplosion() {
        flameRight();
        flameLeft();
        flameTop();
        flameDown();
        Explosion();
        //new SoundPlay("sound/bomb_explosion.wav", "explosion");

    }

    public void Explosion() { // create flame
        flame.add(new Flame(x, y, Sprite.bomb_exploded.getFxImage(), 0));
        for (int i = 0; i < right; i++) {
            Flame e = new Flame(x + size * (i + 1), y);
            if (i == right - 1) {
                e.img = Sprite.explosion_horizontal_right_last.getFxImage();
                e.direction = 2;
            } else {
                e.img = Sprite.explosion_horizontal.getFxImage();
                e.direction = 1;
            }
            flame.add(e);
        }
        for (int i = 0; i < left; i++) {
            Flame e = new Flame(x - size * (i + 1), y);
            if (i == left - 1) {
                e.img = Sprite.explosion_horizontal_left_last.getFxImage();
                e.direction = 3;
            } else {
                e.img = Sprite.explosion_horizontal.getFxImage();
                e.direction = 1;
            }
            flame.add(e);
        }
        for (int i = 0; i < top; i++) {
            Flame e = new Flame(x, y - size * (i + 1));
            if (i == top - 1) {
                e.img = Sprite.explosion_vertical_top_last.getFxImage();
                e.direction = 5;
            } else {
                e.img = Sprite.explosion_vertical.getFxImage();
                e.direction = 4;
            }
            flame.add(e);
        }
        for (int i = 0; i < down; i++) {
            Flame e = new Flame(x, y + size * (i + 1));
            if (i == down - 1) {
                e.img = Sprite.explosion_vertical_down_last1.getFxImage();
                e.direction = 6;
            } else {
                e.img = Sprite.explosion_vertical1.getFxImage();
                e.direction = 4;
            }
            flame.add(e);
        }
    }

    /**
     * Xu ly flame khi gap vat can.
     *
     * @param r
     * @return
     */
    private Object collisionType(Rectangle r) {
        for (Entity e : block) {
            Rectangle r2 = e.getBounds();
            if (r.intersects(r2)) {
                return e;
            }
        }
        return r;
    }

    private void flameRight() {
        for (int i = 0; i < radius; i++) {
            Rectangle ex_right = new Rectangle(x + size * (i + 1), y, size, size);
            if (collisionType(ex_right) instanceof Wall) {
                right = i;
                return;
            } else if (collisionType(ex_right) instanceof Brick) {
                right = i + 1;
                return;
            }
            right = i + 1;
        }
    }


    private void flameLeft() {
        for (int i = 0; i < radius; i++) {
            Rectangle ex_left = new Rectangle(x - size * (i + 1), y, size, size);
            if (collisionType(ex_left) instanceof Wall) {
                left = i;
                return;
            } else if (collisionType(ex_left) instanceof Brick) {
                left = i + 1;
                return;
            }
            left = i + 1;
        }
    }

    private void flameTop() {
        for (int i = 0; i < radius; i++) {
            Rectangle ex_top = new Rectangle(x, y - size * (i + 1), size, size);
            if (collisionType(ex_top) instanceof Wall) {
                top = i;
                return;
            } else if (collisionType(ex_top) instanceof Brick) {
                top = i + 1;
                return;
            }
            top = i + 1;
        }
    }

    private void flameDown() {
        for (int i = 0; i < radius; i++) {
            Rectangle ex_down = new Rectangle(x, y + size * (i + 1), size, size);
            if (collisionType(ex_down) instanceof Wall) {
                down = i;
                return;
            } else if (collisionType(ex_down) instanceof Brick) {
                down = i + 1;
                return;
            }
            down = i + 1;
        }
    }

    private void setImg() {
        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1,
                        Sprite.bomb_exploded2, time, 20).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1
                        , Sprite.explosion_horizontal2, time, 20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1
                        , Sprite.explosion_horizontal_right_last2, time, 20).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1
                        , Sprite.explosion_horizontal_left_last2, time, 20).getFxImage();
                break;
            case 4:
                img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1
                        , Sprite.explosion_vertical2, time, 20).getFxImage();
                break;
            case 5:
                img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1
                        , Sprite.explosion_vertical_top_last2, time, 20).getFxImage();
                break;
            case 6:
                img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1
                        , Sprite.explosion_vertical_down_last2, time, 20).getFxImage();
                break;
        }
    }
}
