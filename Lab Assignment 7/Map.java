package com.company;

import java.lang.Iterable;

public interface Map<K, V> {

    // --- METHODS FOR THE MAP ------------>
    int size();

    boolean isEmpty();

    // --- METHODS FOR THE ENTRIES IN THE MAP ------------>

    V get(K key);

    V put(K key, V value);

    V remove(K key);

    // --- ITERABLES ------------>

    Iterable<K> keySet();

    Iterable<V> values();

    Iterable<Entry<K, V>> entrySet();

}
