package com.neighbor.retailer_android.ui.activity.login;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.common.Common;
import com.neighbor.retailer_android.common.utils.MToast;

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

    private Context context;

    /*
    {
      "id": "openapi_user_resgister",
      "result": {
        "result": null,
        "msg": "请随时关注个人中心,查看审核状态,谢谢",
        "success": true
      },
      "errMsg": "Success",
      "errCd": 0,
      "trId": "Hjlk_openapi_user_resgister_1451018002897"
    }
     */

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

        context = getActivity();

        return rootView;
    }

    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case Common.register_susccess:
                    MToast.show(context,"success");
                    break;
                case Common.register_failed:
                    MToast.show(context,"failed");
                    break;

            }
        }
    };

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
                Common.register(getActivity(),mHandler,"yyj","456879","123","yyFirm","firmType",
                        "sf", "126165165115","dsfoinsd","sdf","dfs","identityCard",
                                "4946545466948464","54546161@qq.com");
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
