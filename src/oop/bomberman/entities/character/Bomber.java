package oop.bomberman.entities.character;


import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import oop.bomberman.BombermanGame;
import oop.bomberman.Sound.SoundPlay;
import oop.bomberman.entities.EntityList;
import oop.bomberman.entities.block.Bomb;
import oop.bomberman.graphics.Sprite;

import java.awt.*;

import static oop.bomberman.BombermanGame.WIDTH;
import static oop.bomberman.BombermanGame.bomberman;
import static oop.bomberman.entities.EntityList.bombs;
import static oop.bomberman.graphics.CreateMap.WIDTHMAP;


public class Bomber extends character {

    private int countBomb;
    private boolean placeBomb = false;
    //private final List<Bomb> bombs = new ArrayList<>();
    private KeyCode direction = null;
    private int time = 0;
    private int trace = 0;
    private int radius;


    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1);
        setSpeed(2); //set speed
        setRadius(1); // set khoang no bom
        setCountBomb(1);
    }

    public int getTrace() {
        return trace;
    }

    public int getCountBomb() {
        return countBomb;
    }

    public void setCountBomb(int countBomb) {
        this.countBomb = countBomb;
    }

    @Override
    public void update() {
        if (isAlive()) {
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
            if (placeBomb && bomberman.isAlive()) {
                putBomb();
            }
            for (int i = 0; i < bombs.size(); i++) {
                if (!bombs.get(i).isAlive()) {
                    bombs.remove(bombs.get(i));
                    countBomb++;
                }
            }
        }
        if (!alive) {
            time++;
            img = Sprite.movingSprite(Sprite.player2_dead1, Sprite.player2_dead2,
                    Sprite.player2_dead3, animate++, 20).getFxImage();
            if (time > 20) {
                EntityList.clearList();
                BombermanGame.moveCamera(-trace, 0);
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

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void handleKeyReleasedEvent(KeyCode keyCode) {
        if (direction == keyCode) {
            if (direction == KeyCode.LEFT || direction == KeyCode.A) {
                img = Sprite.player2_left.getFxImage();

            }
            if (direction == KeyCode.RIGHT || direction == KeyCode.D) {
                img = Sprite.player2_right.getFxImage();

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

    @Override
    public void goLeft() {
        int camera = 0;
        if (this.x - trace > WIDTH * Sprite.SCALED_SIZE / 2 || this.x <= WIDTH * Sprite.SCALED_SIZE / 2)
            super.goLeft();
        else {
            camera -= speed;
            super.goLeft();
        }
        trace += camera;
        BombermanGame.moveCamera(camera, 0);
        img = Sprite.movingSprite(Sprite.player2_left, Sprite.player2_left_1, Sprite.player2_left_2, animate++, 20).getFxImage();

    }

    @Override
    public void goRight() {
        int camera = 0;
        if (this.x - trace < WIDTH * Sprite.SCALED_SIZE / 2 || this.x >= WIDTHMAP * Sprite.SCALED_SIZE - WIDTH * Sprite.SCALED_SIZE / 2)
            super.goRight();
        else {
            camera += speed;
            super.goRight();
        }
        trace += camera;
        BombermanGame.moveCamera(camera, 0);
        img = Sprite.movingSprite(Sprite.player2_right, Sprite.player2_right_1, Sprite.player2_right_2, animate++, 28).getFxImage();

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
            new SoundPlay("sound/put_bombs.wav", "putBomb");
            bombs.add(bomb);
            countBomb--;
        }
    }


    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(X + 4, Y + 4, Sprite.SCALED_SIZE * 3 / 4, Sprite.SCALED_SIZE * 3 / 4);
    }


}