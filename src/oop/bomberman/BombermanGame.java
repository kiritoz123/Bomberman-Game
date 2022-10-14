package oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import oop.bomberman.Item.BombItem;
import oop.bomberman.Item.SpeedItem;
import oop.bomberman.entities.Entity;
import oop.bomberman.entities.EntityList;
import oop.bomberman.entities.block.*;
import oop.bomberman.entities.character.Bomber;
import oop.bomberman.entities.character.enemy.Balloom;
import oop.bomberman.entities.character.enemy.Enemy;
import oop.bomberman.entities.character.enemy.Oneal;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.level.Layer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static oop.bomberman.Control.menu.createMenu;
import static oop.bomberman.entities.EntityList.*;
import static oop.bomberman.entities.character.enemy.Balloom.createBalloon;
import static oop.bomberman.graphics.CreateMap.createMapLevel;


public class BombermanGame extends Application {
    //public static int[][] id_objects = new int[15][25];
    public static final int animation = 3;
    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    public static boolean Bright;
    public static boolean Bleft;
    public static Bomber bomberman;
    public static boolean Run;
    public int rand;
    public static boolean isPause;
    private final List<Entity> entities = new ArrayList<>();
    public List<Entity> Obj = new ArrayList<>();
    private GraphicsContext gc;
    private Canvas canvas;

    public static void main(String[] args) {

        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage Stage) throws Exception {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);

        gc = canvas.getGraphicsContext2D();
        canvas.setTranslateY(32);
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        //Pane pane = createBackground();


        Scene scene = new Scene(root);

        createMenu(root);
        //Scene scene = new Scene(root);


        Stage.setScene(scene);
        Stage.setTitle("BomberGame");
        Stage.show();
        System.currentTimeMillis();
        AnimationTimer time = new AnimationTimer() {
            @Override
            public void handle(long l) {

                render();
                update();

            }
        };
        time.start();
        createMapLevel(1);
        //createMap();
        scene.setOnKeyPressed(event -> {
            bomberman.handleKeyPressedEvent(event.getCode());
        });
        bomberman = new Bomber(1, 1, Sprite.player2_right.getFxImage());
        createBalloon();
        enemies.add(new Oneal(2,2,Sprite.oneal_right1.getFxImage()));
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
        for (Bomb bomb : bombs) {
            bomb.update();
        }
        for (Enemy e : enemies) {
            e.update();
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

        enemies.forEach(g -> g.render(gc));
        flame.forEach(g -> g.render(gc));
        bomberman.render(gc);


    }

    public void handleCollisions() {
        Rectangle r1 = bomberman.getBounds();
        //Bomber vs StillObjects

        for (Entity block : block) {
            Rectangle r2 = block.getBounds();
            if (r2.intersects(r1)) {
                if (bomberman.getLayer() >= block.getLayer()) {
                    if(block instanceof Portal) {
                        EntityList.block.remove(block);
                        //EntityList.block.add(new Grass(block.getX()/32, block.getY()/32,Sprite.grass.getFxImage()));

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
                if(Rec1.intersects(r1)) {
                    bomberman.setAlive(false);
                }
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
            if(r1.intersects(rec)) {
                bomberman.setAlive(false);
            }
            for(Entity b : block) {
                Rectangle Rb = b.getBounds();
                if(r1.intersects(Rb) && b instanceof Brick) {
                    Random random = new Random();
                    rand = random.nextInt(4);
                    if(rand == 1) {
                        block.add(new SpeedItem(b.getX() / 32, b.getY() / 32, Sprite.powerup_speed.getFxImage()));

                    }
                    else if(rand == 2) {
                        block.add(new BombItem(b.getX() / 32, b.getY() / 32, Sprite.powerup_bombs.getFxImage()));
                    }
                    else {
                        break;
                    }
                    block.remove(b);
                }
            }
        }
    }


}
