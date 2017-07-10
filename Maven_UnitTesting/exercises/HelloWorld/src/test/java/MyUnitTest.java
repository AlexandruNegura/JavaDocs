import org.junit.Test;
import ro.teamnet.hello.MyUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alexandru.Negura on 7/10/2017.
 */
public class MyUnitTest {

    @Test
    public void testConcatenate() {
        MyUnit myUnit = new MyUnit();

        String result = myUnit.concatenate("one", "two");

        assertEquals("onetwo", result);
    }

    @Test
    public void testConcat() {
        MyUnit unit = new MyUnit();

        String result = unit.concatenate("one", "two");

        assertEquals("onetwo", result);
    }

    @Test
    public void testTrue() {
        MyUnit myUnit = new MyUnit();

        boolean truee = myUnit.returnTrue();

        

    }
}
