package ca.qc.bdeb.inf203.tp2;

import javafx.scene.canvas.GraphicsContext;

public class Partie {
    private Charlotte charlotte;

    // Constructeur : on crée les objets de la partie
    Partie() {
        charlotte = new Charlotte();

    }

    public void update(double deltaTemps) {
        charlotte.update(deltaTemps);
        charlotte.isDead();

// Tester les collisions
// Autres : vérifie si on a gagné/perdu, ...
    }

    public void draw(GraphicsContext context) {
        charlotte.draw(context);
// Dessiner les objets
    }
}