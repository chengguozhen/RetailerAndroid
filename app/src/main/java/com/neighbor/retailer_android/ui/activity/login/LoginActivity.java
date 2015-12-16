package com.neighbor.retailer_android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class LoginActivity extends Activity implements View.OnClickListener {

    private TextView title;            //title bar显示的文字
    private Button login;              //登录按钮
    private EditText user_id;          //用户名（电话）输入框
    private EditText pwd;              //密码输入框
    private TextView register;         //注册
    private ImageButton doBack;        //title bar 的返回按钮
    private TextView pwd_retrieve;     // title bar 找回密码

    private boolean timerState = false;
    private Timer timer;
    private TimerTask task;

    private String phone;
    private String password;

    public static final int DEVID_NO = 0;
    public static final int DEVID_YES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        init();

    }


    public void init() {
        user_id = (EditText) findViewById(R.id.edit_login_user_id);
        pwd = (EditText) this.findViewById(R.id.edit_login_pwd);
        register = (TextView) this.findViewById(R.id.register);
        pwd_retrieve = (TextView) this.findViewById(R.id.pwd_retrieve);
        doBack = (ImageButton) this.findViewById(R.id.login_back);
        login = (Button) this.findViewById(R.id.login);

        title = (TextView) this.findViewById(R.id.login_title);
        //设置title bar的显示内容
        title.setText(this.getResources().getText(R.string.login_notice));
        pwd_retrieve.setText(this.getResources().getText(R.string.login_notice_password_retrieve));

        login.setOnClickListener(this);
        doBack.setOnClickListener(this);
        pwd_retrieve.setOnClickListener(this);
        register.setOnClickListener(this);

        //mQueue = Volley.newRequestQueue(this);
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case DEVID_NO:
                    //showDialog("请稍候");
                    startTimer();
                    break;
                case DEVID_YES:
                    //Common.doLogin(LoginActivity.this, mQueue, loginListener, errorListener, phone, password);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.login:
                phone = user_id.getText().toString();
                password = pwd.getText().toString().trim();
                //判断输入是否合法
                if (checkPhonePwd(phone, password)) {
//                    String devid = (String) SPUtil.get(this, Common.LOGIN_TOKEN, Common.KEY_DEVID, "");
//                    if (devid.equals("")) {
//                        Log.i("devid", "没有devid");
//
//                        Message msg = new Message();
//                        msg.what = DEVID_NO;
//                        mHandler.sendMessage(msg);
//
//
//                    } else {
//                        Message msg = new Message();
//                        msg.what = DEVID_YES;
//                        mHandler.sendMessage(msg);
//                    }
                }
                break;
            case R.id.login_back:
                //title bar返回键
                this.finish();
                break;
            case R.id.pwd_retrieve:
                //title bar找回密码
                //startActivity(new Intent(this, RetrievePwdActivity.class));
                break;
            case R.id.register:
                //注册
                //startActivity(new Intent(this, RegisterActivity.class));
                break;

        }

    }

//    private void showDialog(String loadingText) {
//        dialog = new FtLoadingDialog(this, loadingText);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener(){
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                cancelTimer();
//            }
//        });
//        dialog.show();
//
//    }

    /**
     * 关闭 loading对话框
     */
//    private void dismissDialog() {
//        if (dialog != null && dialog.isShowing()) {
//            dialog.dismiss();
//        }
//    }

    /**
     * 检验输入的电话和密码
     *
     * @param uPhone 电话号码
     * @param uPhone 密码
     * @return true合格 / false不合格
     */
    public boolean checkPhonePwd(String uPhone, String pwd) {

        if (uPhone.equals("") || pwd.equals("")) {//判断是否为空
            Toast.makeText(LoginActivity.this, LoginActivity.this.getResources().getString(R.string.login_input_empty), Toast.LENGTH_SHORT).show();
            return false;
        } else if (true) {//这里判断是否输入不合法字符
            return true;
        } else {
            Toast.makeText(LoginActivity.this, LoginActivity.this.getResources().getString(R.string.login_pwd_illegal), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.i("back","--------------------------");

            cancelTimer();
            //dismissDialog();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        cancelTimer();
        super.onDestroy();
    }

    public void startTimer(){

         task = new TimerTask() {
            public void run() {
//                String devid = (String) SPUtil.get(LoginActivity.this, Common.LOGIN_TOKEN, Common.KEY_DEVID, "");
//                Log.i("devid", devid);
//                if (!devid.equals("")) {
//                    dismissDialog();
//                    Message message = new Message();
//                    message.what = DEVID_YES;
//                    mHandler.sendMessage(message);
//                    timer.cancel();
//                }
            }
        };

        timer = new Timer();
        timer.schedule(task, 0, 500); //延时1000ms后执行，1000ms执行一次
        timerState = true;

    }


    public void cancelTimer(){
        Log.i("cancel timer","--------------------------");
        if(timer!=null){
            timer.cancel();
            timer = null;
        }
        if(task!=null){
            task.cancel();
            task=null;
        }
    }
}
