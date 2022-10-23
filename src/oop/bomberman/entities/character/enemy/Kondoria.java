package oop.bomberman.entities.character.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;

import java.util.Random;

import static oop.bomberman.BombermanGame.bomberman;
import static oop.bomberman.entities.EntityList.enemies;

public class Kondoria extends Enemy {
    private int slow = 0;
    private int slow2 = 0;

    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(4);
        setSpeed(1);
        CreateMove();
    }

    @Override
    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate++, 20).getFxImage();
    }

    @Override
    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animate++, 20).getFxImage();
    }

    @Override
    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate++, 20).getFxImage();
    }

    @Override
    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animate++, 20).getFxImage();
    }

    @Override
    public void stay() {
        super.stay(); // gap vat can thi random lai
        CreateMove();
    }

    @Override
    public void CreateMove() {
        Random rand = new Random();
        move = rand.nextInt(4);
    }


    @Override
    public void update() {
        if (alive) {
            slow = slow > 1000 ? 0 : slow + 1;
            if (slow % 10 == 0) {
                CreateMove();
            }
            if (move == 0) goLeft();
            if (move == 1) goRight();
            if (move == 2) goUp();
            if (move == 3) goDown();
        } else {
            time++;
            img = Sprite.kondoria_dead.getFxImage();
            if (time > 40) {
                enemies.remove(this);
            }
        }
    }


    @Override
    public void render(GraphicsContext gc) {
        if ((Math.abs(bomberman.getX() - this.x) > 64 || Math.abs(bomberman.getY() - this.y) > 64) && isAlive()) {
            slow2 = slow2 > 2000 ? 0 : slow2 + 1;
            if (slow2 % 500 == 0 || slow2 % 501 == 0 || slow2 % 502 == 0 || slow2 % 503 == 0
                    || slow2 % 504 == 0 || slow2 % 505 == 0) {
                try {
                    try {
                        gc.drawImage(img, x, y);
                    } catch (InternalError e) {
                        System.out.println("Exception");
                    }

                } catch (NullPointerException | InternalError e) {
                    System.out.println("Exception");
                }
            }


        } else {
            super.render(gc);
        }

    }
}
