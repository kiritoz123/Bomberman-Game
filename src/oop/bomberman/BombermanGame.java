package oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import oop.bomberman.Control.menu;
import oop.bomberman.Item.*;
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
import java.util.Random;

import static oop.bomberman.entities.EntityList.*;
import static oop.bomberman.graphics.CreateMap.createMapLevel;


public class BombermanGame extends Application {
    //public static int[][] id_objects = new int[15][25];
    public static final int animation = 3;
    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    public static boolean Bright;
    public static boolean Bleft;
    public static Bomber bomberman;
    public static boolean Run = true;
    public static boolean isPause;
    private final List<Entity> entities = new ArrayList<>();
    public List<Entity> Obj = new ArrayList<>();
    private long start1;
    private long start2;
    private long start3;
    private long start4;
    private long start5;
    private int level = 1;
    private long elapsedTimeMillis1;
    private long elapsedTimeMillis12;
    private long elapsedTimeMillis3;
    private long elapsedTimeMillis4;
    private long elapsedTimeMillis5;
    private boolean flamePass = false;
    private GraphicsContext gc;
    private Canvas canvas;

    public static void main(String[] args) {

        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage Stage) throws Exception {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        canvas.setTranslateY(32);
        gc = canvas.getGraphicsContext2D();
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        menu.createMenu(root);
        //Pane pane = createBackground();


        Scene scene = new Scene(root);


        //Scene scene = new Scene(root);


        Stage.setScene(scene);
        Stage.setTitle("BomberGame");
        Stage.show();

        AnimationTimer time = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                if (isPause) {
                    update();
                    //time();

                }
            }
        };
        time.start();
        createMapLevel(level);
        //createMap();

        scene.setOnKeyPressed(event -> {
            bomberman.handleKeyPressedEvent(event.getCode());
        });
        bomberman = new Bomber(1, 1, Sprite.player2_right.getFxImage());

        //enemies.add(new Oneal(2,2,Sprite.oneal_dead.getFxImage()));
        scene.setOnKeyReleased(event -> bomberman.handleKeyReleasedEvent(event.getCode()));
    }

    //entities.add(bomberman);


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
        //bomberman.render(gc);
        //entities.forEach(Entity::update);
        //block.forEach(block::update);

        //CreateMap.update();
        for (int i = 0; i < flame.size(); i++) {
            flame.get(i).update();
        }

        bomberman.update();
        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).update();
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }
        for (int i = 0; i < block.size(); i++) {
            block.get(i).update();
        }
        handleCollisions();
        collisionFlame();

        /*for (int i = 0; i < flame.size(); i ++) {
            flame.get(i).update();
        }
        bomberman.update();
        handleCollisions();
        for(int i=0; i<bombs.size(); i++) {
            bombs.get(i).update();
        }
        /*CreateMap.update();
        Bright = false;
        Bleft = false;*/

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //grasses.forEach(g->g.render(gc));
        block.sort(new Layer());
        for (int i = block.size() - 1; i >= 0; i--) {
            block.get(i).render(gc);
        }
        //walls.forEach(g->g.render(gc));
        //grasses.forEach(g->g.render(gc));
        //bricks.forEach(g->g.render(gc));

        bombs.forEach(g -> g.render(gc));
        flame.forEach(g -> g.render(gc));

        enemies.forEach(g -> g.render(gc));
        bomberman.render(gc);


    }

    public void handleCollisions() {
        Rectangle r1 = bomberman.getBounds();
        //Bomber vs StillObjects
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
                            bomberman.setLayer(5);
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
                    if (block instanceof Portal) {
                        level++;
                    }
                    bomberman.move();
                } else {
                    bomberman.stay();
                }
                break;
            }
            for (Enemy e : enemies) {
                Rectangle Rec1 = e.getBounds();
                if (r2.intersects(Rec1)) {
                    if (e.getLayer() >= block.getLayer()) {
                        e.move();
                    } else e.stay();
                    break;
                }
                if (Rec1.intersects(r1)) {
                    bomberman.setAlive(false);
                }
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

                }
            }
            for (Entity b : block) {
                Rectangle Rb = b.getBounds();
                if (r1.intersects(Rb) && b instanceof Brick) {
                    Random random = new Random();
                    int rand = random.nextInt(3);
                    b.setAlive(false);
                }
            }
            for (int i = 1; i < bombs.size(); i++) { //xu ly bom no voi bom khac
                Rectangle Rbom = bombs.get(i).getBounds();
                if (r1.intersects(Rbom)) {
                    bombs.get(i).setAnimate(119);
                }
            }
            if (r1.intersects(rec)) {
                if (!flamePass) bomberman.setAlive(false);
            }
        }
    }


}