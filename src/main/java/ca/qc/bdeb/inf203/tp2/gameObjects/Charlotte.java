package ca.qc.bdeb.inf203.tp2.gameObjects;

import ca.qc.bdeb.inf203.tp2.gameObjects.GameObject;
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
        //call update du super qui call update physique dans Gameobject
        super.update(deltaTemps);
        boolean gauche = Input.isKeyPressed(KeyCode.A);
        boolean droite = Input.isKeyPressed(KeyCode.D);
        boolean haut = Input.isKeyPressed(KeyCode.W);
        boolean bas = Input.isKeyPressed(KeyCode.S);

        image = new Image("charlotte.png");

        //Mouvement de Charlotte
        if(gauche){
            System.out.println("gauche");
            ax=-1000;

        }if(droite){
            System.out.println("droite");
            image = new Image("charlotte-avant.png");
            ax=1000;


        }if(haut){
            System.out.println("haut");
            ay=-1000;

        }if(bas) {
            System.out.println("bas");
            ay=1000;

        }


        //Collision avec le bas
        if(image.getHeight() + y >= canvas.getHeight()) {
            y = canvas.getHeight() - image.getHeight() - 1;
            System.out.println("colliding with bottom");
        }
        //Collision avec le haut
        if(y <= 0) {
            y = 1;
            System.out.println("colliding with top");
        }
        //Collision avec la gauche
        if(x <= 0) {
            x = 1;
            System.out.println("colliding with left");
        }

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
    public void draw(GraphicsContext graphics) {
        super.draw(graphics);
    }

    //--------GETTERS--------
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getGauche(){ return x; }
    public double getDroite(){ return x+ image.getWidth();}

    //--------SETTERS--------
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
}
