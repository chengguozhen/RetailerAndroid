package com.neighbor.retailer_android.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.ui.activity.home.newdiscount.MerchandiseNewActivity;
import com.neighbor.retailer_android.ui.activity.home.notice.NoticeListActivity;
import com.neighbor.retailer_android.ui.activity.home.newdiscount.MerchandiseDiscountActivity;
import com.neighbor.retailer_android.ui.activity.my.MyIdentityActivity;
import com.neighbor.retailer_android.ui.adapter.AdvPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    /**
     * 广告图存储
     */
    private ViewPager advPager;

    /**
     * 小圆点存储
     */
    private ImageView[] circleSpot;

    /**
     * 广告适配器
     */
    private AdvPagerAdapter advPagerAdapter;

    /**
     * 图片的url List
     */
    private List<String> imageUrl;
    /**
     * 自动播放广告
     */
    private boolean isAuto = true;

    /**
     * 从0开始
     * 用来确定当前播放哪个广告
     */
    private AtomicInteger advPosition = new AtomicInteger(0);

    private int playDuration = 3000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        home = inflater.inflate(R.layout.main_tab_01, container, false);
        initView();
        initAdvAdapter();
        return home;
    }

    /**
     * 初始化基本控件
     */
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

    /**
     *
     */
    private void initAdvAdapter()
    {
        advPager = (ViewPager)home.findViewById(R.id.adv_pager);
        //小圆点控件
        ViewGroup viewGroup = (ViewGroup)home.findViewById(R.id.circle_spot);
        imageUrl = new ArrayList<String>();
        //广告的url
        imageUrl.add("http://img5.imgtn.bdimg.com/it/u=2805319622,2331480379&fm=21&gp=0.jpg");
        imageUrl.add("http://img1.imgtn.bdimg.com/it/u=121431623,2260515080&fm=21&gp=0.jpg");
        imageUrl.add("http://g.hiphotos.baidu.com/zhidao/pic/item/960a304e251f95ca971dc905cd177f3e6609529c.jpg");
        imageUrl.add("http://a.hiphotos.baidu.com/zhidao/pic/item/9f510fb30f2442a7e1dc64f4d543ad4bd0130294.jpg");
        imageUrl.add("http://f.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=9307d2e77ad98d1076810435140f9438/503d269759ee3d6de821c3d045166d224e4adec2.jpg");

        circleSpot = new ImageView[imageUrl.size()];
        for(int i=0; i<imageUrl.size(); i++)
        {
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20,20));
            imageView.setPadding(5,5,5,5);
            circleSpot[i] = imageView;
            //最开始是第一个广告的小圆点加重
            if(i==0)
            {
                circleSpot[i].setBackgroundResource(R.mipmap.add);
            }
            else {
                circleSpot[i].setBackgroundResource(R.mipmap.substract);
            }
            //将Imageview小圆点添加到布局中
            viewGroup.addView(circleSpot[i]);
        }

        advPagerAdapter = new AdvPagerAdapter(getActivity(),imageUrl);
        advPager.setAdapter(advPagerAdapter);
        //设置小圆点变化监听
        advPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0; i<circleSpot.length ;i++)
                {
                    circleSpot[position].setBackgroundResource(R.mipmap.add);
                    if(position!=i)
                    {
                        circleSpot[i].setBackgroundResource(R.mipmap.substract);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置自动滑动和手动改变监听
        advPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isAuto = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isAuto = true;
                        break;
                }
                return false;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    if (isAuto)
                    {
                        advViewHandler.sendEmptyMessage(advPosition.get());
                        //加一操作 相对的减少decrementAndGet
                        advPosition.incrementAndGet();
                        //如果循环到最后一个4，下一个是5
                        if(advPosition.get() > (imageUrl.size() -1))
                        {
                            //回到最初的第一个位置
                            //getAndAdd 经括号中的定值与当前值相加
                            advPosition.getAndAdd(-imageUrl.size());
                        }
                        try {
                            Thread.sleep(playDuration);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    /**
     * 用handler来改变播放广告
     */
    private Handler advViewHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //设置当前第几个广告
            advPager.setCurrentItem(msg.what);
            super.handleMessage(msg);
        }
    };

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
