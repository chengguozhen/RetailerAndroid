package com.neighbor.retailer_android.bean;

import java.util.List;

/**
 * Created by xu on 2015/12/15.
 */
public class ShopCartInfo {
    private List<ShopInfo> list;
    private String name;
    private String category;

    public List<ShopInfo> getList() {
        return list;
    }

    public void setList(List<ShopInfo> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ShopCartInfo{" +
                "list=" + list +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
