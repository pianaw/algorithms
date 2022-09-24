package ru.kpfu.task1.map.iterator;

import ru.kpfu.task1.map.HashMap;

public class MapIterator<K, V> implements Iterator<HashMap.Node<K, V>> {

    private HashMap.Node<K, V>[] nodes;
    private int position = 0;
    private HashMap.Node<K, V> cursor;

    public MapIterator(HashMap.Node<K, V>[] nodes) {
        this.nodes = nodes;
        this.cursor = null;
    }

    @Override
    public boolean hasNext() {
        if (cursor != null && cursor.getNext() != null) {
            return true;
        }
        else {
            int i = position;
            while (i < nodes.length) {
                if (nodes[i] != null) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    @Override
    public HashMap.Node<K, V> next() {
        if (cursor != null && cursor.getNext() != null) {
            HashMap.Node<K, V> current = cursor;
            cursor = cursor.getNext();
            return current;
        }
        else {
            while (position < nodes.length) {
                if (nodes[position] != null) {
                    cursor = nodes[position];
                    position++;
                    return cursor;
                }
                position++;
            }
            return cursor;
        }
    }
}
