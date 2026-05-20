package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size;
    private double maxLoad;
    private Set<K> keys;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.size = 0;
        this.maxLoad = maxLoad;
        this.buckets = createTable(initialSize);
        this.keys = new HashSet<>();
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    @SuppressWarnings("unchecked")
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = (Collection<Node>[]) new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    // 私有哈希方法
    private int hash(K key, int tableSize) {
        if (key == null) return 0;
        return Math.floorMod(key.hashCode(), tableSize);
    }

    // 私有扩容方法
    private void resize(int newSize) {
        Collection<Node>[] oldOne = buckets;
        buckets = createTable(newSize);
        size = 0;
        keys.clear();

        for (Collection<Node> bucket : oldOne) {
            if (bucket != null) {
                for (Node node : bucket) {
                    put(node.key, node.value);
                }
            }
        }
    }

    // 私有负载检查方法
    private void checkLoad() {
        if ((double) size / buckets.length > maxLoad) {
            resize(buckets.length * 2);
        }
    }

    @Override
    public void clear() {
        buckets = createTable(buckets.length);
        size = 0;
        keys.clear();
    }

    @Override
    public boolean containsKey(K key) {
        int idx = hash(key, buckets.length);
        if (buckets[idx] == null) {
            return false;
        }

        for (Node node : buckets[idx]) {
            if (key.equals(node.key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public V get(K key) {
        int idx = hash(key, buckets.length);
        if (buckets[idx] == null) {
            return null;
        }

        for (Node node : buckets[idx]) {
            if (key.equals(node.key)) {
                return node.value;
            }
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int idx = hash(key, buckets.length);
        if (buckets[idx] == null) {
            buckets[idx] = createBucket();
        }

        for (Node node : buckets[idx]) {
            if (key.equals(node.key)) {
                node.value = value;
                return;
            }
        }

        buckets[idx].add(createNode(key, value));
        keys.add(key);
        size++;
        checkLoad();
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }

}
