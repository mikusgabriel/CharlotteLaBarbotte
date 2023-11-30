package ca.qc.bdeb.inf203.tp2.gameObjects;

import ca.qc.bdeb.inf203.tp2.gameObjects.projectiles.BoiteSardine;
import ca.qc.bdeb.inf203.tp2.gameObjects.projectiles.EtoileDeMer;
import ca.qc.bdeb.inf203.tp2.gameObjects.projectiles.Hippocampe;
import ca.qc.bdeb.inf203.tp2.gameObjects.projectiles.Projectile;
import ca.qc.bdeb.inf203.tp2.gui.Fenetre;
import ca.qc.bdeb.inf203.tp2.utils.Camera;
import ca.qc.bdeb.inf203.tp2.utils.Partie;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class Baril extends GameObject {

    private final double HAUTEUR=83,LARGEUR=70;
    private final Random ran=new Random();

    private double periode;
    private boolean canBeTouch=true;
    private double counter;

    public Baril(){
        image=new Image("baril.png");
        hauteur=HAUTEUR;
        largeur=LARGEUR;
        //x choisit au hasard entre 1/5 et 4/5
        x=(((double) ran.nextInt(2, 8) /10)* Partie.LONGUEUR_MONDE);
        periode=3;
    }
    @Override
    public void update(double deltaTemps, Camera camera){
        super.update(deltaTemps,camera);
        counter += deltaTemps;

        y = (((Fenetre.HAUTEUR-hauteur)/2) * Math.sin((2 * Math.PI*counter)/periode))+((Fenetre.HAUTEUR-hauteur)/2);
    }

    public void isTouch(Shooter shooter){
        if(canBeTouch){
            image=new Image("baril-ouvert.png");
            ArrayList<Projectile> projectilesList=new ArrayList<>();
            projectilesList.add(new Hippocampe(shooter.getX(),shooter.getY()));
            projectilesList.add(new EtoileDeMer(shooter.getX(),shooter.getY()));
            projectilesList.add(new BoiteSardine(shooter.getX(),shooter.getY()));
            boolean isChoisit=false;


            do{

                Projectile projectileChoisit=projectilesList.get(ran.nextInt(0, projectilesList.size()));
                if(projectileChoisit.getClass()!=shooter.getProjectile().getClass()){
                    shooter.setProjectile(projectileChoisit);
                    isChoisit=true;
                }
            }while(!isChoisit);

            canBeTouch=false;
        }
    }

}

