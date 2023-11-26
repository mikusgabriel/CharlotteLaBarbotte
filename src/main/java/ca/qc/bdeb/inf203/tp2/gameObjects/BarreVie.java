package ca.qc.bdeb.inf203.tp2.gameObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BarreVie {
    private Color backgroundColor;
    private static final int LARGEUR_INITIALE=150, HAUTEUR_INITIALE=30, BORDURE_LARGEUR=2;

    public void update(GraphicsContext graphics,int nbVie) {
        double viePourcentage = (double) (nbVie) / 4;
        drawBarreVie(graphics,viePourcentage);

    }

    //Dessine la barre de vie blanche au complet ensuite dessine un rectangle couleur du background a l'interieur
    private void drawBarreVie(GraphicsContext graphics, double viePourcentage) {
        graphics.setFill(Color.WHITE);
        graphics.fillRect(30, 30, LARGEUR_INITIALE, HAUTEUR_INITIALE);
        graphics.setFill(backgroundColor);
        graphics.fillRect(30+BORDURE_LARGEUR + LARGEUR_INITIALE*viePourcentage, 30 + BORDURE_LARGEUR, (LARGEUR_INITIALE -LARGEUR_INITIALE*viePourcentage)-(2*BORDURE_LARGEUR), HAUTEUR_INITIALE-BORDURE_LARGEUR*2);
    }

    //--------SETTERS--------
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
