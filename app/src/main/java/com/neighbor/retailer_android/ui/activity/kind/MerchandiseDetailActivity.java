package com.neighbor.retailer_android.ui.activity.kind;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.MerchandiseDetailBean;
import com.neighbor.retailer_android.bean.ResponseBean;
import com.neighbor.retailer_android.bean.ResponseInnerBean;
import com.neighbor.retailer_android.common.Common;
import com.neighbor.retailer_android.common.utils.JsonUtil;
import com.neighbor.retailer_android.common.utils.MToast;
import com.neighbor.retailer_android.ui.adapter.MerPagerAdapter;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarHeader;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarListener;
import com.neighbor.retailer_android.util.AnimateFirstDisplayListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MerchandiseDetailActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * Toolbar
     */
    private View view;

    /**
     * 商品图片控件
     */
    private ViewPager merPager;

    /**
     * 商品图片地址存储
     */
    private List<String> merImageUrl;
    /**
     * 商品图片view List
     */
    private List<View> merImage;
    /**
     * 小圆点控件
     */
    private ViewGroup viewGroup;
    /**
     * 小圆点图片，初始化
     */
    private ImageView[] circle;
    /**
     * 商品图片适配器
     */
    private MerPagerAdapter merPagerAdapter;
    /**
     * 是否自动播放：默认开启
     */
    private boolean isAuto = true;
    /**
     * 当前播放位置
     */
    private AtomicInteger merPosition = new AtomicInteger(0);

    /**
     * 每一个播放时长
     */
    private int playDuration = 3000;

    /**
     * ImageView控件设定图片需要
     */
    DisplayImageOptions options;
    public ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    protected ImageLoader imageLoader= ImageLoader.getInstance();

    /**
     * 商品详情显示的具体信息控件
     */
    private TextView merchandiseName;
    private TextView merchandiseCellPrice,merchandiseUnitPrice;
    private TextView merchandiseSpecification;
    private TextView createdTime,qualityPeriod;
    private TextView category;
    private TextView salesNum,inventoryNum;
    private TextView purchaseNum;
    private ImageView add,substract;
    private TextView totalPrice;
    private Button addCart,pay;
    /**
     * 起批数
     */
    private int batchNum = 0;

    private void initView()
    {
        merchandiseName = (TextView)findViewById(R.id.merchandise_name);
        merchandiseCellPrice = (TextView)findViewById(R.id.merchandise_cells_price);
        merchandiseUnitPrice = (TextView)findViewById(R.id.merchandise_unit_price);
        merchandiseSpecification = (TextView)findViewById(R.id.merchandise_specification);
        createdTime = (TextView)findViewById(R.id.generate_tv);
        qualityPeriod = (TextView)findViewById(R.id.quality_period_tv);
        category = (TextView)findViewById(R.id.merchandise_category);
        salesNum = (TextView)findViewById(R.id.merchandise_sale_num);
        inventoryNum = (TextView)findViewById(R.id.merchandise_inventory_num);
        purchaseNum = (TextView)findViewById(R.id.purchase_number);
        add = (ImageView)findViewById(R.id.add_merchandise);
        substract = (ImageView)findViewById(R.id.substract_merchandise);
        totalPrice = (TextView)findViewById(R.id.merchandise_total_price);
        addCart = (Button)findViewById(R.id.purchase_btn);
        pay = (Button)findViewById(R.id.pay_btn);
        add.setOnClickListener(this);
        substract.setOnClickListener(this);
        addCart.setOnClickListener(this);
        pay.setOnClickListener(this);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchandise_detail);

        initView();
        initToolbar();
        initPagerAdapter();
        initData();
    }

    private void initToolbar()
    {
        /**
         * toolbar title
         */
        view = findViewById(R.id.merchandise_detail_header);
        if(view != null)
        {
            Toolbar toolbar = (Toolbar)view;
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            MyToolbarHeader toolbarHeader = new MyToolbarHeader(this,toolbar);
            toolbarHeader.setHeaderTitle(getString(R.string.merchandisedetail_title));
            MyToolbarListener listener = new MyToolbarListener() {
                @Override
                public void addNavigation() {
                    finish();
                }
            };
            toolbarHeader.setNavigation(R.mipmap.back,listener);
            //toolbarHeader.setSearchMenu();
        }
    }

    private void initData()
    {
        Common.merchandiseDetail(this,mhandler,"20151225143006");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.add_merchandise:
                batchNum += batchNum;
                break;
            case R.id.substract_merchandise:
                batchNum -= batchNum;
                break;
            case R.id.purchase_btn:

                break;
            case R.id.pay_btn:

                break;
            default:
                break;
        }
    }

    Handler mhandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case Common.merchandiseDetailSuccess:
                    MToast.show(MerchandiseDetailActivity.this,"merchandise detail success.");
                    ResponseBean responseBean = (ResponseBean)msg.obj;
                    ResponseInnerBean innerBean = JsonUtil.jsonToObj(JsonUtil.objToJson(responseBean.getResult()),ResponseInnerBean.class);
                    MerchandiseDetailBean merchandiseDetailBean = JsonUtil.jsonToObj(JsonUtil.objToJson(innerBean.getResult()), MerchandiseDetailBean.class);
                    MToast.show(MerchandiseDetailActivity.this,merchandiseDetailBean.getGoodName());
                    break;
                case Common.merchandiseDetailFailed:
                    MToast.show(MerchandiseDetailActivity.this,"merchandise detail failed.");
                    break;

            }
        }
    };


    private void initPagerAdapter()
    {
        options =new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        merPager = (ViewPager)findViewById(R.id.mer_pager);
        merImage = new ArrayList<>();
        viewGroup = (ViewGroup)findViewById(R.id.circle_spot);
        merImageUrl = new ArrayList<>();

        //商品图片的地址存在merImageUrl中
        merImageUrl.add("http://img5.imgtn.bdimg.com/it/u=2805319622,2331480379&fm=21&gp=0.jpg");
        merImageUrl.add("http://img1.imgtn.bdimg.com/it/u=121431623,2260515080&fm=21&gp=0.jpg");
        merImageUrl.add("http://g.hiphotos.baidu.com/zhidao/pic/item/960a304e251f95ca971dc905cd177f3e6609529c.jpg");
        merImageUrl.add("http://a.hiphotos.baidu.com/zhidao/pic/item/9f510fb30f2442a7e1dc64f4d543ad4bd0130294.jpg");
        merImageUrl.add("http://f.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=9307d2e77ad98d1076810435140f9438/503d269759ee3d6de821c3d045166d224e4adec2.jpg");
        //将每个Url绑定到ImageView上面
        initImageview();
        //初始化小圆点信息
        circle = new ImageView[merImageUrl.size()];
        for(int i=0; i<merImageUrl.size(); i++)
        {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20,20));
            imageView.setPadding(5,5,5,5);
            circle[i] = imageView;
            if(i==0)
            {
                circle[i].setBackgroundResource(R.mipmap.add);
            }
            else {
                circle[i].setBackgroundResource(R.mipmap.substract);
            }
            //将Imageview小圆点添加到布局中
            viewGroup.addView(circle[i]);
        }

        merPagerAdapter = new MerPagerAdapter(this,merImage);
        merPager.setAdapter(merPagerAdapter);
        merPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < circle.length; i++) {
                    circle[position].setBackgroundResource(R.mipmap.add);
                    if (i != position) {
                        circle[i].setBackgroundResource(R.mipmap.substract);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        merPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isAuto = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isAuto = true;
                        break;
                }
                return true;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    //自动播放执行翻页操作
                    if(isAuto)
                    {
                        //仅发送一个int值
                        merchandiseHandler.sendEmptyMessage(merPosition.get());
                        merPosition.incrementAndGet();
                        if(merPosition.get() > (merImageUrl.size()-1))
                        {
                            merPosition.getAndAdd((-merImageUrl.size()));
                        }
                    }
                    try {
                        Thread.sleep(playDuration);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 用handler来改变播放广告
     */
    private Handler merchandiseHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //设置当前第几个广告
            merPager.setCurrentItem(msg.what);
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
        for(int i=0; i<merImageUrl.size(); i++) {
            ImageView view = new ImageView(this);
            view.setScaleType(ImageView.ScaleType.CENTER);
            imageLoader.displayImage(merImageUrl.get(i), view, options, animateFirstListener);
            view.setTag(/*merImageUrl.get(i)*/i+"");
            merImage.add(view);
        }
        return merImage;
    }
}
