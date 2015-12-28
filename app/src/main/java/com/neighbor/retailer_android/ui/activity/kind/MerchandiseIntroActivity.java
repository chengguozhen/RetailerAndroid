package com.neighbor.retailer_android.ui.activity.kind;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarHeader;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarListener;

/**
 * Created by Vicky on 2015/12/17.
 * Retailer_android
 * contact way: 317461087@qq.com
 */
public class MerchandiseIntroActivity extends AppCompatActivity {

    private WebView merchandiseIntro;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchandise_intro);

        initToolbar();
        merchandiseIntro = (WebView)findViewById(R.id.merchandise_intro);
        merchandiseIntro.getSettings().setJavaScriptEnabled(true);
        merchandiseIntro.loadUrl("http://www.baidu.com");
        merchandiseIntro.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return false;

            }
        });
    }

    private void initToolbar()
    {
        view = findViewById(R.id.intro_header);
        if(view != null)
        {
            Toolbar toolbar = (Toolbar)view;
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            MyToolbarHeader toolbarHeader = new MyToolbarHeader(this,toolbar);
            toolbarHeader.setHeaderTitle(getString(R.string.merchandise_intro));
            MyToolbarListener listener = new MyToolbarListener() {
                @Override
                public void addNavigation() {
                    finish();
                }
            };
            toolbarHeader.setNavigation(R.mipmap.back,listener);
            //toolbarHeader.setSearchMenu();
        }
    }
}
