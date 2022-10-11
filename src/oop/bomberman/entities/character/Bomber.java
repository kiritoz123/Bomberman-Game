package oop.bomberman.entities.character;


import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import oop.bomberman.entities.block.Bomb;
import oop.bomberman.graphics.Sprite;

import java.awt.*;

import static oop.bomberman.BombermanGame.*;
import static oop.bomberman.entities.EntityList.bombs;


public class Bomber extends character {

    private int countBomb;
    private boolean placeBomb = false;
    //private final List<Bomb> bombs = new ArrayList<>();
    private KeyCode direction = null;
    private  int time = 0;
    private int radius;
    private int power;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1);
        setSpeed(2); //set speed
        setPower(1);
        setRadius(1); // set khoang no bom
        setCountBomb(3);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getCountBomb() {
        return countBomb;
    }

    public void setCountBomb(int countBomb) {
        this.countBomb = countBomb;
    }


    @Override
    public void update() {

        if (direction == KeyCode.LEFT || direction == KeyCode.A) {
            goLeft();
        }
        if (direction == KeyCode.RIGHT || direction == KeyCode.D) {
            goRight();
        }
        if (direction == KeyCode.UP || direction == KeyCode.W) {
            goUp();
        }
        if (direction == KeyCode.DOWN || direction == KeyCode.S) {
            goDown();
        }
        if (placeBomb) {
            putBomb();
        }
        for (int i = 0; i < bombs.size(); i++) {
            if (!bombs.get(i).isAlive()) {
                bombs.remove(bombs.get(i));
                countBomb++;
            }
        }

        if(!alive) {
            time++;
            img = Sprite.movingSprite(Sprite.player2_dead1, Sprite.player2_dead2,
                    Sprite.player2_dead3, animate++, 20).getFxImage();
            if(time > 30) {
                bomberman = new Bomber(1, 1, Sprite.player2_right.getFxImage());
            }
        }
        animated();
    }

    public void handleKeyPressedEvent(KeyCode keyCode) {

        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT
                || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN || keyCode == KeyCode.A || keyCode == KeyCode.D
        || keyCode == KeyCode.W || keyCode == KeyCode.S) {
            this.direction = keyCode;
        }
        if (keyCode == KeyCode.SPACE) {
            placeBomb = true;
        }
    }

    public void handleKeyReleasedEvent(KeyCode keyCode) {
        if (direction == keyCode) {
            if (direction == KeyCode.LEFT || direction == KeyCode.A) {
                img = Sprite.player2_left.getFxImage();
                Bleft = true;
            }
            if (direction == KeyCode.RIGHT || direction == KeyCode.D) {
                img = Sprite.player2_right.getFxImage();
                Bright = true;
            }
            if (direction == KeyCode.UP || direction == KeyCode.W) {
                img = Sprite.player2_up.getFxImage();
            }
            if (direction == KeyCode.DOWN || direction == KeyCode.S) {
                img = Sprite.player2_down.getFxImage();
            }
            direction = null;
        }
        if (keyCode == KeyCode.SPACE) {
            placeBomb = false;
        }
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.player2_left, Sprite.player2_left_1, Sprite.player2_left_2, animate++, 20).getFxImage();

    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.player2_right, Sprite.player2_right_1, Sprite.player2_right_2, animate++, 20).getFxImage();

    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.player2_up, Sprite.player2_up_2, Sprite.player2_up_2, animate++, 20).getFxImage();

    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.player2_down, Sprite.player2_down_1, Sprite.player2_down_2, animate++, 20).getFxImage();
    }

    //
    public void putBomb() {

        int Bx = (int) Math.round((x + 4) / (double) Sprite.SCALED_SIZE);
        int By = (int) Math.round((y + 4) / (double) Sprite.SCALED_SIZE);
        if (countBomb > 0) {
            for (Bomb bomb : bombs) {
                if (Bx * Sprite.SCALED_SIZE == bomb.getX() && By * Sprite.SCALED_SIZE == bomb.getY()) return;
            }//ko cho bom dat cung vi tri
            Bomb bomb = new Bomb(Bx, By, Sprite.bomb.getFxImage(), radius);
            bombs.add(bomb);
            countBomb--;
        }
    }


    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }


    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(X + 4, Y + 4, Sprite.SCALED_SIZE * 3 / 4, Sprite.SCALED_SIZE * 3 / 4);
    }


}