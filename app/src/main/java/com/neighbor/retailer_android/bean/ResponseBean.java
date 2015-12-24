package com.neighbor.retailer_android.bean;

public class ResponseBean {
    public String id;
    public String trId;
    public Object result;
    public String errMsg;
    public int errCd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrId() {
        return trId;
    }

    public void setTrId(String trId) {
        this.trId = trId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getErrCd() {
        return errCd;
    }

    public void setErrCd(int errCd) {
        this.errCd = errCd;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "errCd=" + errCd +
                ", errMsg='" + errMsg + '\'' +
                ", result=" + result +
                ", trId='" + trId + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}