package com.neighbor.retailer_android.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.ui.activity.home.newdiscount.MerchandiseNewActivity;
import com.neighbor.retailer_android.ui.activity.home.notice.NoticeListActivity;
import com.neighbor.retailer_android.ui.activity.home.newdiscount.MerchandiseDiscountActivity;
import com.neighbor.retailer_android.ui.activity.kind.MerchandiseListActivity;
import com.neighbor.retailer_android.ui.activity.my.MyIdentityActivity;

@SuppressLint("NewApi")
public class HomeTabFragment extends Fragment implements View.OnClickListener{

    //toolbar
    private View home;
    /**
     * 通知公告 优惠商品 新品上市
     */
    private ImageView noticeImage,discountImage,newMerchandiseImage;

    //我的名片
    private Button identity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        home = inflater.inflate(R.layout.main_tab_01, container, false);
        initView();
        return home;
    }

    private void initView()
    {
        /**
         * 通知公告 优惠商品 新品上市
         */
        noticeImage = (ImageView)home.findViewById(R.id.notice_image);
        noticeImage.setOnClickListener(this);
        discountImage = (ImageView)home.findViewById(R.id.notice_discount);
        discountImage.setOnClickListener(this);
        newMerchandiseImage = (ImageView)home.findViewById(R.id.notice_new_merchandise);
        newMerchandiseImage.setOnClickListener(this);

        identity = (Button)home.findViewById(R.id.my_identity_btn);
        identity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.notice_image:
                Intent intent = new Intent();
                intent.setClass(getActivity(), NoticeListActivity.class);
                startActivity(intent);
                break;
            case R.id.notice_discount:
                Intent intentDiscount = new Intent();
                intentDiscount.setClass(getActivity(), MerchandiseDiscountActivity.class);
                startActivity(intentDiscount);
                break;
            case R.id.notice_new_merchandise:
                Intent intentNew = new Intent();
                intentNew.setClass(getActivity(), MerchandiseNewActivity.class);
                startActivity(intentNew);
                break;
            case R.id.my_identity_btn:
                Intent intentIdentity = new Intent(getActivity(), MyIdentityActivity.class);
                startActivity(intentIdentity);
                break;
            default:
                break;
        }

    }
}
