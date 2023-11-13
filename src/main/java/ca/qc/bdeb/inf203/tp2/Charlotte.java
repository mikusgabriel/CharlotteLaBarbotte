package ca.qc.bdeb.inf203.tp2;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Charlotte extends GameObject{
    private final int a=0;

    private final int vie=10;

    private final int largeur=102;
    private final int hauteur=90;

    private ImageView imageCharlotte= new ImageView("charlotte.png");




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

    @Override
    public boolean isDead() {
        if(vie==0){
            return true;
        }
        return false;

    }
}
