package ca.qc.bdeb.inf203.tp2.utils;

public class Camera {
    private double x;
    private final double width;

    public Camera(double x, double width) {
        this.x = x;
        this.width = width;
    }

    //--------GETTERS--------
    public double getX() {
        return x;
    }
    public double getWidth() {
        return width;
    }

    //--------SETTERS--------
    public void setX(double x) {
        this.x = x;
    }
}