package ru.kpfu.task1.set;

public interface Set<E> {

    boolean add(E elem);
    boolean remove(E elem);
    boolean contains(E elem);
    Set<E> intersect(Set<E> set);
    Set<E> union(Set<E> set);
    Set<E> diff(Set<E> set);
}
