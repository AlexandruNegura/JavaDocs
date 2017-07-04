package ro.teamnet.zerotohero.oop.graphicsshape;
import java.lang.Math;

/**
 * Created by Alexandru.Negura on 7/4/2017.
 */
public class Circle extends Shape{
    private int xPos;
    private int yPos;
    private int radius;

    public Circle() {
        xPos = 1;
        yPos = 2;
        radius = 20;
    }

    public Circle(int xPos) {
        this.xPos = xPos;
    }

    public Circle(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Circle(int xPos, int yPos, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "center=(" +
                + xPos + ", " +
                + yPos +
                ", and radius=" + radius;
    }

    @Override
    public double area() {
        return (double) Math.PI * radius * radius;
    }

    public void fillColor(){
        System.out.println(super.getColor());
    }

    public void fillColor(int param1){
        super.setColor(param1);
        System.out.println("The circle color is now: " + param1);
    }

    public void fillColor(float param2){
        super.setSaturation(param2);
    }


}
