package ro.teamnet.zerotohero.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 * in order to resolve the exercises below, you will have to create
 * all the needed clasees, with their members and methods
 * (in ro.teamnet.zerotohero.reflection.demoobjects package)
 */


public class ClassReflectionDemoMain {
    public enum Sample {
        READ,
        WRITE
    }

    public int x;
    private int z;

    public class ASDAD {
        int thing;
    }

    public static void printShit(){
//        System.out.println("Shit");
        float x = Math.signum(new Float(100));
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException, NoSuchFieldException {
        ClassReflectionDemoMain classReflectionDemoMain = new ClassReflectionDemoMain();


        //TODO get the class for a String object, and print it
        Class stringClass = "string".getClass();
        System.out.println(stringClass.getName());


        //TODO get the class of an Enum, and print it

        Class enumClass = Sample.READ.getClass();
        System.out.println(enumClass.getName());


        //TODO get the class of a collection, and print it
        List<String> list = new LinkedList<>();
        Class listClass = list.getClass();
        System.out.println(listClass.getName());


        //TODO get the class of a primitive type, and print it
        int x = 3;
        Class primitiveClass = int.class;
        System.out.println(primitiveClass.getName());


        //TODO get and print the class for a field of primitive type
        try {
            Field primitiveFieldClass = classReflectionDemoMain.getClass().getDeclaredField("x");
            System.out.println(primitiveFieldClass.getType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        //TODO get and print the class for a primitive type, using the wrapper class
        Class wrapperClass = Integer.TYPE;
        System.out.println(wrapperClass.getName());

        //TODO get the class for a specified class name
        try {
            Class classClass = Class.forName("ro.teamnet.zerotohero.reflection.ClassReflectionDemoMain");
            System.out.println(classClass.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        //TODO get the superclass of a class, and print it
        //TODO get the superclass of the superclass above, and print it
        Class superClass = ClassReflectionDemoMain.class.getSuperclass();
        System.out.println(superClass);
        Class superSuperClass = ClassReflectionDemoMain.class.getSuperclass().getSuperclass();
        System.out.println(superSuperClass);


        //TODO get and print the declared classes within some other class
        Class[] declaredClasses = classReflectionDemoMain.getClass().getDeclaredClasses();
        for (Class classA : declaredClasses) {
            System.out.println(classA);
        }


        //TODO print the number of constructors of a class
        int declaredConstructors = classReflectionDemoMain.getClass().getDeclaredConstructors().length;
        System.out.println(declaredConstructors);


        //TODO get and invoke a public constructor of a class
        Constructor constructor = classReflectionDemoMain.getClass().getConstructor();
        Object classReflectionDemoMain1 = constructor.newInstance();


        //TODO get and print the class of one private field
        Field privateFieldClass = classReflectionDemoMain.getClass().getDeclaredField("z");
        System.out.println(privateFieldClass.getType());


        //TODO set and print the value of one private field for an object
        privateFieldClass.setAccessible(true);
        privateFieldClass.setInt(classReflectionDemoMain, 3);
        int modifiedValue = classReflectionDemoMain.z;
        System.out.println(modifiedValue);


        //TODO get and print only the public fields class
        Field[] fields = classReflectionDemoMain.getClass().getFields();
        for (Field field : fields) {
            System.out.println(field);
        }


        //TODO get and invoke one public method of a class
        Method method = classReflectionDemoMain.getClass().getMethod("printShit");
        method.invoke(classReflectionDemoMain);

        //TODO get and invoke one inherited method of a class
        Method method1 = classReflectionDemoMain1.getClass().getMethod("toString");
        method1.invoke(new Object());


        //TODO invoke a method of a class the classic way for 100 times, and print the timestamp (System
        System.out.println(System.currentTimeMillis());
        for(int i = 0; i < 10000; i++){
            printShit();
        }
        System.out.println(System.currentTimeMillis());


        //TODO invoke a method of a class by Reflection for 100 times, and print the timestamp
        //what do you observe?
        for(int i = 0; i < 10000; i++){
            method1.invoke(classReflectionDemoMain);
        }
        System.out.println(System.currentTimeMillis());


    }
}
