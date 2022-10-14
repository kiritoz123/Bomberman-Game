package oop.bomberman.Control;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import oop.bomberman.level.FileLevel;

import static oop.bomberman.BombermanGame.*;

public class menu {
    public static ImageView statusGame, author_view;
    public static Text level, bomb, time;
    public static int bomb_number = 20;
    public static Image pauseGame, playGame;

    public static void createMenu(Group root) throws InterruptedException { //Create a menu
        level = new Text("Level: 1");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.WHITE);
        level.setX(416);
        level.setY(20);
        bomb = new Text("Bombs: 20");
        bomb.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        bomb.setFill(Color.WHITE);
        bomb.setX(512);
        bomb.setY(20);
        time = new Text("Times: 120");
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        time.setFill(Color.WHITE);
        time.setX(608);
        time.setY(20);

        Image newGame = new Image("images/startButton.png");
        statusGame = new ImageView(newGame);
        statusGame.setX(-75);
        statusGame.setY(-10);
        statusGame.setScaleX(0.5);
        statusGame.setScaleY(0.5);

        Image author = new Image("images/author.png");
        author_view = new ImageView(author);
        author_view.setX(-400);
        author_view.setY(-208);
        author_view.setScaleX(0.5);
        author_view.setScaleY(0.5);

        Pane pane = new Pane();
        pane.getChildren().addAll(level, bomb, time, statusGame, author_view);
        pane.setMinSize(800, 32);
        pane.setMaxSize(800, 480);
        pane.setStyle("-fx-background-color: #353535");

        root.getChildren().add(pane);
        root.getChildren().add(author_view);

        playGame = new Image("images/pauseButton.png");
        pauseGame = new Image("images/resumeButton.png");

        statusGame.setOnMouseClicked(event -> {
            root.getChildren().remove(author_view);
            if (bomberman.isAlive()) {
                isPause = !isPause;
            } else {
                new FileLevel();
                Run = true;
            }
            updateMenu();
        });
    }

    public static void updateMenu() { //Update menu
        level.setText("Level: "  );
        bomb.setText("Bombs: " + bomb_number);

        if (bomberman.isAlive())
            if (!isPause) {
                statusGame.setImage(pauseGame);
            } else {
                statusGame.setImage(playGame);
            }
        else {
            Image newGame = new Image("images/startButton.png");
            statusGame.setImage(newGame);
        }
    }
}




