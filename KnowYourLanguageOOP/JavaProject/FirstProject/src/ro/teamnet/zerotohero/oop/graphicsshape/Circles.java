package ro.teamnet.zerotohero.oop.graphicsshape;

/**
 * Created by Alexandru.Negura on 7/4/2017.
 */
public class Circles {

    public double getAreaPub(){
        Circle circle = new Circle();
        return circle.area();
    }

    public void getAreaDef(){
        Circle circle = new Circle();
        circle.fillColor();
        circle.fillColor(2);
        circle.fillColor((float) 1);
    }
}
