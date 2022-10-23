package oop.bomberman.entities.character.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static oop.bomberman.BombermanGame.bomberman;
import static oop.bomberman.entities.EntityList.enemies;

public class Kondoria extends Enemy {
    private final int slow = 0;

    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(4);
        setSpeed(1);
        CreateMove();
    }

    @Override
    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate, 10).getFxImage();
    }

    @Override
    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animate, 10).getFxImage();
    }

    @Override
    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate++, 10).getFxImage();
    }

    @Override
    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animate, 10).getFxImage();
    }

    @Override
    public void stay() {
        super.stay(); // gap vat can thi random lai
        CreateMove();
    }

    @Override
    public void CreateMove() {
        Random rand = new Random();
        move = rand.nextInt(2);
    }


    @Override
    public void update() {
        if (alive) {
            if (move == 1 || Math.abs(bomberman.getY() - this.y) < 10) {
                if (bomberman.getX() < this.x) {
                    goLeft();
                }
                if (bomberman.getX() > this.x) {
                    goRight();
                }
            } else if (move == 0 || Math.abs(bomberman.getX() - this.x) < 10) {
                if (bomberman.getY() > this.y) {
                    goDown();
                }
                if (bomberman.getY() < this.y) {
                    goUp();
                }
            }
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
        if (bomberman.isAlive() && alive) {
            Timer count = new Timer();
            {
                count.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        gc.drawImage(img, x, y);
                        count.cancel();
                    }
                }, 500, 1);
            }
        } else {
            super.render(gc);
        }

    }
}
