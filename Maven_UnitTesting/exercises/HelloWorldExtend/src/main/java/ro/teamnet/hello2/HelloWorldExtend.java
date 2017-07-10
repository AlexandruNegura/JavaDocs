package ro.teamnet.hello2;

import ro.teamnet.hello.HelloWorld;

/**
 * Created by Alexandru.Negura on 7/10/2017.
 */
public class HelloWorldExtend {

    public HelloWorldExtend() {
    }

    public void extendSayHello(){
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
        System.out.println("The new Hello World");
    }


}
