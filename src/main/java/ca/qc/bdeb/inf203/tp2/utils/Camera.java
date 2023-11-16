package ca.qc.bdeb.inf203.tp2.utils;

import ca.qc.bdeb.inf203.tp2.gameObjects.Charlotte;

public class Camera {
    private double x;

    public void suivre(Charlotte charlotte) {
        if(charlotte.getGauche() < 0) {
// Quand le personnage est trop à gauche dans l'écran,
// on ajuste la caméra
            this.x = 100;
        }
        if(charlotte.getDroite() > 500) {
// Même chose si le personnage est trop à droite,
// on déplace la caméra pour garder le personnage
// visible dans l'écran
            this.x = 50;
        }
    }

    public double calculerEcranX(double xMonde) {
        return xMonde - x;
    }
}
