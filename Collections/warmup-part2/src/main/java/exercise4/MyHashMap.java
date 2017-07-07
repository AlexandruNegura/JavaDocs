package exercise4;

import java.util.*;

/**
 * Exercise 3. Implement a HashMap from scratch. In order to pass all the tests
 * you need to implement all the methods defined below. The key-value pair will
 * be encapsulated in the MyHashMap.MyEntry object defined below.
 *
 * The buckets list in which each MyEntry object will be stored is stored in "buckets" object.
 * The hash function that you will use in order to store a pair in a specific bucket will be
 * the one presented earlier: (hashcode value) modulo (bucket array size)
 */
public class MyHashMap {

    private ArrayList<LinkedList<MyEntry>> buckets;

    private final int BUCKET_ARRAY_SIZE = 16;

    int size;

    public MyHashMap() {
        buckets = new ArrayList<LinkedList<MyEntry>>(BUCKET_ARRAY_SIZE);
        for(int i = 0 ; i < BUCKET_ARRAY_SIZE; i++){
            buckets.add(new LinkedList<MyEntry>());
        }

        size = 0;
        //  Initialize buckets list
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
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
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
        for(LinkedList<MyEntry> entries : buckets){
            for(MyEntry myEntry : entries){
                if(myEntry.getKey().equals(key)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(String value) {
        for(LinkedList<MyEntry> entries : buckets){
            for(MyEntry myEntry : entries){
                if(myEntry.getValue().equals(value)){
                    return true;
                }
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for(LinkedList<MyEntry> entries : buckets){
            entries.clear();
        }
        size = 0;
    }

    public Set<MyEntry> entrySet() {
        Set<MyEntry> myEntries = new HashSet<MyEntry>();

        for(LinkedList<MyEntry> entries : buckets){
            myEntries.addAll(entries);
        }

        return myEntries;
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
