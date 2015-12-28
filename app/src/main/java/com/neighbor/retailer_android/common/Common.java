package com.neighbor.retailer_android.common;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.neighbor.retailer_android.common.network.MRequest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Vicky on 2015/12/24.
 * Retailer_android
 * contact way: 317461087@qq.com
 *
 * 接口方法类
 *
 *
 */
public class Common {

    /**
     * url address
     */
    public static String baseUrl = "http://192.168.10.110:8088/v1";
    public static String loginUrl = "/openapi/user/accessToken.d-json";
    public static String registerUrl = "/openapi/user/resgister.d-json";
    public static String merchandiseDetailUrl = "/api/mid/good/goodInfo.d-json";

    /**
     * msg.what
     */
    public static final int loginUrl_success = 10001;
    public static final int loginUrl_failed = 10002;
    public static final int register_susccess = 10004;
    public static final int register_failed = 10005;
    public static final int merchandiseDetailSuccess = 10007;
    public static final int merchandiseDetailFailed = 10008;


    /**
     * login params
     */
    public static String KEY_USER_TEL = "user_tel";
    public static String KEY_REMEMBER_ME = "remember_me";
    public static String KEY_USER_PW = "user_pw";
    /**
     * register params
     */
    public static String KEY_US_NICK_NM = "US_NICK_NM";
    public static String KEY_US_TEL = "US_TEL";
    public static String KEY_US_PW = "US_PW";
    public static String KEY_CO_NM_CN = "CO_NM_CN";
    public static String KEY_CO_TYPE = "CO_TYPE";
    public static String KEY_CHARGE_NM = "CHARGE_NM";
    public static String KEY_CHARGE_TEL = "CHARGE_TEL";
    public static String KEY_LocalArea = "LocalArea";
    public static String KEY_CO_BIZ_SCOPE = "CO_BIZ_SCOPE";
    public static String KEY_CO_ADDR_CN = "CO_ADDR_CN";
    public static String KEY_DocumentType = "DocumentType";
    public static String KEY_DocumentNum = "DocumentNum";
    public static String KEY_US_MAIL = "US_MAIL";
    /**
     * 商品详情 ：商品Id
     */
    public static String KEY_GD_ID = "GD_ID";

    /**
     * 登录接口
     * @param context
     * @param mhandler
     * @param username  用户名
     * @param pwd  密码
     * @param rememberMe  是否记住此用户
     */
    public static void login(Context context, Handler mhandler, String username, String pwd ,String rememberMe)
    {
        Map<String,String> params = new HashMap<String, String>();
        params.put(KEY_USER_TEL,username);
        params.put(KEY_USER_PW,pwd);
        params.put(KEY_REMEMBER_ME,rememberMe);
        MRequest.postRequest(context, loginUrl, null, params, mhandler, loginUrl_success, loginUrl_failed);
    }
    /*
        US_NICK_NM,  //昵称
    US_TEL, //个人电话
    US_PW, //密码
    CO_NM_CN,  //公司中文名
    CO_TYPE, //公司类型
    CHARGE_NM, //负责人名字
    CHARGE_TEL, //负责人电话
    LocalArea,  //所属区域
    CO_BIZ_SCOPE,  //经营范围
    CO_ADDR_CN, //公司中文地址
    DocumentType, //证件类型
    DocumentNum //证件号码
    */
    public static void register(Context context, Handler mhandler, String nickName, String telephone,
                  String pwd, String firmName, String firmType, String charge, String chargePhone,
                  String area, String controlArea, String address, String identityType, String identitiyNum,
                  String email)
    {
        Map<String,String> params = new HashMap<String, String>();
        params.put(KEY_US_NICK_NM,nickName);
        params.put(KEY_US_TEL,telephone);
        params.put(KEY_US_PW,pwd);
        params.put(KEY_CO_NM_CN, firmName);
        params.put(KEY_CO_TYPE, firmType);
        params.put(KEY_CHARGE_NM, charge);
        params.put(KEY_CHARGE_TEL, chargePhone);
        params.put(KEY_LocalArea, area);
        params.put(KEY_CO_BIZ_SCOPE,controlArea);
        params.put(KEY_CO_ADDR_CN, address);
        params.put(KEY_DocumentType, identityType);
        params.put(KEY_DocumentNum, identitiyNum);
        params.put(KEY_US_MAIL,email);

        MRequest.postRequest(context, registerUrl, null, params, mhandler, register_susccess, register_failed);

    }

    public static void merchandiseDetail(Context context, Handler mhandler, String merchandiseId)
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put(KEY_GD_ID,merchandiseId);

        MRequest.postRequest(context, merchandiseDetailUrl, null, params, mhandler,
                merchandiseDetailSuccess, merchandiseDetailFailed);
    }
}
