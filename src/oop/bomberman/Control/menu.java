package oop.bomberman.Control;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import oop.bomberman.BombermanGame;

import static oop.bomberman.BombermanGame.bomberman;
import static oop.bomberman.BombermanGame.isPause;
import static oop.bomberman.Sound.SoundPlay.is_sound_title;
import static oop.bomberman.Sound.SoundPlay.title_screen;

public class menu {
    public static ImageView statusGame, author_view, startGame;
    public static Text level, time;
    public static int time_number = 120;
    public static Image pauseGame, playGame;

    public static void createMenu(Group root) throws InterruptedException { //Create a menu
        level = new Text();
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.WHITE);
        level.setX(416);
        level.setY(20);

        time = new Text();
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        time.setFill(Color.WHITE);
        time.setX(608);
        time.setY(20);


        Image start = new Image("images/startButton.png");
        startGame = new ImageView(start);
        startGame.setX(150);
        startGame.setY(250);


        statusGame = new ImageView();
        statusGame.setX(-75);
        statusGame.setY(-10);
        statusGame.setScaleX(0.5);
        statusGame.setScaleY(0.5);


        Image author = new Image("images/art2.png");
        author_view = new ImageView(author);
        author_view.setX(-400);
        author_view.setY(-208);
        author_view.setScaleX(0.5);
        author_view.setScaleY(0.5);

        Pane pane = new Pane();
        pane.getChildren().addAll(level, time, statusGame, author_view);
        pane.setMinSize(800, 32);
        pane.setMaxSize(800, 480);
        pane.setStyle("-fx-background-color: #353535");

        root.getChildren().add(pane);


        playGame = new Image("images/pauseButton.png");
        pauseGame = new Image("images/resumeButton.png");


        //statusGame.setImage(pauseGame);
        startGame.setOnMouseClicked(event -> {
            root.getChildren().remove(author_view);
            root.getChildren().remove(startGame);
            statusGame.setImage(playGame);
            isPause = true;
            level.setText("Level: " + BombermanGame.level);

        });
        //statusGame.setImage(pauseGame);
        statusGame.setOnMouseClicked(event -> {
            root.getChildren().remove(author_view);
            if (bomberman.isAlive()) {
                title_screen.stop();
                is_sound_title = false;
                isPause = !isPause;
                updateMenu();
            } else {
                statusGame.setImage(playGame);
                root.getChildren().remove(author_view);
                isPause = true;
                time_number = 120;
            }

        });
    }

    public static void updateMenu() { //Update menu
        //time_number = 120;
        level.setText("Level: " + BombermanGame.level);

        if (bomberman.isAlive()) {
            if (!isPause) {
                statusGame.setImage(pauseGame);
            } else {
                statusGame.setImage(playGame);
            }
        } else {
            isPause = false;
            Image newGame = new Image("images/startButton.png");
            statusGame.setImage(newGame);

        }
    }
}




