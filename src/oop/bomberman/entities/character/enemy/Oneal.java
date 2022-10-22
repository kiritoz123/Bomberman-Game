package oop.bomberman.entities.character.enemy;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;

import static oop.bomberman.BombermanGame.bomberman;
import static oop.bomberman.entities.EntityList.enemies;

public class Oneal extends Enemy {
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);

        setLayer(1);
        setSpeed(2);
        CreateMove();

    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate++, 20).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate++, 28).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate++, 20).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate++, 20).getFxImage();
    }

    @Override
    public void stay() {
        super.stay();
        CreateMove();

    }


    @Override
    public void update() {
        //CreateMove();
        if (isAlive()) {
            if (bomberman.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE < 0) goLeft();
            else if (bomberman.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE > 0) goRight();
            else if (bomberman.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE < 0) goUp();
            else goDown();
        } else if (time < 40) {
            super.stay();
            time++;
            img = Sprite.oneal_dead.getFxImage();
            img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
                    Sprite.mob_dead3, animate, 10).getFxImage();
        } else
            enemies.remove(this);
    }

    @Override
    public void CreateMove() {


    }

    @Override
    public void restartEnemy() {

    }


}