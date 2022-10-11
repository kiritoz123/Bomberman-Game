package oop.bomberman.entities.character;

import javafx.scene.image.Image;
import oop.bomberman.entities.Entity;
import oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class character extends Entity {

    protected final int MAX_ANIMATE = 7500; //save the animation status
    protected int animate = 0;

    protected int X;
    protected int Y;
    protected int speed;

    public character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void goLeft() {
        X = x - speed;
    }

    public void goRight() {
        X = x + speed;
    }

    public void goUp() {
        Y = y - speed;
    }

    public void goDown() {
        Y = y + speed;
    }

    public void move() {
        x = X;
        y = Y;
    }

    public void stay() {
        X = x;
        Y = y;
    }
    public Rectangle getBounds() {
        return new Rectangle(X, Y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    protected void animated() {
        if (animate < MAX_ANIMATE) {
            animate++;
        } else {
            animate = 0; //reset animation
        }
    }
}




