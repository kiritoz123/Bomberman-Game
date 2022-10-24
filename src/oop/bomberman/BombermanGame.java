package oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import oop.bomberman.Control.menu;
import oop.bomberman.Item.*;
import oop.bomberman.Sound.SoundPlay;
import oop.bomberman.entities.Entity;
import oop.bomberman.entities.EntityList;
import oop.bomberman.entities.block.*;
import oop.bomberman.entities.character.Bomber;
import oop.bomberman.entities.character.enemy.Enemy;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.level.Layer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static oop.bomberman.Control.menu.*;
import static oop.bomberman.Sound.SoundPlay.title_screen;
import static oop.bomberman.Sound.SoundPlay.updateSound;
import static oop.bomberman.entities.EntityList.*;
import static oop.bomberman.graphics.CreateMap.createMapLevel;
import static oop.bomberman.graphics.CreateMap.setGrid;


public class BombermanGame extends Application {


    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    private static final Group root = new Group();

    public static Bomber bomberman;
    public static boolean Run = true;
    public static boolean isPause;
    public static int level = 1;
    private static GraphicsContext gc;
    private static Canvas canvas;
    private final List<Entity> entities = new ArrayList<>();
    public List<Entity> Obj = new ArrayList<>();
    private long start1;
    private long start2;
    private long start3;
    private long start4;
    private long start5;
    private long elapsedTimeMillis1;
    private long elapsedTimeMillis12;
    private long elapsedTimeMillis3;
    private long elapsedTimeMillis4;
    private long elapsedTimeMillis5;
    private boolean flamePass = false;
    private long last_time;

    public static void main(String[] args) {

        Application.launch(BombermanGame.class);
    }

    public static void gameOver() {
        bomberman.setAlive(false);
        enemies.clear();

        new SoundPlay("sound/just_died.wav", "died");

        Image gameOver = new Image("images/art2.png");
        author_view.setImage(gameOver);
        root.getChildren().add(author_view);

        title_screen.stop();
        updateSound();

        time_number = 120;
        menu.updateMenu();
        createMapLevel(level);
        time.setText("Times: 0");
        bomberman = new Bomber(1, 1, Sprite.player2_right.getFxImage());


    }

    public static void moveCamera(int x, int y) {
        gc.translate(-x, -y);
    }

