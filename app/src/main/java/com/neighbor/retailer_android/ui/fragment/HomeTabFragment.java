package com.neighbor.retailer_android.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.ui.activity.home.notice.DiscountActivity;
import com.neighbor.retailer_android.ui.activity.home.notice.NewMerchandiseActivity;
import com.neighbor.retailer_android.ui.activity.home.notice.NoticeListActivity;
import com.neighbor.retailer_android.ui.activity.kind.MerchandiseListActivity;

@SuppressLint("NewApi")
public class HomeTabFragment extends Fragment implements View.OnClickListener{

    private View home;
    private ImageView noticeImage,discountImage,newMerchandiseImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        home = inflater.inflate(R.layout.main_tab_01, container, false);
        initView();
        return home;
    }

    private void initView()
    {
        noticeImage = (ImageView)home.findViewById(R.id.notice_image);
        noticeImage.setOnClickListener(this);
        discountImage = (ImageView)home.findViewById(R.id.notice_discount);
        discountImage.setOnClickListener(this);
        newMerchandiseImage = (ImageView)home.findViewById(R.id.notice_new_merchandise);
        newMerchandiseImage.setOnClickListener(this);
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
                intentDiscount.setClass(getActivity(), DiscountActivity.class);
                startActivity(intentDiscount);
                break;
            case R.id.notice_new_merchandise:
                Intent intentNew = new Intent();
                //intent.setClass(getActivity(), NewMerchandiseActivity.class);
                intentNew.setClass(getActivity(), MerchandiseListActivity.class);
                startActivity(intentNew);
                break;
        }

    }
}
