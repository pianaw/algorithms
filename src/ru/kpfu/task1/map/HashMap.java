package ru.kpfu.task1.map;

import ru.kpfu.task1.map.iterator.Iterator;
import ru.kpfu.task1.map.iterator.MapIterator;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {

    private Node<K, V>[] table;
    private int initialCapacity = Byte.MAX_VALUE + 1;

    public HashMap() {
        this.table = (Node<K, V>[]) new Node[initialCapacity];
    }

    public static class Node<K, V> {

        private final int hash;
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node<K, V> getNext() {
            return next;
        }

        public K getKey() {
            return key;
        }

        protected Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        protected Node<K, V> findPreviousOf(K key) {
            Node<K, V> current = this;
            Node<K, V> prev = this;

            while (current != null) {
                if (current.key.equals(key)) {
                    return prev;
                }
                prev = current;
                current = current.next;
            }

            return null;
        }

        protected Node<K, V> removeNext() {
            Node<K, V> next = this.next;
            this.next = next.next;
            return next;
        }

        protected boolean contains(K key) {
            Node<K, V> current = this.next;
            while (current != null) {
                if (current.key.equals(key)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        @Override
        public String toString() {
            return "\"" + key.toString() + "\": " + value;
        }
    }

    @Override
    public V put(K key, V value) {
        byte index = index(hash(key));

        Node<K, V> prevNode = table[index];
        if (prevNode != null) {
            Node<K, V> existingNode = prevNode.findPreviousOf(key);
            if (existingNode != null) {
                existingNode.value = value;
                return null;
            } else {
                table[index] = new Node<>(index, key, value, prevNode);
            }
        } else {
            table[index] = new Node<>(index, key, value, null);
        }

        return value;
    }

    @Override
    public V remove(K key) {
        byte index = index(hash(key));

        Node<K, V> prevNode = table[index];
        if (prevNode == null) {
            throw new NoSuchElementException("No such key = " + key);
        }
        if (prevNode.next == null) {
            table[index] = null;
            return prevNode.value;
        }

        prevNode = prevNode.findPreviousOf(key);
        if (prevNode != null) {
            return prevNode.removeNext().value;
        }

        return null;
    }

    @Override
    public V get(K key) {
        byte index = index(hash(key));

        Node<K, V> head = table[index];
        if (head == null) {
            throw new NoSuchElementException("No such key = " + key);
        }
        Node<K, V> prevNode = head.findPreviousOf(key);

        if (prevNode != null) {
            if (prevNode.next != null) {
                return prevNode.next.value;
            } else {
                return prevNode.value;
            }
        }

        throw new NoSuchElementException("No such key = " + key);
    }

    @Override
    public boolean contains(K key) {
        byte index = index(hash(key));

        Node<K, V> head = table[index];
        if (head == null) {
            return false;
        }

        if (head.getKey().equals(key)) {
            return true;
        }

        return head.contains(key);
    }

    @Override
    public Iterator iterator() {
        return new MapIterator(this.table);
    }

    private static int hash(Object key) {
        int h = key.hashCode();
        return (key == null) ? 0 : (h) ^ (h >>> 16);
    }

    private byte index(int keyHashCode) {
        byte index = (byte) (keyHashCode * (table.length - 1));
        if (index < 0) {
            index = (byte) -index;
        }
        return index;
    }

    @Override
    public String toString() {
        return "{ " + Arrays.stream(table)
                .filter(Objects::nonNull)
                .map(Node::toString)
                .reduce((str1, str2) -> str1 + ", " + str2)
                .orElse("") + " }";
    }
}