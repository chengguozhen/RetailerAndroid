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
import android.util.Log;
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

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
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
    private ImageView locView;
    private TextView cityTv;
    private ImageView contactView;

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

    /**
     * 提示每日限时抢已结束
     */
    private TextView endTv;

    /**
     * 百度定位信息
     */
    private LocationClient mLocationClient = null;
    private BDLocationListener myListener = new MyLocationListener();

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
        initLocation();
        return home;
    }

    private void initToolbar()
    {
        /*homeHeader = home.findViewById(R.id.home_header);
        if(homeHeader != null)
        {
            Toolbar toolbar = (Toolbar)homeHeader;
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            MyToolbarHeader toolbarHeader = new MyToolbarHeader(getActivity(),toolbar);
            toolbarHeader.setHeaderTitle(*//*getString(R.string.)*//*"首页");
            toolbarHeader.setSearchMenu();
            MyToolbarListener listener = new MyToolbarListener() {
                @Override
                public void addNavigation() {
                //定位
                mLocationClient.start();
                mLocationClient.requestLocation();
                }
            };
            toolbarHeader.setNavigation(R.mipmap.add, listener);
            //toolbarHeader.setSearchMenu();
        }*/

        homeHeader = home.findViewById(R.id.home_header);
        locView = (ImageView)homeHeader.findViewById(R.id.home_loc);
        cityTv = (TextView)homeHeader.findViewById(R.id.home_city);
        contactView = (ImageView)homeHeader.findViewById(R.id.home_contact);
        locView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocationClient.start();
                mLocationClient.requestLocation();
            }
        });
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

    /**
     * 初始化定位设置
     */
    private void initLocation()
    {
        mLocationClient = new LocationClient(getActivity());
        //注册监听
        mLocationClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        //设置定位模式：默认高精度  高精度 低功耗 仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //使用哪个坐标系 默认国测局gcj02
        option.setCoorType("bd0911");
        //设置多久定位一次
        int span = 1000*60;
        option.setScanSpan(span);

        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }

    /**
     * 定位信息接收
     *
     61 ： GPS定位结果，GPS定位成功。
     62 ： 无法获取有效定位依据，定位失败，请检查运营商网络或者wifi网络是否正常开启，尝试重新请求定位。
     63 ： 网络异常，没有成功向服务器发起请求，请确认当前测试手机网络是否通畅，尝试重新请求定位。
     65 ： 定位缓存的结果。
     66 ： 离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果。
     67 ： 离线定位失败。通过requestOfflineLocaiton调用时对应的返回结果。
     68 ： 网络连接失败时，查找本地离线定位时对应的返回结果。
     161： 网络定位结果，网络定位定位成功。
     162： 请求串密文解析失败。
     167： 服务端定位失败，请您检查是否禁用获取位置信息权限，尝试重新请求定位。
     502： key参数错误，请按照说明文档重新申请KEY。
     505： key不存在或者非法，请按照说明文档重新申请KEY。
     601： key服务被开发者自己禁用，请按照说明文档重新申请KEY。
     602： key mcode不匹配，您的ak配置过程中安全码设置有问题，请确保：sha1正确，“;”分号是英文状态；且包名是您当前运行应用的包名，请按照说明文档重新申请KEY。
     501～700：key验证失败，请按照说明文档重新申请KEY。
     *
     */
    public class  MyLocationListener implements BDLocationListener{
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            bdLocation.getLatitude();
            bdLocation.getLongitude();
            Log.i("home loction:", bdLocation.getLatitude() + "-" + bdLocation.getLongitude());
            Log.e("error code:",bdLocation.getLocType()+"");
            bdLocation.getTime();
            //错误码：
            bdLocation.getLocType();
            //GPS
            if(bdLocation.getLocType() == BDLocation.TypeGpsLocation)
            {
                bdLocation.getAddress();
                bdLocation.getAddrStr();
            }
            //Netword
            else if(bdLocation.getLocType() == BDLocation.TypeNetWorkLocation)
            {
                bdLocation.getAddrStr();
                bdLocation.getAddress();
            }
            else {}
            //Log.i("city",bdLocation.getAddress().city);
            if(bdLocation.getAddress()!=null)
            {
                cityTv.setVisibility(View.VISIBLE);
                cityTv.setText(bdLocation.getAddress().city);
            }
            else {
                cityTv.setVisibility(View.INVISIBLE);
            }
        }
    }
}
