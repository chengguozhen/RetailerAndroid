package com.neighbor.retailer_android.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by xu on 2015/12/15.
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WholeSale {
    public String midId;
    public String midName;
    public String picUrl;
    public String midSpec;

    public String getMidId() {
        return midId;
    }

    public void setMidId(String midId) {
        this.midId = midId;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMidSpec() {
        return midSpec;
    }

    public void setMidSpec(String midSpec) {
        this.midSpec = midSpec;
    }
}
