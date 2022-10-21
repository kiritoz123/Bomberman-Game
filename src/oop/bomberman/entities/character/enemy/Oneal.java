package oop.bomberman.entities.character.enemy;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;

import java.util.Random;

import static oop.bomberman.BombermanGame.bomberman;
import static oop.bomberman.entities.EntityList.enemies;

public class Oneal extends Enemy{
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
        setSpeed(1);
        CreateMove();
    }

    @Override
    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 10).getFxImage();
    }

    @Override
    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3,animate, 10).getFxImage();
    }

    @Override
    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate++, 10).getFxImage();
    }

    @Override
    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3,animate, 10).getFxImage();;
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
    public void restartEnemy() {

    }

    @Override
    public void update() {
        if(alive) {
            if(bomberman.getX() == this.x) {
                if (bomberman.getY() < this.y) {
                    System.out.println("UP");
                    goUp();
                }
                else {
                    System.out.println("DOWN");
                    goDown();
                }
            }

            if (bomberman.getY() == this.y) {
                if (bomberman.getX() > this.x) {
                    System.out.println("RIGHT");
                    goRight();
                }
                else {
                    System.out.println("LEFT");
                    goLeft();
                }
            }


            if(move == 1) {
                if (bomberman.getX() < this.x) {
                    goLeft();
                }
                if (bomberman.getX() > this.x) {
                    goRight();
                }
            } else if (move == 2) {
                if (bomberman.getY() > this.y) {
                    goDown();
                }
                if (bomberman.getY() < this.y) {
                    goUp();
                }
            } else {
                stay();
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
