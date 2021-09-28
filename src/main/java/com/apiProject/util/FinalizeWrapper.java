package com.apiProject.util;

public final class FinalizeWrapper<T> {

    private T value;

    public FinalizeWrapper() {
    }

    public FinalizeWrapper(final T value){
        this.value = value;
    }
    public T getValue() {
        return value;
    }

    public void setValue(final T value) {
        this.value = value;
    }

    public boolean isNull() {
        return value == null;
    }
}
