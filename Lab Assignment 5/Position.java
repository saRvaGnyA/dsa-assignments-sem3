package com.company;

public interface Position<E> {

    E getElement() throws IllegalStateException;

}
