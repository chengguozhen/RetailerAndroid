package com.neighbor.retailer_android.ui.activity.login;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.neighbor.retailer_android.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint("NewApi")
public class RegisterTab01Fragment extends Fragment implements View.OnClickListener{

    private EditText userName;
    private EditText userPhone;
    private EditText code;
    private EditText userMail;
    private EditText userPwd;
    private EditText userPwdSubmit;
    private Button getCode;
    private Button next;
    private View rootView;
    private String name;
    private String phoneNum;
    private String email;
    private String pwd;

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            //检验输入的手机号位数
            if (s.length() == 11) {   //11位 获取验证码按钮可点击
                getCode.setClickable(true);
                getCode.setBackgroundResource(R.drawable.click);
            } else {                  //不是11位 获取验证码按钮不可点击
                getCode.setClickable(false);
                getCode.setBackgroundResource(R.color.gray_background_color);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.register_one, container, false);
        userName = (EditText)rootView.findViewById(R.id.edit_register_user_id);
        userPhone = (EditText)rootView.findViewById(R.id.edit_register_phone);
        code = (EditText)rootView.findViewById(R.id.edit_register_code);
        userMail = (EditText)rootView.findViewById(R.id.edit_register_mail);
        userPwd = (EditText)rootView.findViewById(R.id.edit_register_pwd);
        userPwdSubmit = (EditText)rootView.findViewById(R.id.edit_register_pwd_sub);
        getCode = (Button)rootView.findViewById(R.id.register_new_code_btn);
        next = (Button)rootView.findViewById(R.id.register_next);
        getCode.setOnClickListener(this);
        next.setOnClickListener(this);
        code.addTextChangedListener(watcher);

        //设置按钮不可点击时的颜色
        getCode.setBackgroundResource(R.color.gray_text_color);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_new_code_btn:
                //发送验证码
                break;
            case R.id.register_next:
                //跳转至下一个注册页面
                ((Step01ClickListener)getActivity()).onStep01Click(name,phoneNum,email,pwd);
                break;
        }
    }

    /**
     * 设置按钮点击的回调
     */
    public interface Step01ClickListener {
        void onStep01Click(String name,String phoneNumber,String email,String pwd);
    }

    /**
     * 检验输入的电话号码是否是有效的电话号码
     */
    public boolean isPhoneQualified(String phone) {
        Pattern p = Pattern.compile("^\\d{11}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    //验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture,long countDownInterval){
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // 即使过程显示
            getCode.setClickable(false);
            getCode.setBackgroundResource(R.color.gray);
            getCode.setText(millisUntilFinished/1000+"秒");

        }

        @Override
        public void onFinish() {
            getCode.setText("获取验证码");
            getCode.setBackgroundResource(R.color.main_theme_color);
            getCode.setClickable(true);
        }
    }
}
