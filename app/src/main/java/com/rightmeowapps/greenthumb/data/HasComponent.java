package com.rightmeowapps.greenthumb.data;

public interface HasComponent<T> {
    T getComponent();

    T buildComponent();
}