package com.neighbor.retailer_android.bean;


/**
 * Created by Vicky on 2015/12/25.
 * Retailer_android
 * contact way: 317461087@qq.com
 */
public class ResponseInnerBean {

    /**
     * result : {"EXPIRE_TS":"Fri Dec 25 21:39:39 CST 2015","REG_TS":"Fri Dec 25 09:39:39 CST 2015","REG_IP":"0:0:0:0:0:0:0:1","REG_DC":"1F054CC74DA1A13FED9A6023D7762D442C8D4ADEDB7C77005B9B281B44550AA0","TK_ID":"A13E00FFCDE2AE71341194D28DC25389FC850129BC68D6FC6F016A2770D3DD4C","LT_REQ_TS":"Fri Dec 25 09:39:39 CST 2015","US_ID":"123"}
     * msg : 登录成功
     * success : true
     */
    private Object result;
    private String msg;
    private boolean success;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
