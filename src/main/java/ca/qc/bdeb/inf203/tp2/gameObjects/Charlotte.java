package ca.qc.bdeb.inf203.tp2.gameObjects;

import ca.qc.bdeb.inf203.tp2.gameObjects.GameObject;
import ca.qc.bdeb.inf203.tp2.utils.Input;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Charlotte extends GameObject {
    private final int PV_MAX = 4;
    public Charlotte() {
        super(0, 260, 90, 102);
        image=new Image("charlotte.png");
        x=0;
        y=260;
    }
    private int vie= PV_MAX;

    private final int largeur=102;
    private final int hauteur=90;

    public void update(double deltaTemps){
        boolean gauche = Input.isKeyPressed(KeyCode.A);
        boolean droite = Input.isKeyPressed(KeyCode.D);
        boolean haut = Input.isKeyPressed(KeyCode.W);
        boolean bas = Input.isKeyPressed(KeyCode.S);


        if(gauche){
            System.out.println("gauche");
            x--;

        }else if(droite){
            System.out.println("droite");
            x++;


        }else if(haut){
            System.out.println("haut");
            y--;

        }else if(bas){
            System.out.println("bas");
            y++;

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
