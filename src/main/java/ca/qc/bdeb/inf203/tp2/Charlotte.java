package ca.qc.bdeb.inf203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class Charlotte extends GameObject{
    public Charlotte() {
        super(0, 260, 90, 102);
        image=new Image("charlotte.png");
    }
    private int vie=4;

    private final int largeur=102;
    private final int hauteur=90;




    private ArrayList<KeyCode> directions = new ArrayList<>();

    public void update(double deltaTemps){

        boolean gauche = Input.isKeyPressed(KeyCode.LEFT);
        boolean droite = Input.isKeyPressed(KeyCode.RIGHT);
        boolean haut = Input.isKeyPressed(KeyCode.RIGHT);
        boolean bas = Input.isKeyPressed(KeyCode.RIGHT);

        if(gauche){

        }else if(droite){

        }else if(haut){

        }else if(bas){

        }



    }
    public void perdreVie(){
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
}
