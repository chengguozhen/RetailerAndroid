package com.neighbor.retailer_android.bean;

import java.util.List;

/**
 * Created by xu on 2015/12/15.
 */
public class WholeSale {
    private String name;
    private String url;
    private String tag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "WholeSale{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
