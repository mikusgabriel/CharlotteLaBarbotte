package ca.qc.bdeb.inf203.tp2;


import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.Random;

public class PageJeu {

    private final VBox pageJeu = new VBox();
    Charlotte charlotte=new Charlotte();



    private Scene sceneJeu = new Scene(pageJeu, 900, 520);

    Canvas canvas=new Canvas(sceneJeu.getWidth(),sceneJeu.getHeight());

    PageJeu(){
        GraphicsContext context= canvas.getGraphicsContext2D();
        Partie partie = new Partie();
        var background = generateRandomBackground();

        var timer = new AnimationTimer() {
            long lastTime = System.nanoTime();
            @Override
            public void handle(long now) {
                double deltaTemps = (now - lastTime) * 1e-9;
                partie.update(deltaTemps);
                context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                partie.draw(context);
                lastTime = now;
            }
        };
        timer.start();
        pageJeu.getChildren().add(canvas);

        pageJeu.setBackground(background);
    }

    private Background generateRandomBackground() {
        Image[] images = {new Image("decor1.png"),
            new Image("decor2.png"),
            new Image("decor3.png"),
            new Image("decor4.png"),
            new Image("decor5.png"),
            new Image("decor6.png")};

        var rand = new Random();
        double hue = rand.nextDouble(190, 270), saturation = 0.84, brightness = 1.0;

        var backgroundFill = new BackgroundFill(Color.hsb(hue, saturation, brightness), null, null);

        var background = new Background(new BackgroundImage(images[0],
              BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, new BackgroundPosition(
                      null, rand.nextInt(50, 100), false, Side.BOTTOM,
                0, false),
               null), new BackgroundImage(images[1],
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, new BackgroundPosition(
                null, rand.nextInt(100, 150), false, Side.BOTTOM,
                0, false),
                null));





//        background.getImages().set(0, new BackgroundImage(images[0],
//                null, null, new BackgroundPosition(
//                        null, rand.nextInt(50, 100), false, Side.BOTTOM,
//                        0, false),
//                null));

        return background;
    }

    //--------GETTERS--------
    public Scene getSceneJeu() {
        return sceneJeu;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
