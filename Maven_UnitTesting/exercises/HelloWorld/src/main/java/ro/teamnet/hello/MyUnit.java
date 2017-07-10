package ro.teamnet.hello;

import java.util.Random;

/**
 * Created by Alexandru.Negura on 7/10/2017.
 */
public class MyUnit {
    public String concatenate(String one, String two){
        return one + two;
    }

    public boolean returnTrue(){
        return new Random().nextBoolean();
    }
}
