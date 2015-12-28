package com.neighbor.retailer_android.common.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.neighbor.retailer_android.MyApplication;
import com.neighbor.retailer_android.bean.ResponseBean;
import com.neighbor.retailer_android.common.Common;
import com.neighbor.retailer_android.common.utils.JsonUtil;
import com.neighbor.retailer_android.common.utils.MLog;
import com.neighbor.retailer_android.common.utils.MToast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;


/**
 * Author: Bu
 * Date  : 2015/12/14
 * Time  : 15:49
 */
public class MRequest {
    public static RequestQueue mAppQueue = MyApplication.getQueue();



    public static int DEFAULT_TIMECOUNT = 1;

    /**
     * @param url 后半部分不同的URL
     * @return 完整的URL
     */
    private static String getUrl(String url) {
        return Common.baseUrl + url;
    }

    /**
     * 拼接get请求的url
     *
     * @param url    请求url
     * @param params 参数的map
     * @return 拼接好的url
     */
    private static String getUrlWithParams(String url, Map<String, String> params) {
        if (params == null || params.size() == 0)
            return url;
        StringBuilder sb = new StringBuilder();
        for (String paramKey : params.keySet()) {
            try {
                sb.append("&")
                        .append(paramKey)
                        .append("=")
                        .append(URLEncoder.encode(params.get(paramKey), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                return url;
            } catch (Exception e) {
                return url;
            }
        }
        return url + sb.replace(0, 1, "?").toString();
    }

    /**
     * Http请求方法base
     *
     * @param context       context
     * @param mQueue        请求队列
     * @param errorListener 如果传null，使用默认的
     * @param method        GET/POST
     * @param url           不带公共前缀的url
     * @param headerMap     header的Map，不需要header请传null
     * @param params        参数的Map，没有参数请传null
     * @param mhandler      handler
     * @param what          handler 里message的what的值
     * @param time_count    timeout的时间  2.5秒的多少倍
     */
    public static void doRequest(final Context context, RequestQueue mQueue, Response.ErrorListener errorListener, final int method,
                                 final String url, final Map<String, String> headerMap, final Map<String, String> params,
                                 final Handler mhandler, final int what, int time_count, final int failed_what, final boolean isToast) {


        if (errorListener == null) {
            errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    ResponseBean bean = null;
                    Message message = new Message();
                    message.what = failed_what;
                    message.obj = bean;
                    mhandler.sendMessage(message);
                    if (isToast) {
                        if (volleyError.networkResponse == null) {
                            MToast.show(context, "网络连接错误");
                        } else if (volleyError.networkResponse.statusCode == 408) {
                            MToast.show(context, "网络连接超时");
                        } else {
                            MToast.show(context, "服务器出错了");
                        }
                    }
                }
            };
        }
        StringRequest request = new StringRequest(method, getUrl(url), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                MLog.i("request result", s);
                ResponseBean bean = null;
                bean = JsonUtil.jsonToObj(s, ResponseBean.class);

                Message message = new Message();
                message.what = what;
                if (bean.getErrCd() == 0) {
                    MLog.i("HTTP: --", bean.getResult().toString());
                    message.obj = bean;
                    mhandler.sendMessage(message);
                } else {
                    /**
                     * 这里添加返回特定error code时做的操作
                     */
                    MLog.i("HTTP: error", bean.getErrMsg());
                    message.what = failed_what;
                    message.obj = bean;
                    mhandler.sendMessage(message);
                }
            }
        }, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (headerMap == null) {
                    return super.getHeaders();
                }
                return headerMap;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> paramsTemp;
                if (method == Method.GET || params == null) {
                    paramsTemp = super.getParams();
                } else {
                    paramsTemp = params;
                    MLog.i("HTTP POST Request:", super.getUrl() + "-" + params.toString());
                }

