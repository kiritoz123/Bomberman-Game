package oop.bomberman.entities.character;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import oop.bomberman.entities.Entity;

public class Bomber extends character {
    public static int swap_kill = 1;
    private static int count_kill = 0;

    public Bomber() {

    }
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }

}