package ru.kpfu.task1.set;

import ru.kpfu.task1.map.HashMap;
import ru.kpfu.task1.map.Map;
import ru.kpfu.task1.map.iterator.MapIterator;

import java.util.Arrays;
import java.util.Objects;

public class HashSet<E> implements Set<E> {

    protected Map<E, Object> map;

    public HashSet() {
        this.map = new HashMap<>();

    }

    @Override
    public boolean add(E elem) {
        if (!contains(elem)) {
            this.map.put(elem, null);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(E elem) {
        if (this.contains(elem)) {
            this.map.remove(elem);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(E elem) {
        return this.map.contains(elem);
    }

    @Override
    public Set<E> intersect(Set<E> set) {
        Set<Object> result = new HashSet<>();
        MapIterator iterator1 = (MapIterator) this.map.iterator();

        while (iterator1.hasNext()) {
            E elem = (E) iterator1.next().getKey();
            if (set.contains(elem)) {
                result.add(elem);
            }
        }

        return (Set<E>) result;
    }

    @Override
    public Set<E> union(Set<E> set) {
        Set<Object> result = new HashSet<>();
        MapIterator iterator1 = (MapIterator) this.map.iterator();
        MapIterator iterator2 = (MapIterator) ((HashSet<E>)(set)).map.iterator();

        result.add(iterator1.next().getKey());
        while (iterator1.hasNext()) {
            result.add(iterator1.next().getKey());
        }

        result.add(iterator1.next().getKey());
        while (iterator2.hasNext()) {
            result.add(iterator2.next().getKey());
        }

        return (Set<E>) result;
    }

    @Override
    public Set<E> diff(Set<E> set) {
        Set<Object> result = new HashSet<>();
        MapIterator iterator1 = (MapIterator) this.map.iterator();
        MapIterator iterator2 = (MapIterator) ((HashSet<E>)(set)).map.iterator();

        while (iterator1.hasNext()) {
            result.add(iterator1.next().getKey());
        }

        while (iterator2.hasNext()) {
            result.remove(iterator2.next().getKey());
        }

        return (Set<E>) result;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
