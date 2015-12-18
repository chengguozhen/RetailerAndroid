package com.neighbor.retailer_android.ui.activity.login;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.neighbor.retailer_android.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint("NewApi")
public class RegisterTab02Fragment extends Fragment implements View.OnClickListener{

    private View rootView;
    //商户名称
    private EditText shopName;
    //商户类型
    private Spinner shopKind;
    //负责人
    private EditText name;
    //负责人电话
    private EditText phone;
    //所在区域
    private Spinner area;
    //经营范围
    private Spinner range;
    //详细地址
    private EditText address;
    //证件类型
    private Spinner documentType;
    //证件号码
    private EditText documentNum;
    //上传图片按钮
    private Button uploadImg;
    //上传的图片
    private ImageView img;
    //上一步
    private Button preview;
    //立即注册
    private Button register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.register_two, container, false);

        shopName = (EditText)rootView.findViewById(R.id.edit_saler_shop_name);
        shopKind = (Spinner)rootView.findViewById(R.id.edit_saler_shop_kind);
        name = (EditText)rootView.findViewById(R.id.edit_saler_name);
        phone = (EditText)rootView.findViewById(R.id.edit_saler_phone);
        area = (Spinner)rootView.findViewById(R.id.edit_saler_area);
        range = (Spinner)rootView.findViewById(R.id.edit_sale_range);
        address = (EditText)rootView.findViewById(R.id.edit_saler_address);
        documentType = (Spinner)rootView.findViewById(R.id.edit_saler_document_type);
        documentNum = (EditText)rootView.findViewById(R.id.edit_saler_document_num);
        uploadImg = (Button)rootView.findViewById(R.id.upload_img_btn);
        img = (ImageView)rootView.findViewById(R.id.my_img);
        preview = (Button)rootView.findViewById(R.id.register_pre);
        register = (Button)rootView.findViewById(R.id.register_submit);
        uploadImg.setOnClickListener(this);
        preview.setOnClickListener(this);
        register.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upload_img_btn:
                //上传图片
                break;
            case R.id.register_pre:
                //跳转至上一个注册页面
                ((Step02ClickListener)getActivity()).onStep02BackClick();
                break;
            case R.id.register_submit:
                //判断提交注册
                ((Step02ClickListener)getActivity()).onStep02Click();
                break;
        }
    }

    /**
     * 设置按钮点击的回调
     */
    public interface Step02ClickListener {
        void onStep02Click();
        void onStep02BackClick();
    }

    /**
     * 检验输入的电话号码是否是有效的电话号码
     */
    public boolean isPhoneQualified(String phone) {
        Pattern p = Pattern.compile("^\\d{11}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}
