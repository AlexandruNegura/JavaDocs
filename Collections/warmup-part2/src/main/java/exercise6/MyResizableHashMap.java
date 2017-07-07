package exercise6;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Create a resizable generic HashMap. When the number of entries exceeds (load capacity * bucket array size)
 * the HashMap needs to be resized.
 * <p>
 * Resizing (rehashing) consists in increasing the size of the bucket array with the value of
 * INCREASE_SIZE_FACTOR. After this step, all the entries that were stored in the HashMap
 * must be inserted in the new bucket array according to the insertion algorithm in a HashMap:
 * the entry must be placed in a bucket after applying the hash function (hashcode modulo (bucket array size))
 * on the key's hashcode value. The result of the hash function will return the index from the
 * bucket array where the entry will be stored. (see HashMap documentation)
 * <p>
 * Created by Radu.Hoaghe on 7/6/2017.
 */
public class MyResizableHashMap<K, V> {

    /**
     * The array of buckets.
     */
    private Node<K, V>[] buckets;

    /**
     * Default initial capacity for the bucket array.
     */
    private final int DEFAULT_BUCKET_ARRAY_SIZE = 16;

    /**
     * The maximum accepted load property of the data structure.
     */
    private static final double LOAD_FACTOR = 0.75d;

    /**
     * The factor for increasing the size of the data structure.
     */
    private static final int INCREASE_SIZE_FACTOR = 2;

    /**
     * The number of entries stored in the Map.
     */
    private int size;
    private int capacity;

    @SuppressWarnings("all")
    public MyResizableHashMap() {

        // Initialize buckets list
        buckets = new Node[DEFAULT_BUCKET_ARRAY_SIZE];
        size = 0;
        capacity = DEFAULT_BUCKET_ARRAY_SIZE;
    }

    private void resize() {
        //  function that does the rehashing of the HashMap
        Node<K, V>[] newBucket = new Node[]

    }

    public V get(K key) {
        if(key == null) {
            return null;
        }

        int hash = Math.abs(key.hashCode()) % capacity;

        if(capacity < hash ){
            return null;
        }

        Node<K, V> node = buckets[hash];

        while(node != null){
            if(node.getEntry().getKey().equals(key)){
                return node.getEntry().getValue();
            }

            node = node.getNextElement();
        }

        return null;
    }

    public void put(K key, V value) {
        if (key == null) {
            buckets[0].setNextElement(new Node<>(new MyEntry<>(key, value), 0, null));
            size++;
            return;
        }

        int hash = Math.abs(key.hashCode()) % capacity;

        if (capacity < hash) {
            // TODO do resize
        }

        Node<K, V> node = buckets[hash];

        while(node != null){
            if(node.getEntry().getKey().equals(key)){
                node.getEntry().setValue(value);
                return;
            }

            node = node.getNextElement();
        }

        node = buckets[hash];
        Node<K,V> newNode = new Node<>(new MyEntry<>(key, value), hash, node);
        buckets[hash] = newNode;

        size++;
    }

    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();

        for (Node<K, V> node : buckets) {
            while (node != null) {
                MyEntry<K, V> entry = node.getEntry();
                keySet.add(entry.getKey());

                node = node.getNextElement();
            }
        }

        return keySet;
    }

    public Collection<V> values() {
        Set<V> keySet = new HashSet<>();

        for (Node<K, V> node : buckets) {
            while (node != null) {
                MyEntry<K, V> entry = node.getEntry();
                keySet.add(entry.getValue());

                node = node.getNextElement();
            }
        }

        return keySet;
    }

    public V remove(K key) {
        //  Returns the value associated with the key removed from the map or null if the key wasn't found
//        for(LinkedList<MyEntry> entries : buckets){
//            for(MyEntry myEntry : entries){
//                if(myEntry.getKey().equals(key)){
//                    String value = myEntry.getValue();
//                    entries.remove(myEntry);
//                    size--;
//                    return value;
//                }
//            }
//        }

        int i = 0;

        for (Node<K, V> node : buckets) {
            if(node != null && node.getEntry().getKey().equals(key)){
                V value = node.getEntry().getValue();
                buckets[i] = buckets[i].getNextElement();
                size--;
                return value;
            }

            Node<K, V> lastNode = node;
            while(node != null){

                if(node.getEntry().getKey().equals(key)){
                    V value = node.getEntry().getValue();
                    lastNode.setNextElement(node.getNextElement());
                    size--;
                    return value;
                }

                lastNode = node;
                node = node.getNextElement();
            }

            i++;
        }

        return null;
    }

    public boolean containsKey(K key) {
        for (Node<K, V> node : buckets) {
            while (node != null) {
                MyEntry<K, V> entry = node.getEntry();
                if (entry.getKey() == key) {
                    return true;
                }

                node = node.getNextElement();
            }
        }

        return false;
    }

    public boolean containsValue(V value) {
        for (Node<K, V> node : buckets) {
            while (node != null) {
                MyEntry<K, V> entry = node.getEntry();
                if (entry.getValue() == value) {
                    return true;
                }

                node = node.getNextElement();
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
        for (Node node : buckets) {
            node = null;
        }
        size = 0;
    }

    public Set<MyEntry> entrySet() {
        //  Return a Set containing all the Entry objects
        Set<MyEntry> entrySet = new HashSet<>();
        for (Node node : buckets) {
            while (node != null) {
                MyEntry entry = node.getEntry();
                entrySet.add(entry);

                node = node.getNextElement();
            }
        }
        return entrySet;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static class MyEntry<K, V> {
        private K key;
        private V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    static class Node<K, V> {
        private final MyEntry<K, V> entry;
        private final int hash;
        private Node<K, V> nextElement;

        public Node(MyEntry<K, V> entry, int hash, Node<K, V> nextElement) {
            this.entry = entry;
            this.hash = hash;
            this.nextElement = nextElement;
        }

        public MyEntry<K, V> getEntry() {
            return entry;
        }

        public int getHash() {
            return hash;
        }

        public Node<K, V> getNextElement() {
            return nextElement;
        }

        public void setNextElement(Node<K, V> nextElement) {
            this.nextElement = nextElement;
        }


    }
}
