package ro.teamnet.zerotohero.oop.graphicsshape;

/**
 * Created by Alexandru.Negura on 7/4/2017.
 */
public class Immutable {
    private final int x = 1;

    private int getX() {
        return x;
    }

    Exception e = new Exception("Asdasda");

    public void testException(){
        try{
            throw e;
        }catch (Exception excecption1){
            System.out.println(e);
        }
    }

}
