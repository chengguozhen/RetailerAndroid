package com.neighbor.retailer_android.ui.activity.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarHeader;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarListener;

public class MyIdentityActivity extends AppCompatActivity {

    /**
     * toolbar
     */
    private View view;
    /**
     * 我的头像 证件照
     */
    private ImageView myImage,myIdentityImage;
    /**
     * 我的昵称 账户
     */
    private EditText myName,myAccount;
    //商品种类应该是选择的
    /**
     * 我的区域 经营范围 负责人 负责人电话
     */
    private EditText myAddress,myStoreArea,myProxy,myProxyPhone;
    /**
     * 证件类型 证件号 银行卡号 支付宝号
     */
    private TextView myIdentityKind,myIdentityNum,myBankAccount,myAlipayAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_identity);

        initToolbar();
    }

    /**
     * 初始化toolbar
     */
    private void initToolbar()
    {
        view = findViewById(R.id.my_identity_toobar);
        if(view != null)
        {
            Toolbar toolbar = (Toolbar)view;
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            MyToolbarHeader toolbarHeader = new MyToolbarHeader(this,toolbar);
            toolbarHeader.setHeaderTitle(getString(R.string.my_identity));
            MyToolbarListener listener = new MyToolbarListener() {
                @Override
                public void addNavigation() {
                    finish();
                }
            };
            toolbarHeader.setNavigation(R.mipmap.back,listener);
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return false;
                }
            });
        }
    }


}
