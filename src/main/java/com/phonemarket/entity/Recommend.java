package com.phonemarket.entity;

public class Recommend {
    private Long item;
    private  float value;

    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
    public Recommend() {
        super();
    }
}
