package oop.bomberman.entities.character.enemy;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;

import java.util.Random;

import static oop.bomberman.entities.EntityList.enemies;

public class Balloom extends Enemy {
    private int move;
    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
        setSpeed(1);

    }

    @Override
    public void update() {
        if(alive) {
            switch(move) {
                case 0 :
                    goLeft();
                    break;
                case 1 :
                    goRight();
                    break;
                case 2 :
                    goUp();
                    break;
                case 3 :
                    goDown();
                    break;
            }
        }
        else {
            time++;
            img = Sprite.balloom_dead.getFxImage();
            if(time > 40) {
                enemies.remove(this);
            }
        }

    }

    @Override
    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 18).getFxImage();
    }

    @Override
    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3,animate, 18).getFxImage();
    }

    @Override
    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate++, 18).getFxImage();
    }

    @Override
    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3,animate, 18).getFxImage();;
    }

    @Override
    public void stay() {
        super.stay(); // gap vat can thi random lai
        Random rand = new Random();
        move = rand.nextInt(4);
    }
}
