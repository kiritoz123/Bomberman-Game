package oop.bomberman.entities.character.enemy;

import javafx.scene.image.Image;
import oop.bomberman.entities.character.character;

public abstract class Enemy extends character {

    protected int time = 0;
    protected int move;

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
        alive = true;
    }

    public abstract void CreateMove();


}
