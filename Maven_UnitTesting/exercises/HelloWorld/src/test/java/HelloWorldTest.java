import junit.framework.TestCase;
import org.junit.Test;
import ro.teamnet.hello.HelloWorld;

/**
 * Created by Alexandru.Negura on 7/10/2017.
 */
public class HelloWorldTest extends TestCase{
    @Test
    public void testSayHello(){
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
    }

    @Test
    public void testReturnHelloKey(){
        HelloWorld helloWorld = new HelloWorld();
        assert helloWorld.returnHelloKey().equals("HelloKey");
        System.out.println("From Test: " + helloWorld.returnHelloKey());
    }

}
