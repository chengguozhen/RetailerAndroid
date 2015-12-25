package com.neighbor.retailer_android.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vicky on 2015/12/25.
 * Retailer_android
 * contact way: 317461087@qq.com
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResultBean  {


    /**
     * EXPIRE_TS : Fri Dec 25 21:39:39 CST 2015
     * REG_TS : Fri Dec 25 09:39:39 CST 2015
     * REG_IP : 0:0:0:0:0:0:0:1
     * REG_DC : 1F054CC74DA1A13FED9A6023D7762D442C8D4ADEDB7C77005B9B281B44550AA0
     * TK_ID : A13E00FFCDE2AE71341194D28DC25389FC850129BC68D6FC6F016A2770D3DD4C
     * LT_REQ_TS : Fri Dec 25 09:39:39 CST 2015
     * US_ID : 123
     */

    @JsonProperty("EXPIRE_TS")
    public String expireTs;
    @JsonProperty("REG_TS")
    public String regTs;
    @JsonProperty("REG_IP")
    public String regIp;
    @JsonProperty("REG_DC")
    public String regDc;
    @JsonProperty("TK_ID")
    public String tkId;
    @JsonProperty("LT_REQ_TS")
    public String ltReqTs;
    @JsonProperty("US_ID")
    public String usId;

    public String getExpireTs() {
        return expireTs;
    }

    public String getRegTs() {
        return regTs;
    }

    public String getRegIp() {
        return regIp;
    }

    public String getRegDc() {
        return regDc;
    }

    public String getTkId() {
        return tkId;
    }

    public String getLtReqTs() {
        return ltReqTs;
    }

    public String getUsId() {
        return usId;
    }
}
