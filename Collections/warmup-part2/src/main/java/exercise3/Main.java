package exercise3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexandru.Negura on 7/7/2017.
 */
public class Main {

    public static void main(String[] args) {
        Map<DumbStudent, BigDecimal>  map1 = new HashMap<DumbStudent, BigDecimal>();
        Map<EvenDumberStudent, BigDecimal>  map2 = new HashMap<EvenDumberStudent, BigDecimal>();
        Map<AutismIsHighWIthThisOne, BigDecimal>  map3 = new HashMap<AutismIsHighWIthThisOne, BigDecimal>();
        Map<OutOfNamesClass, BigDecimal>  map4 = new HashMap<OutOfNamesClass, BigDecimal>();


        System.out.println("Test1");

        map1.put(new DumbStudent("Gigel", "Bucur"), new BigDecimal(1));
        map1.put(new DumbStudent("Gigel", "Vasile"), new BigDecimal(1));

        System.out.println("Expected : 2 entries");
        System.out.println("Found");
        System.out.println(map1);

        System.out.println("\n Test2");

        map4.put(new OutOfNamesClass("Gigel", "Bucur"), new BigDecimal(1));
        map4.put(new OutOfNamesClass("Gigel", "Vasile"), new BigDecimal(1));

        System.out.println("Expected : 2 entries");
        System.out.println("Found");
        System.out.println(map4);

        System.out.println("\n Test2");

        map3.put(new AutismIsHighWIthThisOne("Gigel", "Bucur"), new BigDecimal(1));
        map3.put(new AutismIsHighWIthThisOne("Gigel", "Vasile"), new BigDecimal(1));

        System.out.println("Expected : 2 entries");
        System.out.println("Found");
        System.out.println(map3);
    }
}
