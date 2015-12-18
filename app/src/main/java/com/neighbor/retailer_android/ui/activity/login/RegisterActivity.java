package com.neighbor.retailer_android.ui.activity.login;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.neighbor.retailer_android.R;


public class RegisterActivity extends Activity implements View.OnClickListener,RegisterTab01Fragment.Step01ClickListener,RegisterTab02Fragment.Step02ClickListener {

    private TextView title;
    private ImageButton doBack;
    private RegisterTab01Fragment step01;
    private RegisterTab02Fragment step02;
    //记录注册的第几步
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        if (savedInstanceState == null) {
            step01 = new RegisterTab01Fragment();
            step02 = new RegisterTab02Fragment();

            tx.add(R.id.register_steps, step01, "step01");
            tx.add(R.id.register_steps, step02, "step02");
            tx.commit();
        }else{
            step01 = (RegisterTab01Fragment) getFragmentManager().findFragmentByTag("step01");
            step02 = (RegisterTab02Fragment) getFragmentManager().findFragmentByTag("step02");
        }
        init();
    }

    public void init(){
        title = (TextView) findViewById(R.id.login_title);
        title.setText("注册");
        doBack = (ImageButton) findViewById(R.id.login_back);
        doBack.setOnClickListener(this);
        setFragment(1);
        position = 1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            switch (position) {
                case 1:
                    this.finish();
                    break;
                case 2:
                    setFragment(1);
                    position = 1;
                    break;
            }
        }
        return false;
    }

    @Override
    public void onStep01Click(String name, String phoneNumber, String email, String pwd) {
        //设置step02需要的数据
        setFragment(2);
        position = 2;
    }

    @Override
    public void onStep02Click() {
        //判断数据并进行注册
        this.finish();
    }

    @Override
    public void onStep02BackClick() {
        //上一步逻辑
        setFragment(1);
        position = 1;
    }

    /**
     * 设置显示第几步对应的Fragment
     *
     * @param position 注册的第几步
     */
    public void setFragment(int position) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        switch (position) {
            case 1:
                tx.show(step01);
                tx.hide(step02);
                break;
            case 2:
                tx.hide(step01);
                tx.show(step02);
                break;
        }
        tx.commit();
    }

}
