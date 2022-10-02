package oop.bomberman.entities.character;

import javafx.scene.image.Image;
import oop.bomberman.entities.Entity;
public abstract class character extends Entity {

    protected int swap;
    protected String direction;
    protected int count;
    protected int countRun;
   

    public character(int x_unit, int y_unit, Image img) {
        super(x_unit, y_unit, img);
    }

    public character() {

    }



    public int getSwap() {
        return swap;
    }

    public void setSwap(int swap) {
        this.swap = swap;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCountRun() {
        return countRun;
    }

    public void setCountRun(int countRun) {
        this.countRun = countRun;
    }



    @Override
    public void update() {

    }

    public boolean isLife() {
        return true;
    }
}




