package com.company;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {

    // --- PRIVATE ATTRIBUTES ------------>

    protected int n = 0; // current number of entries in the HashMap
    protected int capacity; // size of the hash table
    private final int prime; // prime factor
    private final long scale, shift; // shifting and scaling factors

    // --- CONSTRUCTORS ------------>

    AbstractHashMap(int cap, int p) {
        this.capacity = cap;
        this.prime = p;
        Random rand = new Random();
        this.scale = rand.nextInt(this.prime - 1) + 1;
        this.shift = rand.nextInt(this.prime);
        createTable();
    }

    AbstractHashMap(int cap) {
        // delegate to the two-args constructor with a default prime
        this(cap, 65537);
    }

    AbstractHashMap() {
        // delegate to the one-arg constructor with a default capacity
        this(20);
    }

    // --- PROTECTED ABSTRACT METHODS TO BE IMPLEMENTED BY CHILDREN CLASSES ------------>
    protected abstract void createTable();

    protected abstract V bucketGet(int h, K k);

    protected abstract V bucketPut(int h, K k, V v);

    protected abstract V bucketRemove(int h, K k);

    // --- PRIVATE UTILITIES ------------>

    private int hashValue(K k) {
        // return the hash-value using the MAD method
        return (int) ((Math.abs(k.hashCode() * this.scale + this.shift) % this.prime) % this.capacity);
    }

    private void resize(int newCap) {
        // resize the hash-table when the number of entries in the hash-map start to exceed the hash-table's capacity

        ArrayList<Entry<K, V>> temp = new ArrayList<>(this.n); // store the current hash-map

        // deep-copy the current hash-map to the temp array
        for (Entry<K, V> entry : entrySet()) {
            temp.add(entry);
        }

        // create a new hash-map and update the corresponding size attributes
        this.capacity = newCap;
        createTable(); // based on the new capacity attribute
        this.n = 0;

        // copy back the previously stored entries in the old hash-map
        for (Entry<K, V> entry : temp) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    // --- PUBLIC METHODS ------------>

    public int size() {
        return this.n; // return current number of entries in the HashMap
    }

    public V get(K k) {
        return bucketGet(hashValue(k), k);
    }

    public V remove(K k) {
        return bucketRemove(hashValue(k), k);
    }

    public V put(K k, V v) {
        V temp = bucketPut(hashValue(k), k, v);

        // check the load factor "alpha" if it is within the required limits (less than 0.5) - if not, then resize the hash-map with a new capacity
        if (this.n / this.capacity > 1 / 2) {
            resize(2 * this.capacity - 1);
        }

        return temp;
    }

}
