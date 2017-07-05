package exercise.exercise0;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Create a List (ArrayList or LinkedList), add elements to it and print all of them using ListIterator
 *             for loop and foreach loop.
 *
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughList(){

        // TODO Exercise #0 a) Create a list (ArrayList or LinkedList) and add elements to it
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        integerArrayList.add(1);
        integerArrayList.add(1);
        integerArrayList.add(1);
        integerArrayList.add(1);
        // TODO Exercise #0 a) Don't forget to specify the type of the list (Integer, String etc.)
        // TODO Exercise #0 b) Iterate through the list using ListIterator and print all its elements

        Iterator<Integer> integerIterator = integerArrayList.listIterator();
        while(integerIterator.hasNext()){
            Integer integer = integerIterator.next();
            System.out.println(integer);
        }
        // TODO Exercise #0 c) Iterate through the list using classic for loop and print all its elements
        for(int i = 0; i < integerArrayList.size(); i++){
            System.out.println(integerArrayList.get(i));
        }

        // TODO Exercise #0 d) Iterate through the list using foreach loop and print all its elements
        for(Integer integer : integerArrayList){
            System.out.println(integer);
        }

    }

    public static void main(String[] args) {
        // TODO Exercise #0 e) Create a new instance of Exercise0 class and call the iterateThroughList() method
        Exercise0 exercise0 = new Exercise0();
        exercise0.iterateThroughList();
    }
}
