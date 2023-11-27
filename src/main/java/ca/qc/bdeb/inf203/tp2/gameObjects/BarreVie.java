package ca.qc.bdeb.inf203.tp2.gameObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BarreVie {
    public static final int LARGEUR_BARRE_DE_VIE = 150, HAUTEUR_BARRE_DE_VIE = 30, BORDURE_BARRE_DE_VIE = 2;
    private final Color backgroundColor;
    private double viePourcentage;

    public BarreVie(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void update(int nbVie) {
        viePourcentage = (double) (nbVie) / 4;
    }

    //Dessine la barre de vie blanche au complet ensuite dessine un rectangle couleur du background à l'intérieur
    public void draw(GraphicsContext graphics) {
        double posX = 30 + BORDURE_BARRE_DE_VIE + LARGEUR_BARRE_DE_VIE * viePourcentage,
            posY = 30 + BORDURE_BARRE_DE_VIE,
            width = (LARGEUR_BARRE_DE_VIE - LARGEUR_BARRE_DE_VIE * viePourcentage)-(2* BORDURE_BARRE_DE_VIE),
            height = HAUTEUR_BARRE_DE_VIE - BORDURE_BARRE_DE_VIE * 2;

        graphics.setFill(Color.WHITE);
        graphics.fillRect(30, 30, LARGEUR_BARRE_DE_VIE, HAUTEUR_BARRE_DE_VIE);

        graphics.setFill(backgroundColor);
        graphics.fillRect(posX, posY, width, height);
    }
}
