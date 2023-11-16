package ca.qc.bdeb.inf203.tp2.gameObjects;

import ca.qc.bdeb.inf203.tp2.gameObjects.GameObject;
import ca.qc.bdeb.inf203.tp2.utils.Input;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Charlotte extends GameObject {
    private final static int PV_MAX = 4;
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
        boolean gauche = Input.isKeyPressed(KeyCode.A);
        boolean droite = Input.isKeyPressed(KeyCode.D);
        boolean haut = Input.isKeyPressed(KeyCode.W);
        boolean bas = Input.isKeyPressed(KeyCode.S);

        image = new Image("charlotte.png");

        //Mouvement de Charlotte
        if(gauche){
            System.out.println("gauche");
            x-=5;

        }if(droite){
            System.out.println("droite");
            image = new Image("charlotte-avant.png");
            x+=5;


        }if(haut){
            System.out.println("haut");
            y-=5;

        }if(bas) {
            System.out.println("bas");
            y += 5;

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

    //--------SETTERS--------
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
}
