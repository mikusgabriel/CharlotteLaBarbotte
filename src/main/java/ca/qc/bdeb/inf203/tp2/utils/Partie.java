package ca.qc.bdeb.inf203.tp2.utils;

import ca.qc.bdeb.inf203.tp2.gameObjects.BarreVie;
import ca.qc.bdeb.inf203.tp2.gameObjects.Charlotte;
import ca.qc.bdeb.inf203.tp2.gameObjects.Ennemi;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.sql.SQLOutput;
import java.util.ArrayList;


public class Partie {
    private final Charlotte charlotte;
    private final BarreVie barreVie=new BarreVie();
    private final Canvas canvas;

    private final Camera camera;
    private final ArrayList<Ennemi> ennemis = new ArrayList<>();


    // Constructeur : on crée les objets de la partie
    public Partie(Canvas canvas, Charlotte charlotte,Camera camera) {
        this.charlotte = charlotte;
        this.canvas = canvas;
        this.camera=camera;
        for(int i=0;i<5;i++){
            ennemis.add(new Ennemi(canvas,1));
        }



    }

    public void update(double deltaTemps) {
        charlotte.update(deltaTemps,camera);
        charlotte.isDead();
        for (Ennemi ennemi:ennemis
             ) {
            ennemi.update(deltaTemps,camera);
            ennemi.isDead();
        }


// Tester les collisions
// Autres : vérifie si on a gagné/perdu, ...
    }

    public void draw(GraphicsContext context) {
        for (Ennemi ennemi:ennemis
        ) {
            System.out.println("ennemi print");
            ennemi.draw(context, camera);
        }
        charlotte.draw(context,camera);
        barreVie.update(context,charlotte.getVie());

    }

    //FIXME implementation temporaire pour finir la partie
   public void end(){

    }
}