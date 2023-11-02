package ca.qc.bdeb.inf203.tp2;

public class Camera {
    private double x,y;

    public void update(double deltaTemps) {
        double vitesseDefilementX = 50;
            // La caméra bouge vers la droite automatiquement

    }

    public void suivre(Charlotte charlotte) {
        if(personnage.getGauche() < ...) {
// Quand le personnage est trop à gauche dans l'écran,
// on ajuste la caméra
            this.x = ...
        }
        if(personnage.getDroite() > ...) {
// Même chose si le personnage est trop à droite,
// on déplace la caméra pour garder le personnage
// visible dans l'écran
            this.x = ...
        }
    }



    public double calculerEcranX(double xMonde) {
        return xMonde - x;
    }
    public double calculerEcranY(double yMonde) {
        return yMonde - y;
    }
}
