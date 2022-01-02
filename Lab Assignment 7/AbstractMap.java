package com.company;

import java.util.Iterator;
import java.lang.Iterable;

public abstract class AbstractMap<K, V> implements Map<K, V> {

    // --- NESTED MAP-ENTRY CLASS ------------>

    protected static class MapEntry<K, V> implements Entry<K, V> {

        // private attributes

        private K key;
        private V value;

        // constructor
        public MapEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        // public getters to implement the interface

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        // protected setter methods apart from those in the interface

        protected void setKey(K key) {
            this.key = key;
        }

        protected V setValue(V val) {
            V temp = this.value;
            this.value = val;
            return temp;
        }

    }

    // --- METHODS OF THE ABSTRACT CLASS ------------>

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    // keySet

    private class KeyIterator implements Iterator<K> {

        // use entrySet iterator
        private final Iterator<Entry<K, V>> entries = entrySet().iterator();

        // implement the iterator interface methods
        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public K next() {
            return entries.next().getKey();
        }

    }

    private class KeyIterable implements Iterable<K> {

        // implement the iterable interface methods

        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

    }

    @Override
    public Iterable<K> keySet() {
        return new KeyIterable();
    }

    // valueSet

    private class valueIterator implements Iterator<V> {

        // use entrySet iterator
        private final Iterator<Entry<K, V>> entries = entrySet().iterator();

        // implement the iterator interface methods
        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public V next() {
            return entries.next().getValue();
        }

    }

    private class ValueIterable implements Iterable<V> {

        // implement the iterable interface methods

        @Override
        public Iterator<V> iterator() {
            return new valueIterator();
        }

    }

    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }

}