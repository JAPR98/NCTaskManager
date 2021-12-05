package mx.edu.j2se.perez.evaluation;

public class Circle {
    private int radius;

    public Circle() {
        this.radius = 1;
    }

    public Circle(int radius) throws IllegalArgumentException{
        if (radius > 0) {
            this.radius = radius;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setRadius(int radius) throws  IllegalArgumentException{
        if (radius > 0) {
            this.radius = radius;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getRadius() {
        return this.radius;
    }

    public double getArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }
}
