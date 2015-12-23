package com.neighbor.retailer_android.ui.activity.my;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.common.Constants;

/**
 * Created by Vicky on 2015/9/9.
 * WeiFa
 * contact way: 317461087@qq.com
 */

public class ModifyPwd extends Activity{

    private EditText oldPwd;
    private EditText newPwd;
    private EditText newPwd2;

    private String pwdOldStr;
    private String pwdNewStr;
    private String pwdNewStr2;

//    private RelativeLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_pwd);

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
