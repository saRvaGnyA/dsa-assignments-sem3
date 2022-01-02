package com.company;

import java.util.ArrayList;
import java.lang.Iterable;

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {

    // a fixed size array of UnsortedHashMap as an auxiliary chain
    private UnsortedTableMap<K, V>[] table;

    // --- CONSTRUCTOR ------------>

    public ChainHashMap() {
        super();
    }

    public ChainHashMap(int cap) {
        super(cap);
    }

    public ChainHashMap(int cap, int prime) {
        super(cap, prime);
    }

    // --- CONCRETE IMPLEMENTATION OF AbstractHashMap METHODS ------------>

    // create an empty hash-table with specified capacity
    @Override
    protected void createTable() {
        this.table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[this.capacity];
    }

    // get the value associated to the key 'k' in the bucket corresponding to hash-value 'h' (return null if the entry is not found in the bucket)
    @Override
    protected V bucketGet(int h, K k) {
        UnsortedTableMap<K, V> bucket = this.table[h];
        if (bucket == null) {
            return null;
        } else {
            return bucket.get(k);
        }
    }

    // put the value 'v' in the associated key 'k' and return the previous value
    @Override
    protected V bucketPut(int h, K k, V v) {
        UnsortedTableMap<K, V> bucket = this.table[h];
        if (bucket == null) { // no entry previously existed with the passed key - create a new bucket(chain)
            this.table[h] = new UnsortedTableMap<>();
            bucket = this.table[h];
        }
        int oldN = bucket.size();
        V temp = bucket.put(k, v);
        n += (bucket.size() - oldN); // update the size
        return temp;
    }

    // remove entry with key 'k' in bucket corresponding to hash value 'h' and return deleted value (if any)
    @Override
    protected V bucketRemove(int h, K k) {
        UnsortedTableMap<K, V> bucket = this.table[h];
        if (bucket == null)
            return null;
        else {
            int oldN = bucket.size();
            V temp = bucket.remove(k);
            n -= (oldN - bucket.size()); // update the size
            return temp;
        }
    }

    // return an iterable of all entries in the HashMap
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> temp = new ArrayList<>();
        for (int i = 0; i < capacity; ++i) {
            if (this.table[i] != null) { // if a bucket exists at the 'ith' entry
                for (Entry<K, V> entry : table[i].entrySet()) {
                    temp.add(entry);
                }
            }
        }
        return temp;
    }
}
