package oop.bomberman.entities.character;

import javafx.scene.image.Image;
import oop.bomberman.entities.Entity;

public abstract class character extends Entity {

    protected final int MAX_ANIMATE = 7500; //save the animation status
    protected int animate = 0;
    protected int X = x;
    protected int Y = y;
    protected int speed;
    protected int left = 0;
    protected int right = 0;
    protected int up = 0;
    protected int down = 0;

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


    protected void animate() {
        if (animate < MAX_ANIMATE) {
            animate++;
        } else {
            animate = 0; //reset animation
        }
    }
}




