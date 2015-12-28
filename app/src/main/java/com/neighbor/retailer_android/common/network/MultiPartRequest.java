package com.neighbor.retailer_android.common.network;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import org.apache.http.HttpEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Author: Bu
 * Date  : 2015/9/23
 * Time  : 10:11
 */
public class MultiPartRequest extends Request<String> {

    private Listener<String> listener = null;
    private MultipartParams params = null;
    private HttpEntity httpEntity = null;


    public MultiPartRequest(int method, String url, Listener<String> listener, Response.ErrorListener errorListener,MultipartParams params){
        super(method, url, errorListener);
        this.params = params;
        this.listener = listener;

    }
    public MultiPartRequest(String url, Listener<String> listener, Response.ErrorListener errorListener,MultipartParams params){
        super(1, url, errorListener);
        this.params = params;
        this.listener = listener;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        // TODO Auto-generated method stub
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(params != null) {
            httpEntity = params.getEntity();
            try {
                httpEntity.writeTo(baos);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.d("MultiPartRequest", "IOException writing to ByteArrayOutputStream");
            }
        }
        return baos.toByteArray();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {

        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException var4) {
            parsed = new String(response.data);
        }

        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }




    @Override
    public String getBodyContentType() {
        // TODO Auto-generated method stub
        String str = httpEntity.getContentType().getValue();
        return httpEntity.getContentType().getValue();
    }

    @Override
    protected void deliverResponse(String response) {
        // TODO Auto-generated method stub
        listener.onResponse(response);
    }

}
