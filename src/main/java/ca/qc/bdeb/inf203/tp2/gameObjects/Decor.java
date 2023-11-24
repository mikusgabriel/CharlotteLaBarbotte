package ca.qc.bdeb.inf203.tp2.gameObjects;

import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Decor {
    private final Background background = generateBackground();
    private final static int MAP_WIDTH = 4160;

    //Generer un background
    private Background generateBackground() {
        var rand = new Random();
        double hue = rand.nextDouble(190, 270), saturation = 0.84, brightness = 1.0;

        var backgroundFill = new BackgroundFill(Color.hsb(hue, saturation, brightness), null, null);
        var bgFills = new ArrayList<BackgroundFill>();

        bgFills.add(backgroundFill);

        var bgImages = new ArrayList<BackgroundImage>();

        var currentFill = 0;
        while (MAP_WIDTH > currentFill) {
            Image[] images = {
                    new Image("decor1.png"),
                    new Image("decor2.png"),
                    new Image("decor3.png"),
                    new Image("decor4.png"),
                    new Image("decor5.png"),
                    new Image("decor6.png")};
            var image = images[rand.nextInt(0,5)];
            var distanceFromPreviousImage = rand.nextInt(50, 100);
            bgImages.add(
                    new BackgroundImage(
                            image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                            new BackgroundPosition(null,
                                    currentFill + distanceFromPreviousImage,
                                    false,
                                    Side.BOTTOM,
                                    -10,
                                    false),
                            null)
            );
            currentFill += (int) image.getWidth() + distanceFromPreviousImage;
            System.out.println("added 1 new background image!");
            System.out.println(currentFill);
        }
        return new Background(bgFills, bgImages);
    }

    //--------GETTERS--------
    public Background getBackground() {
        return background;
    }

}
