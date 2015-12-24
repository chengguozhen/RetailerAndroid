package com.neighbor.retailer_android.common;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

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

    private RequestQueue mQueue;


    private static void basicRequest(final Context context,final int method,final String url,
                      RequestQueue mQueue, final Map<String, String> params, int time_count,
                      final int what,final Handler mHandler, final int where_failed)
    {
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //Message message = new Message();
                mHandler.sendEmptyMessage(where_failed);

                if (volleyError.networkResponse == null) {
                    Toast.makeText(context, "网络连接错误", Toast.LENGTH_SHORT).show();
                } else if (volleyError.networkResponse.statusCode == 408) {
                    Toast.makeText(context, "网络连接超时", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "服务器内部错误", Toast.LENGTH_SHORT).show();
                }
            }
        };
        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //转化json对象数据

                //处理返回数据
                Message msg = new Message();
                msg.what = what;
                //msg.obj = bean;
            }
        }, errorListener)
        {
            //发送post请求时，调用父类的getParams方法来获得发送给服务器的参数值
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> paramsTemp;
                if(method == Method.GET || params == null)
                {
                    paramsTemp = super.getParams();
                }
                else {
                    paramsTemp = params;
                    Log.i("http post request:", super.getUrl()+"\n params:"+params.toString());
                }
                return paramsTemp;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * time_count,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(request);
    }

}
