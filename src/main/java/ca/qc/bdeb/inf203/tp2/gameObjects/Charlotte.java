package ca.qc.bdeb.inf203.tp2.gameObjects;

import ca.qc.bdeb.inf203.tp2.gameObjects.GameObject;
import ca.qc.bdeb.inf203.tp2.utils.Camera;
import ca.qc.bdeb.inf203.tp2.utils.Input;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Charlotte extends GameObject {
    private final static int PV_MAX = 4, VX_MAX=300,VY_MAX=300;

    private int vie = PV_MAX, largeur = 102, hauteur = 90;
    private Canvas canvas;

    public Charlotte(Canvas canvas) {
        super(0, 260, 90, 102);
        image=new Image("charlotte.png");
        x=0;
        y=260;
        this.canvas = canvas;
    }
    public void update(double deltaTemps){
        System.out.println(vx);
        System.out.println(vy);
        //call update du super qui call update physique dans Gameobject
        super.update(deltaTemps);
        boolean gauche = Input.isKeyPressed(KeyCode.A);
        boolean droite = Input.isKeyPressed(KeyCode.D);
        boolean haut = Input.isKeyPressed(KeyCode.W);
        boolean bas = Input.isKeyPressed(KeyCode.S);

        image = new Image("charlotte.png");

        //Mouvement de Charlotte translation axe des X
        if(gauche){
            System.out.println("gauche");
            ax=-1000;

        }else if(droite){
            System.out.println("droite");
            image = new Image("charlotte-avant.png");
            ax=1000;


        }else{
            ax = 0;
            vx=ralentir(deltaTemps,vx);

        }

        //Mouvement de Charlotte translation axe des Y
        if(haut){
            System.out.println("haut");
            ay=-1000;

        }else if(bas) {
            System.out.println("bas");
            ay=1000;

        }else{
            ay=0;
            vy=ralentir(deltaTemps,vy);

        }

        vx=vitesseMax(vx);
        vy=vitesseMax(vy);


        //Collision avec le bas
        if(image.getHeight() + y >= canvas.getHeight()) {
            y = canvas.getHeight() - image.getHeight() - 1;
            System.out.println("colliding with bottom");
            vy=0;
        }
        //Collision avec le haut
        if(y <= 0) {
            y = 1;
            vy=0;
            System.out.println("colliding with top");
        }
        //Collision avec la gauche
        if(x <= 0) {
            x = 1;
            vx=0;
            System.out.println("colliding with left");
        }

    }

    //methode utilisee du prof pour faire diminuer la vitesse d'un object
    public double ralentir(double deltaTemps,double v){
    // Quand on relâche : on RALENTI au lieu de stopper
        int signeVitesse = v > 0 ? 1 : -1;
        double vitesseAmortissementX = -signeVitesse * 500;
        v += deltaTemps * vitesseAmortissementX;
        int nouveauSigneVitesse = v > 0 ? 1 : -1;
    // On évite un petit glitch :
    // si le signe change, la vitesse tombe à zéro
        if(nouveauSigneVitesse != signeVitesse) {
            v = 0;
        }
        return v;
    }

    //La vitesse ne peut pas depasser 300
    public double vitesseMax(double v){
        if(v > VX_MAX)
            v = VX_MAX;
        else if(v < -VX_MAX)
            v = -VX_MAX;

        return v;
    }


    public void perdreVie(){
        image= new Image("charlotte-ouch.png");
        vie--;
    }

    @Override
    public boolean isDead() {
        if(vie==0){
            return true;
        }
        return false;

    }

    @Override
    public void draw(GraphicsContext graphics, Camera camera) {
        super.draw(graphics, camera);
    }

    //--------GETTERS--------
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getGauche(){ return x; }
    public double getDroite(){ return x + image.getWidth();}

    //--------SETTERS--------
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
}
