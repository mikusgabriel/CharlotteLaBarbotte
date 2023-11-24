package ca.qc.bdeb.inf203.tp2.utils;

import ca.qc.bdeb.inf203.tp2.gameObjects.Charlotte;

public class Camera {
    private double x;

    public double calculerEcranX(double xObjet) {
        System.out.println("xObjet = " + xObjet);
        return xObjet - x - 300;
    }

    public void suivre(Charlotte charlotte) {
        if(charlotte.getDroite() > x) {
            this.x --;
        }
        if(charlotte.getGauche() < 0) {
            this.x ++;
        }
        if(x < 0) this.x = 0;
    }
}
