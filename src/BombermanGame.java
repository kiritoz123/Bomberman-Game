import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import oop.bomberman.Control.Move;
import oop.bomberman.entities.Entity;
import oop.bomberman.entities.block.Grass;
import oop.bomberman.entities.block.Wall;
import oop.bomberman.entities.character.Bomber;
import oop.bomberman.entities.character.character;
import oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    public List<Entity> Obj = new ArrayList<>();
    public static character bomberman;
    private GraphicsContext gc;
    private Canvas canvas;
    private final List<Entity> entities = new ArrayList<>();

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

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            if (true) {
                switch (event.getCode()) {
                    case UP:
                        Move.up(bomberman);
                        break;
                    case DOWN:
                        Move.down(bomberman);
                        break;
                    case RIGHT:
                        Move.right(bomberman);
                        break;
                    case LEFT:
                        Move.left(bomberman);
                        break;

                }
            }
        });
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
        createMap();
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

        //entities.add(bomberman);



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
        //bomberman.render(gc);
        entities.forEach(Entity::update);
        bomberman.setCountRun(bomberman.getCountRun() + 1);
        if (bomberman.getCountRun() == 4) {
            Move.checkRun(bomberman);
            bomberman.setCountRun(0);
        }

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Obj.forEach(g -> g.render(gc));
        //entities.forEach(g -> g.render(gc));
        bomberman.render(gc);
    }
}

