package main.java;

import main.kotlin.Interface;
import main.kotlin.KotlinMutableList;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

// extends Class, implements interface

public class Example implements Runnable {

    // experimenting with private, final, public
    final private int value1 = 0;  // always make fields private, final if possible
    private int value2 = value1;
    public void setValue2(int newValue2){
        value2 = newValue2;
    }
    // inside the class can be set and gotten using top level syntax
    // the setValue function and getValue is a workaround for the 'private'
    // can be used inside the class for checking

    // constructor
    final private int value3;
    public Example(int inputValue3){
        value3 = inputValue3;
        int value4 = 0; // is a local variable (cannot be public or private)
    }

    // concurrency
    final private ReentrantLock value2Lock = new ReentrantLock();
    public void run(){
        try{
            value2Lock.lock();
            value2 = 2;
            System.out.println("value 2 set");
        }catch (Exception e){
            throw new RuntimeException("value 2 locked");
        } finally{
            value2Lock.unlock();
        }
    }

    //Mutable variables
    private final Set<Integer> setOfValues = new HashSet();
    public void addToSet(Integer toAdd){
        setOfValues.add(toAdd);
    }
    public void addAllToSet(Integer... toAdds){
        for (int i = 0; i<5; i++){
            // example for counting
        }
        for (Integer toAdd: toAdds){
            setOfValues.add(toAdd);
        }
    }
    public Set<Integer> getSetOfValues(){
        return Collections.unmodifiableSet(setOfValues);
    }

    // streams
    private void streamsExample(){
        final List<Integer> list = new ArrayList<>();
        for (int i= 0; i<5; i++){
            list.add(i);
        }
        final List<Integer> sortedList = list.stream().sorted((a,b) -> Integer.compare(b,a)).toList();
        final List<Integer> sortedList2 = list.stream().sorted(Comparator.reverseOrder()).toList();
        final List<Integer> filteredList = list.stream().filter(it -> it != 1).toList();
    }


}
