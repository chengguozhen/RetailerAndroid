package com.neighbor.retailer_android.ui.activity.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.neighbor.retailer_android.R;


public class Agreement extends Activity implements View.OnClickListener {

    private TextView titleText;
    private ImageButton title_back;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_agreement);
        titleText = (TextView) findViewById(R.id.login_title);
        titleText.setText("用户服务协议");
        title_back = (ImageButton) findViewById(R.id.login_back);
        title_back.setOnClickListener(this);
        webView = (WebView) this.findViewById(R.id.webView);
        //webView.loadUrl(Common.AGREEMENT_URL);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_back:
                finish();
                break;
            default:
                break;
        }
    }
}
