package com.neighbor.retailer_android.ui.activity.login;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.neighbor.retailer_android.R;


public class RetrievePwdActivity extends Activity implements View.OnClickListener {

    private TextView title;
    private ImageButton doBack;
    private EditText phone_input;
    private EditText code_input;
    private EditText pwd_input;
    private Button code_get_btn;
    private Button pwd_new_btn;
    //private RequestQueue mQueue;
    private TimeCount timeCount;
    private String uuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_retrieve_pwd);
        init();
    }

    public void init(){
        title = (TextView) findViewById(R.id.login_title);
        title.setText(getResources().getString(R.string.login_notice_password_retrieve));
        doBack = (ImageButton) findViewById(R.id.login_back);
        doBack.setOnClickListener(this);
        phone_input = (EditText) findViewById(R.id.login_pwd_new_phone_input);
        code_input = (EditText) findViewById(R.id.login_pwd_new_code_input);
        pwd_input = (EditText) findViewById(R.id.login_pwd_new_pwd_input);
        code_get_btn = (Button) findViewById(R.id.login_pwd_new_code_btn);
        code_get_btn.setOnClickListener(this);
        pwd_new_btn = (Button) findViewById(R.id.login_pwd_new_btn);
        pwd_new_btn.setOnClickListener(this);
        //mQueue = Volley.newRequestQueue(this);
        uuid = "";
        timeCount = new TimeCount(60000, 1000);//构造CountDownTimer对象
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //title返回键
            case R.id.login_back:
                finish();
                break;
            //获取验证码
            case R.id.login_pwd_new_code_btn:
//                if(Common.isPhoneQualified(phone_input.getText().toString().trim())){
//                    Common.newPwdCode(this,mQueue,getCodeListener,errorListener,phone_input.getText().toString().trim());
//                } else {
//                    Toast.makeText(this, getResources().getString(R.string.login_quick_phone_illegal), Toast.LENGTH_SHORT).show();
//                }
                break;
            //设置新密码
            case R.id.login_pwd_new_btn:
                String code = code_input.getText().toString().trim();
                String pwd = pwd_input.getText().toString().trim();
//                if(Common.strQualified(this,pwd,6,1,"请输入密码！","密码至少6位！")){
//                    if(Common.strQualified(this,code,6,0,"请输入短信验证码！","短信验证码为6位！")){
//                        Common.setPassword(this,mQueue,retrieveListener,errorListener,uuid,code,pwd);
//                    }
//                }
                break;
        }
    }
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture,long countDownInterval){
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // 即使过程显示
            code_get_btn.setClickable(false);
            code_get_btn.setBackgroundResource(R.color.gray);
            code_get_btn.setText(millisUntilFinished/1000+"秒");

        }

        @Override
        public void onFinish() {
            code_get_btn.setText("获取验证码");
            code_get_btn.setBackgroundResource(R.color.main_theme_color);
            code_get_btn.setClickable(true);
        }
    }

}
