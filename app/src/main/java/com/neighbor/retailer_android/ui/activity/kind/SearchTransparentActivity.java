package com.neighbor.retailer_android.ui.activity.kind;

import android.app.Activity;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.neighbor.retailer_android.R;

public class SearchTransparentActivity extends Activity implements View.OnClickListener{

    private ImageButton searchBtn;
    private EditText searchName;
    private LinearLayout back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //信息栏
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_seatch_transparent);

        initView();

    }

    /**
     * 初始化 搜索按钮 返回按钮 输入的搜索信息
     */
    private void initView()
    {
        searchBtn = (ImageButton)findViewById(R.id.search_btn);
        searchName = (EditText)findViewById(R.id.search_name_edit);
        back = (LinearLayout) findViewById(R.id.back);
        searchBtn.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.search_btn:
                if(isInputRight())
                {
                    //执行搜索操作

                }else {}
                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     *
     */
    private boolean isInputRight()
    {
        if(null == searchName.getText().toString() || searchName.getText().toString().equals(""))
        {
            return false;
        }
        return true;
    }
}
