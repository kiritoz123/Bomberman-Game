package oop.bomberman.entities.character.enemy;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;

import java.util.Random;

import static oop.bomberman.entities.EntityList.enemies;

public class Doll extends Enemy{

    public Doll(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
        setSpeed(2);
        CreateMove();
    }

    public void CreateMove() {
        Random random = new Random();
        move = random.nextInt(2);
    }

    @Override
    public void restartEnemy() {

    }

    @Override
    public void update() {
        if(isAlive()) {
            switch(move) {
                case 0 :
                    goLeft();
                    break;
                case 1 :
                    goRight();
                    break;
                default:
                    CreateMove();
            }
        } else {
            time++;
            img = Sprite.doll_dead.getFxImage();
            if(time > 40) {
                enemies.remove(this);
            }
        }
    }
    @Override
    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animate++, 18).getFxImage();
    }
    @Override
    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animate++, 18).getFxImage();
    }

    @Override
    public void stay() {
        super.stay();
        CreateMove();
    }


}
