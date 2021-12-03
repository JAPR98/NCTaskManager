package mx.edu.j2se.perez.evaluation;

public class Evaluation1 {

    public static int biggestCircle(Circle[] circles) {
        int biggestIndex = 0;
        double biggestArea = circles[0].getArea();
        for (int i = 1; i < circles.length; i++) {
            if (circles[i].getArea() > biggestArea) {
                biggestIndex = i;
                biggestArea = circles[i].getArea();
            }
        }
        return biggestIndex;
    }

    public static void main(String[] args) {
        try {
            Circle circle = new Circle(-1);
        } catch (Exception e) {
            System.out.println("Ooops, you can't create a circle with that radius");
        }
        Circle[] circles = new Circle[] {
                new Circle(5),
                new Circle(4),
                new Circle(3)
        };
        System.out.println(circles[0].getArea());
        System.out.println(circles[1].getArea());
        System.out.println(circles[2].getArea());
        System.out.println(biggestCircle(circles));
    }
}
