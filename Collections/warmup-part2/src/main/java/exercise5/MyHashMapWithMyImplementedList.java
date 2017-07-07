package exercise5;

import exercise4.MyHashMap;

import java.util.*;

/**
 * Create a HashMap that uses to store the buckets your implementation of MyImplementedList that you
 * created in the Collections I workshop.
 * <p>
 * Created by Radu.Hoaghe on 7/6/2017.
 */
public class MyHashMapWithMyImplementedList {


    //  uncomment the following line and add your MyImplementedList implementation to the project
    private MyImplementedList<LinkedList<MyEntry>> buckets;

    private final int BUCKET_ARRAY_SIZE = 16;

    int size;

    public MyHashMapWithMyImplementedList() {
        buckets = new MyImplementedList<LinkedList<MyEntry>>();
        for (int i = 0; i < BUCKET_ARRAY_SIZE; i++) {
            buckets.add(new LinkedList<MyEntry>());
        }

        size = 0;
    }

    public String get(String key) {
        if(key == null) {
            return null;
        }

        int hashCode = Math.abs(key.hashCode()) % BUCKET_ARRAY_SIZE;
        System.out.println(hashCode);

        if(BUCKET_ARRAY_SIZE < hashCode ){
            return null;
        }

        LinkedList<MyEntry> myEntries = buckets.get(hashCode);
        for(MyEntry entry : myEntries){
            if(entry.getKey().equals(key)){
                return entry.getValue();
            }
        }
        return null;
    }

    public void put(String key, String value) {
        if(key == null){
            buckets.get(0).add(new MyEntry(key, value));
            size++;
            return;
        }

        int hash = Math.abs(key.hashCode()) % BUCKET_ARRAY_SIZE;

        if(BUCKET_ARRAY_SIZE < hash ){
            return;
        }

        LinkedList<MyEntry> myEntries = buckets.get(hash);
        for(MyEntry myEntry : myEntries){
            if(key.equals(myEntry.getKey())){
                myEntry.setValue(value);
                return;
            }
        }

        myEntries.add(new MyEntry(key, value));
        size++;
    }

    public Set<String> keySet() {
        Set<String> keySet = new HashSet<String>();

        for(LinkedList<MyEntry> entries : buckets){
            for(MyEntry myEntry : entries){
                keySet.add(myEntry.getKey());
            }
        }

        return keySet;
    }

    public Collection<String> values() {
        Collection<String> values = new LinkedList<String>();

        for(LinkedList<MyEntry> entries : buckets){
            for(MyEntry myEntry : entries){
                values.add(myEntry.getValue());
            }
        }

        return values;
    }

    public String remove(String key) {
        //  Returns the value associated with the key removed from the map or null if the key wasn't found
        for(LinkedList<MyEntry> entries : buckets){
            for(MyEntry myEntry : entries){
                if(myEntry.getKey().equals(key)){
                    String value = myEntry.getValue();
                    entries.remove(myEntry);
                    size--;
                    return value;
                }
            }
        }

        return null;
    }

    public boolean containsKey(String key) {
        for (LinkedList<MyEntry> entries : buckets) {
            for(MyEntry entry : entries){
                if(entry.getKey().equals(key)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean containsValue(String value) {
        for (LinkedList<MyEntry> entries : buckets) {
            for(MyEntry entry : entries){
                if(entry.getValue().equals(value)){
                    return true;
                }
            }
        }

        return false;
    }

    public int size() {
        //  Return the number of the Entry objects stored in all the buckets
        return size;
    }

    public void clear() {
        //  Remove all the Entry objects from the bucket list
        for (LinkedList<MyEntry> entries : buckets) {
            entries.clear();
        }
        size = 0;
    }

    public Set<MyEntry> entrySet() {
        Set<MyEntry> entrySet = new HashSet<MyEntry>();

        for (LinkedList<MyEntry> entries : buckets) {
            entrySet.addAll(entries);
        }

        return entrySet;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static class MyEntry {
        private String key;
        private String value;

        public MyEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
