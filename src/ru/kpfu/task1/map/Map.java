package ru.kpfu.task1.map;

import ru.kpfu.task1.map.iterator.Iterator;

public interface Map<K, V> {

    V put(K key, V value);
    V remove(K key);
    V get(K key);
    boolean contains(K key);
    Iterator iterator();
}
