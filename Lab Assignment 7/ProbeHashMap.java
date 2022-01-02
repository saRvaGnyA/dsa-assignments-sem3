package com.company;

import java.util.ArrayList;

public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {

    private MapEntry<K, V>[] table; // fixed-capacity array of entries
    private final MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null); // sentinel entry

    // --- CONSTRUCTOR ------------>

    public ProbeHashMap() {
        super();
    }

    public ProbeHashMap(int cap) {
        super(cap);
    }

    public ProbeHashMap(int cap, int prime) {
        super(cap, prime);
    }

    // --- PRIVATE UTILITIES ------------>

    // return if the passed array location is empty or sentinel
    private boolean isAvailable(int j) {
        return (this.table[j] == null || this.table[j] == this.DEFUNCT);
    }

    // returns array index with key 'k' (if not exists, returns an index which can hold a new entry with that key
    private int findSlot(int h, K k) {
        int avail = -1;
        int j = h; // index while finding in the hash table
        do {
            if (isAvailable(j)) {
                if (avail == -1) // first slot itself found
                    avail = j;
                if (this.table[j] == null) // empty slot found - return slot
                    break;
            } else if (this.table[j].getKey().equals(k)) {
                return j;
            }
            j = (j + 1) % capacity; // linear probe
        } while (j != h); // stop linear probing if we return to the starting location
        return -(avail + 1);
    }

    // --- CONCRETE IMPLEMENTATION OF AbstractHashMap METHODS ------------>

    // create an empty hash-table with specified capacity
    @Override
    protected void createTable() {
        this.table = (MapEntry<K, V>[]) new MapEntry[capacity];
    }

    // get the value associated to the key 'k' in the bucket corresponding to hash-value 'h' (return null if the entry is not found in the bucket)
    @Override
    protected V bucketGet(int h, K k) {
        int n = findSlot(h, k);
        if (n < 0)
            return null;
        else
            return table[n].getValue();
    }

    // put the value 'v' in the associated key 'k' and return the previous value
    @Override
    protected V bucketPut(int h, K k, V v) {
        int j = findSlot(h, k);
        if (j >= 0) {
            return table[h].setValue(v);
        }
        table[-(j + 1)] = new MapEntry<>(k, v); // if key doesn't already exist, create its associated entry at the returned empty slot
        ++n;
        return null;
    }

    // remove entry with key 'k' in bucket corresponding to hash value 'h' and return deleted value (if any)
    @Override
    protected V bucketRemove(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0) // if the key doesn't have an associated empty
            return null;
        else {
            V temp = table[h].getValue();
            table[j] = DEFUNCT; // mark the slot as DEFUNCT(deleted)
            --n;
            return temp;
        }
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> temp = new ArrayList<>();
        for (int i = 0; i < capacity; ++i) {
            if (!isAvailable(i)) {
                temp.add(table[i]);
            }
        }
        return temp;
    }
}
