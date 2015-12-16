package com.neighbor.retailer_android.bean;

import java.util.List;

/**
 * Created by xu on 2015/12/15.
 */
public class Dish {
    private List<String> list;
    private String name;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "list=" + list +
                ", name='" + name + '\'' +
                '}';
    }
}