                return paramsTemp;
            }

            @Override
            public String getUrl() {
                String urlString;
                if (method == Method.GET) {
                    urlString = getUrlWithParams(super.getUrl(), params);
                    MLog.i("HTTP GET Request:", urlString);
                } else {
                    urlString = super.getUrl();
                }
                return urlString;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * time_count,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setShouldCache(false);
        mQueue.add(request);
    }


    /**
     * get
     * 使用Application的mQueue、errorListener
     * 进行toast
     * @param context
     * @param url
     * @param headerMap
     * @param params
     * @param mhandler
     * @param what
     * @param failed_what
     * @param time_count
     */
    public static void getRequest(Context context, String url, Map<String, String> headerMap, Map<String, String> params,
                                  Handler mhandler, int what, int failed_what, int time_count) {
        doRequest(context, mAppQueue, null, Request.Method.GET, url, headerMap, params, mhandler, what, time_count, failed_what, true);
    }

    /**
     * get
     * 使用Application的mQueue、errorListener
     * 进行toast
     * 请求时间为默认时间
     * @param context
     * @param url
     * @param headerMap
     * @param params
     * @param mhandler
     * @param what
     * @param failed_what
     */
    public static void getRequest(Context context, String url, Map<String, String> headerMap, Map<String, String> params,
                                  Handler mhandler, int what, int failed_what) {
        doRequest(context, mAppQueue, null, Request.Method.GET, url, headerMap, params, mhandler, what, DEFAULT_TIMECOUNT, failed_what, true);
    }

    /**
     * post
     * 用Application的mQueue、errorListener
     * 进行toast
     * @param context
     * @param url
     * @param headerMap
     * @param params
     * @param mhandler
     * @param what
     * @param failed_what
     * @param time_count
     */
    public static void postRequest(Context context,String url, Map<String, String> headerMap, Map<String, String> params,
                                   Handler mhandler, int what, int failed_what, int time_count) {
        doRequest(context, mAppQueue, null, Request.Method.POST, url, headerMap, params, mhandler, what, time_count, failed_what, true);
    }

    /**
     * post
     * 使用Application的mQueue、errorListener
     * 进行toast
     * 请求时间为默认时间
     * @param context
     * @param url
     * @param headerMap
     * @param params
     * @param mhandler
     * @param what
     * @param failed_what
     */
    public static void postRequest(Context context, String url, Map<String, String> headerMap, Map<String, String> params,
                                   Handler mhandler, int what, int failed_what) {
        doRequest(context, mAppQueue, null, Request.Method.POST, url, headerMap, params, mhandler, what, DEFAULT_TIMECOUNT, failed_what, true);
    }

    /**
     * 登出专用请求post
     * 使用Application的mQueue、errorListener
     * 不进行toast
     * 请求时间为默认时间
     * @param context
     * @param url
     * @param headerMap
     * @param params
     * @param mhandler
     * @param what
     * @param failed_what
     */
    public static void logoutRequest(Context context, String url, Map<String, String> headerMap, Map<String, String> params,
                                   Handler mhandler, int what, int failed_what) {
        doRequest(context, mAppQueue, null, Request.Method.POST, url, headerMap, params, mhandler, what, DEFAULT_TIMECOUNT, failed_what, false);
    }

    /**
     * 上传文件
     *
     * @param context
     * @param url
     * @param params
     * @param mHandler
     * @param what
     * @param what_failed
     * @param isToast
     */
    public static void uploadFile(final Context context, String url, MultipartParams params, final Handler mHandler, final int what, final int what_failed, final boolean isToast) {
        final Message message = new Message();
        MultiPartRequest request = new MultiPartRequest(getUrl(url), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                ResponseBean bean = null;
                bean = JsonUtil.jsonToObj(s, ResponseBean.class);
                message.what = what;
                message.obj = bean;
                mHandler.sendMessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (isToast) {
                    if (volleyError.networkResponse == null) {
                        MToast.show(context, "网络连接错误");
                    } else if (volleyError.networkResponse.statusCode == 408) {
                        MToast.show(context, "网络连接超时");
                    } else {
                        MToast.show(context, "服务器内部错误");
                    }
                }
                message.what = what_failed;
                mHandler.sendMessage(message);
            }
        }, params);
        request.setShouldCache(false);
        mAppQueue.add(request);
    }

    /**
     * @param url
     * @param params
     * @param mHandler
     * @param what
     * @param what_failed
     */
    public static void uploadFile(String url, MultipartParams params, Handler mHandler, int what, int what_failed) {
        uploadFile(null, url, params, mHandler, what, what_failed, false);
    }
}