    @Override
    public void start(Stage Stage) throws Exception {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        canvas.setTranslateY(32);
        gc = canvas.getGraphicsContext2D();
        // Tao root container
        menu.createMenu(root);
        root.getChildren().add(canvas);
        root.getChildren().add(author_view);
        root.getChildren().add(startGame);

        //Pane pane = createBackground();
        Scene scene = new Scene(root);
        Stage.setResizable(false);
        Stage.setX(320);
        Stage.setY(180);
        Stage.setScene(scene);
        Stage.setTitle("BomberGame");
        Stage.show();
        last_time = System.currentTimeMillis();
        AnimationTimer time = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                if (isPause) {
                    update();
                    time();

                }
            }
        };
        time.start();
        createMapLevel(level);
        //createMap();
        bomberman = new Bomber(1, 1, Sprite.player2_right.getFxImage());
        scene.setOnKeyPressed(event -> {
            bomberman.handleKeyPressedEvent(event.getCode());
        });

        scene.setOnKeyReleased(event -> bomberman.handleKeyReleasedEvent(event.getCode()));
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                Obj.add(object);
            }
        }
    }

    public void update() {

        for (int i = 0; i < flame.size(); i++) {
            flame.get(i).update();
        }


        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).update();
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }
        bomberman.update();
        for (int i = 0; i < block.size(); i++) {
            block.get(i).update();
        }
        handleCollisions();
        collisionFlame();
        updateSound();


    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //grasses.forEach(g->g.render(gc));
        block.sort(new Layer());
        for (int i = block.size() - 1; i >= 0; i--) {
            block.get(i).render(gc);
        }


        bombs.forEach(g -> g.render(gc));
        flame.forEach(g -> g.render(gc));

        enemies.forEach(g -> g.render(gc));
        bomberman.render(gc);


    }

    public void handleCollisions() {
        Rectangle r1 = bomberman.getBounds();

        for (Entity block : block) {
            Rectangle r2 = block.getBounds();
            if (r2.intersects(r1)) {
                if (bomberman.getLayer() >= block.getLayer()) {
                    if (block instanceof Item) {
                        EntityList.block.remove(block);
                        if (block instanceof BombItem) {
                            start1 = System.currentTimeMillis();
                            bomberman.setCountBomb(bomberman.getCountBomb() + 1);
                        }
                        if (block instanceof FlameItem) {
                            bomberman.setRadius(2);
                            start2 = System.currentTimeMillis();
                        }
                        if (block instanceof WallItem) {
                            bomberman.setLayer(4);
                            start3 = System.currentTimeMillis();
                        }
                        if (block instanceof SpeedItem) {
                            bomberman.setSpeed(4);
                            start4 = System.currentTimeMillis();

                        }
                        if (block instanceof FlamePass) {
                            flamePass = true;
                            start5 = System.currentTimeMillis();
                        }
                    }
                    if (block instanceof Portal && enemies.size() == 0) {
                        createMapLevel(++level);
                        menu.level.setText("Level: " + level);
                        time_number += 30;
                        bomberman.setAlive(false);
                        new SoundPlay("sound/level_complete.wav", "default");
                    }
                    Run = true;
                } else {
                    Run = false;
                }
                for (Bomb bomb : bombs) {
                    Rectangle r3 = bomb.getBounds();
                    if (bomb.pass(bomberman) && r1.intersects(r3)) {
                        Run = false;
                        break;
                    }
                }
                if (Run) bomberman.move();
                else bomberman.stay();
                break;
            }

            if (!r2.intersects(r1) && block instanceof Grass) {
                if (bomberman.getLayer() > 1) {

                    elapsedTimeMillis3 = System.currentTimeMillis() - start3;
                    if (elapsedTimeMillis3 > 10000) {
                        bomberman.setLayer(1);
                    }
                }
            }
        }
        for (Enemy enemy : enemies) {
            Rectangle Re = enemy.getBounds();
            for (Bomb bomb : bombs) {
                Rectangle r = bomb.getBounds();
                if (bomb.pass(enemy) && Re.intersects(r)) {
                    enemy.stay();
                    break;
                }
            }
        }
        for (Enemy e : enemies) {
            Rectangle Rec1 = e.getBounds();
            for (Entity block : block) {
                Rectangle r2 = block.getBounds();
                if (r2.intersects(Rec1)) {
                    if (e.getLayer() >= block.getLayer()) {
                        e.move();
                    } else e.stay();
                    break;
                }
            }
        }


        for (Enemy e : enemies) {
            Rectangle Re = e.getBounds();
            if (Re.intersects(r1)) {
                bomberman.setAlive(false);
            }
        }
        if (bomberman.getCountBomb() > 1) { //thoi gian su dung BombItem
            elapsedTimeMillis1 = System.currentTimeMillis() - start1;
            if (elapsedTimeMillis1 > 10000) {
                bomberman.setCountBomb(1);
            }
        }
        if (bomberman.getRadius() > 1) { //thoi gian su dung FlameItem
            elapsedTimeMillis12 = System.currentTimeMillis() - start2;
            if (elapsedTimeMillis12 > 10000) {
                bomberman.setRadius(1);
            }
        }
        if (bomberman.getSpeed() > 2) {
            elapsedTimeMillis4 = System.currentTimeMillis() - start4;
            if (elapsedTimeMillis4 > 10000) {
                bomberman.setSpeed(2);
            }
        }
        if (flamePass) {
            elapsedTimeMillis5 = System.currentTimeMillis() - start5;
            if (elapsedTimeMillis5 > 10000) {
                flamePass = false;
            }
        }


    }

    public void collisionFlame() {
        for (Flame flame : flame) {
            Rectangle r1 = flame.getBounds();
            Rectangle rec = bomberman.getBounds();
            for (Enemy enemy : enemies) {
                Rectangle r2 = enemy.getBounds();
                if (r1.intersects(r2)) {
                    enemy.setAlive(false);
                    new SoundPlay("sound/dead.wav", "died");
                }
            }
            for (Entity b : block) {
                Rectangle Rb = b.getBounds();
                if (r1.intersects(Rb) && b instanceof Brick) {
                    b.setAlive(false);
                    setGrid(b.getY() / Sprite.SCALED_SIZE, b.getX() / Sprite.SCALED_SIZE, 0);
                }
            }
            for (int i = 1; i < bombs.size(); i++) { //xu ly bom no voi bom khac
                Rectangle Rbom = bombs.get(i).getBounds();
                if (r1.intersects(Rbom)) {
                    bombs.get(i).setAnimate(119);
                }
            }
            if (r1.intersects(rec)) {
                if (!flamePass) {
                    bomberman.setAlive(false);
                }
            }
        }
    }

    public void time() {
        long now = System.currentTimeMillis();
        if (now - last_time > 1000) {
            last_time = System.currentTimeMillis();
            time.setText("Time: " + time_number);
            time_number--;
            if (time_number < 0) {
                gameOver();
            }
        }
    }

}