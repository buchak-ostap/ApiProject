package com.apiProject.model.jackson;

public class JSONObjectHolder {
    private String title;
    private Object data;

    public JSONObjectHolder() {
    }

    public String getTitle() {
        return this.title;
    }

    public JSONObjectHolder setTitle(String title) {
        this.title = title;
        return this;
    }

    public Object getData() {
        return this.data;
    }

    public JSONObjectHolder setData(Object data) {
        this.data = data;
        return this;
    }
}
