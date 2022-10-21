package oop.bomberman.entities.character.enemy;

import javafx.scene.image.Image;
import javafx.util.Pair;
import oop.bomberman.graphics.Sprite;

import java.util.Random;

import static oop.bomberman.BombermanGame.bomberman;
import static oop.bomberman.entities.EntityList.enemies;
import static oop.bomberman.entities.character.enemy.Astar.A_Star.aStarSearch;
import static oop.bomberman.graphics.CreateMap.getGird;

public class Minvo extends Enemy {

    private final int[][] mp = getGird();
    private int slow = 0;

    public Minvo(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
        setSpeed(1);
    }

    public Pair<Integer, Integer> pair(int row, int col) {
        return aStarSearch(mp, new Pair<>(this.y / Sprite.SCALED_SIZE, this.x / Sprite.SCALED_SIZE)
                , new Pair<>(row, col));

    }

    @Override
    public void CreateMove() {
        Random rand = new Random();
        move = rand.nextInt(4);
        if(move == 1 || move == 3) setSpeed(2);
        else setSpeed(1);
    }

    @Override
    public void restartEnemy() {

    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate++, 20).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate++, 20).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate++, 20).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate++, 20).getFxImage();
    }


    @Override
    public void update() {

        if (isAlive()) {
            int destRow = (int) Math.round((bomberman.getY() - 2) / (double) Sprite.SCALED_SIZE);
            int destCol = (int) Math.round((bomberman.getX() - 2) / (double) Sprite.SCALED_SIZE);

            Pair<Integer, Integer> pair = pair(destRow, destCol);

            if ((pair.getKey() == -1 && pair.getValue() == -1)) {

                slow = slow > 100 ? 0 : slow + 1;
                switch (move) {
                    case 0:
                        if (slow % 2 == 0) goUp();
                        break;
                    case 1:
                        if (slow % 2 == 0) goDown();
                        break;
                    case 2:
                        if (slow % 2 == 0) goLeft();
                        break;
                    case 3:
                        if (slow % 2 == 0) goRight();
                        break;
                }
            } else {
                slow = slow > 100 ? 0 : slow + 1;

                if (this.y < pair.getKey() * 32) {
                    if (slow % 2 == 0) goDown();
                }
                if (this.y > pair.getKey() * 32) {
                    if (slow % 2 == 0) goUp();
                }
                if (this.x < pair.getValue() * 32) {
                    if (slow % 2 == 0) goRight();
                }
                if (this.x > pair.getValue() * 32) {
                    if (slow % 2 == 0) goLeft();
                }
            }
        } else {
            if (time < 40) {
                time++;
                img = Sprite.minvo_dead.getFxImage();

            } else {
                enemies.remove(this);
            }
        }
    }

    @Override
    public void stay() {
        super.stay();
        CreateMove();
    }
}
