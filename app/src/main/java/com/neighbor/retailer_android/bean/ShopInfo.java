package com.neighbor.retailer_android.bean;

import java.util.List;

/**
 * Created by xu on 2015/12/15.
 */
public class ShopInfo {
    private String url;
    private String name;
    private String spec;
    private double price;
    private int count;
    private double sum;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
    @Override
    public String toString() {
        return "ShopInfo{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", spec='" + spec + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", sum=" + sum +
                '}';
    }
}
