import com.sun.org.apache.xml.internal.utils.StringComparable;
import exercise.exercise4.MyImplementedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;

/**
 * //TODO Uncomment those lines after developing the {@link MyImplementedList} methods.
 * //TODO You should start checking your code by running the specific tests, after developing the add(Object o_O) method.
 *
 * @author Cristian.Dumitru
 * @since 7/3/2017.
 */
public class Exercise4Test {

    private MyImplementedList<String> testingStringValues;
    private MyImplementedList<Integer> testingIntegerValues;

    @Before
    public void beforeMethod() {
        testingStringValues = new MyImplementedList<String>();
        testingIntegerValues = new MyImplementedList<Integer>();

        populateLists();
    }

    /**
     * The add(Object o_O) method should work before executing a test!
     */
    private void populateLists() {
        testingStringValues.add("Testing ");
        testingStringValues.add("the ");
        testingStringValues.add("current ");
        testingStringValues.add("list!");
        testingStringValues.add("Testing ");
        testingStringValues.add("huh?!");

        testingIntegerValues.add(0);
        testingIntegerValues.add(1);
        testingIntegerValues.add(2);
        testingIntegerValues.add(3);
    }

    @Test
    public void testAddAndSizeMethodsOverStringList() {
        Assert.assertTrue(testingStringValues.size() == 6);
    }

    @Test
    public void testAddAndSizeMethodsOverIntegerList() {
        Assert.assertTrue(testingIntegerValues.size() == 4);
    }

    @Test
    public void testRemoveMethodOverStringList() {
        testingStringValues.remove(1);
        Assert.assertTrue(testingStringValues.size() == 5);
    }

    @Test
    public void testIsEmptyMethodOverIntegerList() {
        Iterator<Integer> iterator = testingIntegerValues.iterator();
        while (iterator.hasNext()) {

            Integer integer = iterator.next();
            iterator.remove();
        }

        Assert.assertTrue(testingIntegerValues.isEmpty());
    }

    @Test
    public void testIndexOfMethodOverStringList() {
        Assert.assertTrue(testingStringValues.indexOf("Testing ") == 0);
    }

    @Test
    public void testLastIndexOfMethodOverStringList() {
        Assert.assertTrue(testingStringValues.lastIndexOf("Testing ") == 4);
    }

    @Test
    public void testGetMethodOverIntegerList() {
        Assert.assertTrue(testingIntegerValues.get(1).compareTo(1) == 0);
    }

    @Test
    public void testSetMethodOverStringList() {
        testingStringValues.set(1, "this ");
        Assert.assertTrue(testingStringValues.get(1).equals("this "));
    }

    @Test
    public void testListForOneExtension() {
        for (int i = 4; i < 12; i++) {
            testingIntegerValues.add(i);
        }
        Assert.assertTrue(testingIntegerValues.size() == 12);
    }

    @Test
    public void testListForTwoExtensions() {
        for (int i = 4; i < 32; i++) {
            testingIntegerValues.add(i);
        }
        Assert.assertTrue(testingIntegerValues.size() == 32);
    }

//    @Test
//    public void testForeachMethodsOverStringList() {
//        for (Object obj : testingStringValues) {
//            System.out.println(obj);
//        }
//    }

    @Test
    public void testSortMethodOverStringList() {
        MyImplementedList.WhateverComparator comparator = testingStringValues.new WhateverComparator();
//        testingStringValues.sort(comparator);
        Comparator<String> cmp = new Comparator<String>() {
            public int compare(String o1, String o2) {
                if (o1 != null && o2 != null) {
                    return o1.compareToIgnoreCase(o2);
                } else {
                    return 0;
                }
            }
        };

        testingStringValues.sort(cmp);

        System.out.println("AFISARE COMPARARE");
        for (Object obj : testingStringValues) {
            System.out.println(obj);
        }
    }
}
