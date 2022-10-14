package oop.bomberman.entities.character.enemy;

import javafx.scene.image.Image;
import oop.bomberman.BombermanGame;
import oop.bomberman.entities.character.Bomber;
import oop.bomberman.graphics.Sprite;

import java.awt.*;

import static oop.bomberman.BombermanGame.bomberman;
import static oop.bomberman.entities.EntityList.enemies;

public class Oneal extends Enemy {
    private int startX;
    private int startY;
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        startX = xUnit;
        startY = yUnit;
        setLayer(1);
        setSpeed(2);

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
        CreateMove();
        if (move == 0) goLeft();
        if (move == 1) goRight();
        if (move == 2) goUp();
        if (move == 3) goDown();
        if(! bomberman.isAlive()) {
            restartEnemy();
        }
        if(isAlive()){

        } else if(time < 40) {
            super.stay();
            time++;
            img = Sprite.oneal_dead.getFxImage();
            img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
                Sprite.mob_dead3, animate, 10).getFxImage();
        } else
            enemies.remove(this);
    }

    public void CreateMove() {

        if (bomberman.getX()  - x  < 0) move = 0;
        if (bomberman.getX()  - x  > 0) move = 1;
        if (bomberman.getY()  - y  < 0) move = 2;
        if (bomberman.getY()  - y  > 0) move = 3;
    }


    public void restartEnemy() {
        super.stay();
        this.x = startX * Sprite.SCALED_SIZE;
        this.y = startY * Sprite.SCALED_SIZE;
    }
}
