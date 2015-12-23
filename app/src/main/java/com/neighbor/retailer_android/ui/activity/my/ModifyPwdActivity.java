package com.neighbor.retailer_android.ui.activity.my;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.common.Constants;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarHeader;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarListener;

/**
 * Created by Vicky on 2015/9/9.
 * WeiFa
 * contact way: 317461087@qq.com
 */

public class ModifyPwdActivity extends AppCompatActivity{

    private EditText oldPwd;
    private EditText newPwd;
    private EditText newPwd2;

    private String pwdOldStr;
    private String pwdNewStr;
    private String pwdNewStr2;

    private View view;
//    private RelativeLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_pwd);

        initView();

    }


    private void initView()
    {
        view = findViewById(R.id.pwd_header);
        if(view != null)
        {
            Toolbar toolbar = (Toolbar)view;
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            MyToolbarHeader toolbarHeader = new MyToolbarHeader(this,toolbar);
            toolbarHeader.setHeaderTitle(/*getString(R.string.)*/"修改密码");
            toolbarHeader.setSearchMenu();
            MyToolbarListener listener = new MyToolbarListener() {
                @Override
                public void addNavigation() {
                   finish();
                }
            };
            toolbarHeader.setNavigation(R.mipmap.back, listener);
            //toolbarHeader.setSearchMenu();
        }
        oldPwd = (EditText)findViewById(R.id.old_pwd);
        newPwd = (EditText)findViewById(R.id.new_pwd);
        newPwd2 = (EditText)findViewById(R.id.new_pwd2);
    }

    public void getString()
    {
        pwdOldStr = oldPwd.getText().toString();
        pwdNewStr = newPwd.getText().toString();
        pwdNewStr2 = newPwd2.getText().toString();
        if(null == pwdOldStr||pwdOldStr.equals(""))
        {
            Toast.makeText(this,"旧密码不可为空",Toast.LENGTH_SHORT).show();
        }
        else {
            if("".equals(pwdNewStr)||pwdNewStr.length()< Constants.pwd_min_length
                    ||pwdNewStr.length()>Constants.pwd_max_length||"".equals(pwdNewStr2)
                    ||pwdNewStr2.length()<Constants.pwd_min_length||pwdNewStr2.length()>Constants.pwd_max_length)
            {
                Toast.makeText(this,"新密码长度须在6-18之间",Toast.LENGTH_SHORT).show();
            }
            else {
                if(pwdNewStr.equals(pwdNewStr2))
                {
                    if(pwdNewStr.equals(pwdOldStr))
                    {
                        Toast.makeText(this,"新旧密码不可相同",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //可做保存密码操作
                    }

                }
                else {
                    Toast.makeText(this,"两次新密码不相同",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


}
