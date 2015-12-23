package com.neighbor.retailer_android.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.ui.activity.home.newdiscount.MerchandiseNewActivity;
import com.neighbor.retailer_android.ui.activity.home.notice.NoticeListActivity;
import com.neighbor.retailer_android.ui.activity.home.newdiscount.MerchandiseDiscountActivity;
import com.neighbor.retailer_android.ui.activity.my.AddressEditActivity;
import com.neighbor.retailer_android.ui.activity.my.MyIdentityActivity;
import com.neighbor.retailer_android.ui.activity.my.NewAddressActivity;
import com.neighbor.retailer_android.ui.adapter.AdvPagerAdapter;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarHeader;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarListener;
import com.neighbor.retailer_android.util.AnimateFirstDisplayListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressLint("NewApi")
public class HomeTabFragment extends Fragment implements View.OnClickListener{

    //toolbar
    private View home,homeHeader;
    /**
     * 通知公告 优惠商品 新品上市
     */
    private ImageView noticeImage,discountImage,newMerchandiseImage;

    //我的名片
    //private Button identity;

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

    private List<View> advView;
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

    DisplayImageOptions options;
    public ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    protected ImageLoader imageLoader= ImageLoader.getInstance();

    /**
     * 每日限时抢 计时器
     */
    private Timer timer;
    /**
     * 小时 分钟 秒数
     */
    private TextView second,minute,hour;
    private int hourInt,minuteInt,secondInt;

    private TextView endTv;

    /**
     * 比onCreateView先调用, menu关联
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        home = inflater.inflate(R.layout.main_tab_01, container, false);
        initToolbar();
        initView();
        initAdvAdapter();
        startTimer();
        return home;
    }

    private void initToolbar()
    {
        homeHeader = home.findViewById(R.id.home_header);
        if(homeHeader != null)
        {
            Toolbar toolbar = (Toolbar)homeHeader;
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            MyToolbarHeader toolbarHeader = new MyToolbarHeader(getActivity(),toolbar);
            toolbarHeader.setHeaderTitle(/*getString(R.string.)*/"首页");
            toolbarHeader.setSearchMenu();
            MyToolbarListener listener = new MyToolbarListener() {
                @Override
                public void addNavigation() {
                //定位
                Toast.makeText(getActivity(), "定位", Toast.LENGTH_SHORT).show();
                    /*Intent address = new Intent(getActivity(), AddressEditActivity.class);
                    startActivity(address);*/
                }
            };
            toolbarHeader.setNavigation(R.mipmap.add, listener);
            //toolbarHeader.setSearchMenu();
        }
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
        /*
        identity = (Button)home.findViewById(R.id.my_identity_btn);
        identity.setOnClickListener(this);
        */
        hour = (TextView)home.findViewById(R.id.hour);
        minute = (TextView)home.findViewById(R.id.minute);
        second = (TextView)home.findViewById(R.id.second);
        endTv = (TextView)home.findViewById(R.id.merchandise_end);
        endTv.setVisibility(View.INVISIBLE);
    }

    /**
     *
     */
    private void initAdvAdapter()
    {
        options =new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        advView = new ArrayList<View>();
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

        //初始化广告viewlist
        advView = initImageview();

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

        advPagerAdapter = new AdvPagerAdapter(getActivity(),advView);
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

    /**
     * 加载网络图片
     *
     * @return
     */
    private List<View> initImageview()
    {
        //LayoutInflater.from(getActivity()).inflate(R.layout.adv_image_layout,get,true);
        for(int i=0; i<imageUrl.size(); i++) {
            ImageView view = new ImageView(getActivity());
            view.setScaleType(ImageView.ScaleType.CENTER);
            imageLoader.displayImage(imageUrl.get(i), view, options, animateFirstListener);
            view.setTag(imageUrl.get(i));
            advView.add(view);
        }
        return advView;
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
            /*case R.id.my_identity_btn:
                Intent intentIdentity = new Intent(getActivity(), MyIdentityActivity.class);
                startActivity(intentIdentity);
                break;*/
            default:
                break;
        }

    }

    /**
     *  想要生效 必须先setHasOptionsMenu(true);
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        if(inflater!=null) {
            inflater.inflate(R.menu.contact_menu, menu);
        }
        else {
            getActivity().getMenuInflater().inflate(R.menu.search_menu,menu);
        }
    }

    /**
     * 开启倒计时
     */
    private void startTimer()
    {
        //需要先得到倒计时，Date形式？ 假设10：01：10
        final String hourStr = "00";
        final String minuteStr = "01";
        final String secondStr = "10";

        hour.setText(hourStr);
        minute.setText(minuteStr);
        second.setText(secondStr);
        timer = new Timer();
        hourInt = Integer.valueOf(hourStr);
        minuteInt = Integer.valueOf(minuteStr);
        secondInt = Integer.valueOf(secondStr);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 1000;
                timerHandler.sendMessage(msg);
            }
        };
        timer.schedule(timerTask,1000,1000);
    }

    private Handler timerHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 1000:
                    if(secondInt > 0)
                    {
                        secondInt--;
                        second.setText(secondInt+"");
                        if(secondInt <= 9)
                        {
                            second.setText("0"+secondInt);
                        }

                        /*if(minuteInt>0)
                        {
                            minuteInt--;
                            minute.setText(minuteInt+"");
                        }
                        else if(minuteInt == 0){
                            if(hourInt > 0)
                            {
                                hourInt--;
                                hour.setText(hourInt+"");
                            }
                            else if(hourInt == 0)
                            {}
                        }*/
                    }
                    else if(secondInt == 0)
                    {
                        if(minuteInt>0)
                        {
                            secondInt = 59;
                            second.setText(secondInt+"");
                            minuteInt--;
                            minute.setText(minuteInt+"");
                            if(minuteInt <= 9)
                            {
                                minute.setText("0"+minuteInt);
                            }
                        }
                        else if(minuteInt == 0){
                            if(hourInt > 0)
                            {
                                secondInt = 59;
                                second.setText(secondInt+"");
                                hourInt--;
                                hour.setText(hourInt+"");
                                if(hourInt <= 9)
                                {
                                    hour.setText("0"+hourInt);
                                }
                                minuteInt = 59;
                                minute.setText(minuteInt+"");
                            }
                            else if(hourInt == 0)
                            {
                                timer.cancel();
                                endTv.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    break;
                default:

                    break;
            }
        }
    };
}
