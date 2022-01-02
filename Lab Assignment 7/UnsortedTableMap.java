package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Iterable;
import java.util.NoSuchElementException;

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {

    // Storing the hashtable in an ArrayList
    private final ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    // --- CONSTRUCTOR ------------>

    public UnsortedTableMap() {
        // create an empty HashMap
    }

    // --- PRIVATE UTILITY ------------>

    // returns array index of the passed key (if not found, returns -1)
    private int findKey(K k) {
        for (int i = 0; i < table.size(); ++i) {
            if (table.get(i).equals(k)) return i;
        }
        return -1;
    }

    // --- CONCRETE IMPLEMENTATION OF THE AbstractMap METHODS ------------>

    // return the hash-table size
    @Override
    public int size() {
        return table.size();
    }

    // return the value of the entry with the passed key (return null if no entry exists)
    @Override
    public V get(K key) {
        int n = findKey(key);
        if (n == -1) return null;
        return table.get(n).getValue();
    }

    // add a new entry with passed key (if key exists, override that entry's value)
    @Override
    public V put(K key, V value) {
        int n = findKey(key);
        if (n == -1) { // i.e. table doesn't have the entry
            MapEntry<K, V> newEntry = new MapEntry<>(key, value);
            table.add(newEntry);
            return null;
        } else { // i.e. entry exists
            return table.get(n).setValue(value);
        }
    }

    // remove entry with passed key and return its value (if entry doesn't exist, return null)
    @Override
    public V remove(K key) {
        int j = findKey(key);
        int n = size();
        if (j == -1) { // entry doesn't exist
            return null;
        } else {
            V temp = table.get(j).getValue();
            if (j != n - 1) {
                table.set(j, table.get(n - 1)); // shift the last entry to the emptied index
            }
            table.remove(n - 1);
            return temp;
        }
    }

    // iterator of entries
    private class EntryIterator implements Iterator<Entry<K, V>> {

        private int j = 0; // current corresponding index of the iteration

        @Override
        public boolean hasNext() {
            return j < table.size();
        }

        @Override
        public Entry<K, V> next() throws NoSuchElementException {
            if (table.size() == j) throw new NoSuchElementException("Array index out of bounds");
            return table.get(j++); // increment to the next array index
        }

    }

    // private utility - iterable of entries
    private class EntryIterable implements Iterable<Entry<K, V>> {

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }

    // public wrapper for iterable of entries - implementing the abstract base class method
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }
}
