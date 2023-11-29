package ca.qc.bdeb.inf203.tp2.gameObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

public class BarreVie {
    public static final int LARGEUR_BARRE_DE_VIE = 150, HAUTEUR_BARRE_DE_VIE = 30, BORDURE_BARRE_DE_VIE = 2, DISTANCE_BORD=30;
    private final Color backgroundColor;
    private double viePourcentage;

    public BarreVie(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void update(int nbVie) {
        viePourcentage = (double) (nbVie) / 4;
    }

    //Dessine la barre de vie blanche au complet ensuite dessine un rectangle couleur du background à l'intérieur
    public void draw(GraphicsContext graphics, Projectile projectile) {
        double posX = DISTANCE_BORD + BORDURE_BARRE_DE_VIE + LARGEUR_BARRE_DE_VIE * viePourcentage,
            posY = DISTANCE_BORD + BORDURE_BARRE_DE_VIE,
            width = (LARGEUR_BARRE_DE_VIE - LARGEUR_BARRE_DE_VIE * viePourcentage)-(2* BORDURE_BARRE_DE_VIE),
            height = HAUTEUR_BARRE_DE_VIE - BORDURE_BARRE_DE_VIE * 2;

        graphics.setFill(Color.WHITE);
        graphics.fillRect(DISTANCE_BORD, DISTANCE_BORD, LARGEUR_BARRE_DE_VIE, HAUTEUR_BARRE_DE_VIE);

        graphics.setFill(backgroundColor);
        graphics.fillRect(posX, posY, width, height);


        //update l'icone du projectile
        double posXicone=DISTANCE_BORD+BORDURE_BARRE_DE_VIE+LARGEUR_BARRE_DE_VIE+10;
        double posYicone=DISTANCE_BORD+BORDURE_BARRE_DE_VIE+HAUTEUR_BARRE_DE_VIE;
        graphics.drawImage(projectile.getImage(),posXicone,posYicone);
    }
}
