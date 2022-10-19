package oop.bomberman.entities.character.enemy;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;

import java.util.Random;

import static oop.bomberman.BombermanGame.bomberman;
import static oop.bomberman.entities.EntityList.enemies;

public class Kondoria extends Enemy{
    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
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
        img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3,animate, 10).getFxImage();
    }

    @Override
    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate++, 10).getFxImage();
    }

    @Override
    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3,animate, 10).getFxImage();;
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
    public void restartEnemy() {

    }

    @Override
    public void update() {
        if(alive) {
            if(move == 1 || Math.abs(bomberman.getY()-this.y) < 10) {
                if (bomberman.getX() < this.x) {
                    goLeft();
                }
                if (bomberman.getX() > this.x) {
                    goRight();
                }
            } else if (move == 2 || Math.abs(bomberman.getX()-this.x) < 10) {
                if (bomberman.getY() > this.y) {
                    goDown();
                }
                if (bomberman.getY() < this.y) {
                    goUp();
                }
            } else {
                stay();
                if(Math.abs(bomberman.getY()-this.Y) < 300 && Math.abs(bomberman.getX()-this.X) < 300){
                    speed = 2;
                } else {
                    speed = 1;
                }
            }
        }
        else {
            time++;
            img = Sprite.kondoria_dead.getFxImage();
            if(time > 40) {
                enemies.remove(this);
            }
        }
    }
}
