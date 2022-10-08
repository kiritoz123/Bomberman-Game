package oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import oop.bomberman.entities.Entity;
import oop.bomberman.entities.block.Grass;
import oop.bomberman.entities.block.Wall;
import oop.bomberman.entities.character.Bomber;
import oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static oop.bomberman.entities.EntityList.block;
import static oop.bomberman.graphics.CreateMap.createMapLevel;


public class BombermanGame extends Application {
    //public static int[][] id_objects = new int[15][25];
    public static final int animation = 3;
    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    public static boolean right;
    public static boolean left;
    public static Bomber bomberman;
    public static boolean Run;
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
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        //Pane pane = createBackground();


        Scene scene = new Scene(root);


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
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

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
        entities.forEach(Entity::update);
        //block.forEach(block::update);

        //CreateMap.update();
        bomberman.update();
        bomberman.move();

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //grasses.forEach(g->g.render(gc));
        //for(int i=0;i<walls.size();i++) {
        // walls.get(i).render(gc);
        //}
        //walls.forEach(g->g.render(gc));
        //grasses.forEach(g->g.render(gc));
        //bricks.forEach(g->g.render(gc));
        block.forEach(g -> g.render(gc));


        bomberman.render(gc);


    }


}
