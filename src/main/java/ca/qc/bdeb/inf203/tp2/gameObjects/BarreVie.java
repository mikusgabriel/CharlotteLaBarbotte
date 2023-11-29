package ca.qc.bdeb.inf203.tp2.gameObjects;

import ca.qc.bdeb.inf203.tp2.gameObjects.projectiles.Projectile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Montre le nombre de points de vie de Charlotte et
 * montre le type de projectile qu'elle peut tirer.
 */
public class BarreVie extends GameObject {
    public static final int LARGEUR_BARRE_DE_VIE = 150,
            HAUTEUR_BARRE_DE_VIE = 30,
            BORDURE_BARRE_DE_VIE = 2,
            DISTANCE_BORD = 30;
    private Color backgroundColor;
    private double viePourcentage, posXicone;

    public BarreVie(Color backgroundColor) {
        x = DISTANCE_BORD + BORDURE_BARRE_DE_VIE + LARGEUR_BARRE_DE_VIE * viePourcentage;
        y = DISTANCE_BORD + BORDURE_BARRE_DE_VIE;
        hauteur = HAUTEUR_BARRE_DE_VIE - BORDURE_BARRE_DE_VIE * 2;
        this.backgroundColor = backgroundColor;
    }

    public void update(int nbVie) {
        viePourcentage = (double) (nbVie) / 4;
        largeur = (LARGEUR_BARRE_DE_VIE - LARGEUR_BARRE_DE_VIE * viePourcentage)-(2* BORDURE_BARRE_DE_VIE);

        //update l'icône du projectile
        posXicone = DISTANCE_BORD + BORDURE_BARRE_DE_VIE + LARGEUR_BARRE_DE_VIE + 10;
    }


    public void draw(GraphicsContext graphics, Projectile projectile) {
        //Dessine la barre de vie blanche au complet
        graphics.setFill(Color.WHITE);
        graphics.fillRect(DISTANCE_BORD, DISTANCE_BORD, LARGEUR_BARRE_DE_VIE, HAUTEUR_BARRE_DE_VIE);

        // ensuite dessine un rectangle couleur du
        // background à l'intérieur
        graphics.setFill(backgroundColor);
        graphics.fillRect(x, y, largeur, hauteur);

        // Dessine l'image du projectile
        graphics.drawImage(projectile.getImage(),posXicone, DISTANCE_BORD);
    }

    //--------SETTERS--------
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
