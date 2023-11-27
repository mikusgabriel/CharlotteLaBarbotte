package ca.qc.bdeb.inf203.tp2.utils;

public class Camera {
    private double x;
    private double width;

    public void setX(double x) {
        this.x = x;
    }

    public Camera(double x, double width) {
        this.x = x;
        this.width = width;
    }

    public double getX() {
        return x;
    }

    public double getWidth() {
        return width;
    }

}