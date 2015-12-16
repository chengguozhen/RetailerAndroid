package com.neighbor.retailer_android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.neighbor.retailer_android.ui.fragment.HomeTabFragment;
import com.neighbor.retailer_android.ui.fragment.KindTabFragment;
import com.neighbor.retailer_android.ui.fragment.MyTabFragment;
import com.neighbor.retailer_android.ui.fragment.ShopCartTabFragment;
import com.neighbor.retailer_android.ui.fragment.WholeSaleTabFragment;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener,View.OnLongClickListener{

//    /**
//     * 从饭团tab界面跳转到main 时，通过aciton设置tab切换
//     */
//    public static final String FT_TAB_ACTION = "FT_TAB_TO_MAIN";
//
//    public static final String MY_TAB_ACTION = "MY_TAB_TO_MAIN";
//
//    public static final String FT_SHOW_DIALOG= "FT_SHOW_DIALOG";
    private String TAG="nihao";
    /**
     * tab页面 fragment ，添加各自的fragment时自行修改
     */
    private HomeTabFragment mTab01;
    private KindTabFragment mTab02;
    private WholeSaleTabFragment mTabMid;
    private ShopCartTabFragment mTab03;
    private MyTabFragment mTab04;

    /**
     * 底部四个按钮
     */
    private LinearLayout mTabBtnHome;
    private LinearLayout mTabBtnKind;
    private LinearLayout mTabBtnWSale;
    private LinearLayout mTabBtnShopCart;
    private LinearLayout mTabBtnMy;
    //private PushTestReceiver pushReceiver = new PushTestReceiver();
    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;
    private long mExitTime;

    public static final int UPDATA_CLIENT = 0;

    public  static final int VERSION_UPDATE_MUST = 1;
    public  static final int DOWN_ERROR = 2;

    //private RequestQueue mQueue;
    private String apkDownUrl = "";
    private boolean isUpdateMust = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        fragmentManager = getFragmentManager();
        setTabSelection(0);
        //PushManager.getInstance().initialize(this.getApplicationContext());
        //mQueue = Volley.newRequestQueue(this);
//        Common.getSystemVersion(mQueue, listener, errorListener,Common.VERSION_URL,"system/force_update");
        //Common.getSystemVersionOfAndroid(mQueue, listener, errorListener,Common.VERSION_URL,Common.URL_SYSTEM_VERSION);

        //L.i("MAIN-ONCREATE");
        showDialogActivity();
    }

    /**
     * 初始化控件
     */
    private void initViews() {

        mTabBtnHome = (LinearLayout) findViewById(R.id.id_tab_bottom_home);
        mTabBtnKind = (LinearLayout) findViewById(R.id.id_tab_bottom_meishi);
        mTabBtnWSale = (LinearLayout) findViewById(R.id.id_tab_bottom_pi);
        mTabBtnShopCart = (LinearLayout) findViewById(R.id.id_tab_bottom_fantuan);
        mTabBtnMy = (LinearLayout) findViewById(R.id.id_tab_bottom_my);

        mTabBtnHome.setOnClickListener(this);
        mTabBtnKind.setOnClickListener(this);
        mTabBtnWSale.setOnClickListener(this);
        mTabBtnShopCart.setOnClickListener(this);
        mTabBtnMy.setOnClickListener(this);
        //mTabBtnMy.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tab_bottom_home:
                setTabSelection(0);
                break;
            case R.id.id_tab_bottom_meishi:
                setTabSelection(1);
                break;
            case R.id.id_tab_bottom_pi:
                setTabSelection(2);
                break;
            case R.id.id_tab_bottom_fantuan:
                setTabSelection(3);
                break;
            case R.id.id_tab_bottom_my:
                setTabSelection(4);
                break;

            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     */
    @SuppressLint("NewApi")
    private void setTabSelection(int index) {
        // 重置按钮
        resetBtn();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnHome.findViewById(R.id.btn_tab_bottom_home))
                        .setImageResource(R.mipmap.main_home_press);
                if (mTab01 == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    mTab01 = new HomeTabFragment();
                    transaction.add(R.id.id_content, mTab01);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mTab01);
                }
                break;
            case 1:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnKind.findViewById(R.id.btn_tab_bottom_meishi))
                        .setImageResource(R.mipmap.main_meishi_press);
                if (mTab02 == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    mTab02 = new KindTabFragment();
                    transaction.add(R.id.id_content, mTab02,"meishi");
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mTab02);
                }
                break;
            case 2:
                //批发商
                ((ImageButton) mTabBtnWSale.findViewById(R.id.btn_tab_bottom_pi))
                        .setImageResource(R.mipmap.main_meishi_press);
                if (mTabMid == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    mTabMid = new WholeSaleTabFragment();
                    transaction.add(R.id.id_content, mTabMid,"pifashang");
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mTabMid);
                }
                break;
            case 3:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnShopCart.findViewById(R.id.btn_tab_bottom_fantuan))
                        .setImageResource(R.mipmap.main_fantuan_press);
                if (mTab03 == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    mTab03 = new ShopCartTabFragment();
                    transaction.add(R.id.id_content, mTab03,"gouwuche");
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(mTab03);
                }
                break;
            case 4:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnMy.findViewById(R.id.btn_tab_bottom_my))
                        .setImageResource(R.mipmap.main_my_press);
                if (mTab04 == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    mTab04 = new MyTabFragment();
                    transaction.add(R.id.id_content, mTab04);
                } else {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(mTab04);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void resetBtn() {
        ((ImageButton) mTabBtnHome.findViewById(R.id.btn_tab_bottom_home))
                .setImageResource(R.mipmap.main_home);
        ((ImageButton) mTabBtnKind.findViewById(R.id.btn_tab_bottom_meishi))
                .setImageResource(R.mipmap.main_meishi);
        ((ImageButton) mTabBtnWSale.findViewById(R.id.btn_tab_bottom_pi))
                .setImageResource(R.mipmap.ic_launcher);
        ((ImageButton) mTabBtnShopCart.findViewById(R.id.btn_tab_bottom_fantuan))
                .setImageResource(R.mipmap.main_fantuan);
        ((ImageButton) mTabBtnMy.findViewById(R.id.btn_tab_bottom_my))
                .setImageResource(R.mipmap.main_my);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    @SuppressLint("NewApi")
    private void hideFragments(FragmentTransaction transaction) {
        if (mTab01 != null) {
            transaction.hide(mTab01);
        }
        if (mTab02 != null) {
            transaction.hide(mTab02);
        }
        if(mTabMid != null){
            transaction.hide(mTabMid);
        }
        if (mTab03 != null) {
            transaction.hide(mTab03);
        }
        if (mTab04 != null) {
            transaction.hide(mTab04);
        }

    }

    /**
     * 1.将MainActivity launchMODE设置成singleTask模式
     * 2.此时任务栈中会执行类似Intent Flag Clear_Top操作
     * 3.通过inentAction判断需要跳转的界面
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        L.i("MAIN-onNewIntent");
//        setIntent(intent);//需要更新intent，若不设置，得到的是旧intent.No Why!
//        //通过intent Action 判断 需要
//        if (FT_TAB_ACTION .equals(getIntent().getAction())) {
//            setTabSelection(2);
//            L.i("Tab-FT-->" + getIntent().getAction());
//        }
//        if(MY_TAB_ACTION.equals(getIntent().getAction())){
//            setTabSelection(3);
//        }
//
//        if(FT_SHOW_DIALOG.equals(getIntent().getAction())){
//            //获取intent extra值
//            String groupCode=getIntent().getStringExtra(PushTestReceiver.GROUP_KEY);
//            String groupID=getIntent().getStringExtra(PushTestReceiver.GROUP_ID);
//            String inviteCode=getIntent().getStringExtra(PushTestReceiver.INVITE_CODE);
//
//            L.i("MAIN-onNewIntent-inviteCode"+inviteCode);
//            L.i("MAIN-onNewIntent-groupCode"+groupCode);
//
//        }
        showDialogActivity();
    }


    /**
     * 打开dialogActivity，推送通知，点击通知，打开MainActivity
     * 再打开dialogActivity
     */
    private void showDialogActivity(){
//        L.i("MAIN-showDialog");
//        Intent intent=getIntent();
//        L.i("MAIN-showDialog" + intent.getAction());
//        if(FT_SHOW_DIALOG.equals(intent.getAction())){
//            String groupCode=null;
//            String groupID=null;
//            String inviteCode=null;
//            String orderID=null;
//            switch (intent.getIntExtra(FtDialogActivity.SHOW_DAILOG_FLAG,0)) {
//
//                case FtDialogActivity.SHOW_INVITE_DIALOG:
//                    //获取intent extra值
//                    groupCode = intent.getStringExtra(PushTestReceiver.GROUP_KEY);
//                    groupID = intent.getStringExtra(PushTestReceiver.GROUP_ID);
//                    inviteCode = intent.getStringExtra(PushTestReceiver.INVITE_CODE);
//
//                    //新打开activity 并赋值extra
//                    Intent showInviteIntent = new Intent(this, FtDialogActivity.class);
//                    showInviteIntent.putExtra(PushTestReceiver.GROUP_KEY, groupCode);
//                    showInviteIntent.putExtra(PushTestReceiver.GROUP_ID, groupID);
//                    showInviteIntent.putExtra(PushTestReceiver.INVITE_CODE, inviteCode);
//                    showInviteIntent.putExtra(FtDialogActivity.SHOW_DAILOG_FLAG, FtDialogActivity.SHOW_INVITE_DIALOG);
//                    startActivity(showInviteIntent);
//                    break;
//
//                case FtDialogActivity.SHOW_ADD_DISH_DIALOG:
//                    Intent showAddDishIntent = new Intent(this, FtDialogActivity.class);
//                    //获取intent extra值
//                    groupCode = intent.getStringExtra(PushTestReceiver.GROUP_KEY);
//                    groupID = intent.getStringExtra(PushTestReceiver.GROUP_ID);
//                    orderID = intent.getStringExtra(PushTestReceiver.ORDER_KEY);
//
//                    showAddDishIntent.putExtra(FtDialogActivity.SHOW_DAILOG_FLAG, FtDialogActivity.SHOW_ADD_DISH_DIALOG);
//                    showAddDishIntent.putExtra(PushTestReceiver.GROUP_ID, groupID);
//                    showAddDishIntent.putExtra(PushTestReceiver.GROUP_KEY, groupCode);
//                    showAddDishIntent.putExtra(PushTestReceiver.ORDER_KEY, orderID);
//
//                    startActivity(showAddDishIntent);
//                    break;
//
//                case FtDialogActivity.SHOW_BING_TABLE_DIALOG:
//                    Intent bindTableIntent = new Intent(this, FtDialogActivity.class);
//                    //获取intent extra值
//                    groupCode = intent.getStringExtra(PushTestReceiver.GROUP_KEY);
//                    orderID = intent.getStringExtra(PushTestReceiver.ORDER_KEY);
//
//                    bindTableIntent.putExtra(FtDialogActivity.SHOW_DAILOG_FLAG, FtDialogActivity.SHOW_BING_TABLE_DIALOG);
//                    bindTableIntent.putExtra(PushTestReceiver.GROUP_KEY, groupCode);
//                    bindTableIntent.putExtra(PushTestReceiver.ORDER_KEY, orderID);
//
//                    startActivity( bindTableIntent);
//                    break;
//
//                case FtDialogActivity.SHOW_CANCEL_NOTIFY_TABLE_DIALOG:
//                    Intent toCancelNotify = new Intent(this, FtDialogActivity.class);
//                    toCancelNotify.putExtra(FtDialogActivity.SHOW_DAILOG_FLAG, FtDialogActivity.SHOW_CANCEL_NOTIFY_TABLE_DIALOG);
//                    toCancelNotify.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    this.startActivity(toCancelNotify);
//                    break;
//
//                case  FtDialogActivity.SHOW_CANCEL_TABLE_DIALOG:
//                    Intent toDialogActivity = new Intent(this, FtDialogActivity.class);
//                    toDialogActivity.putExtra(FtDialogActivity.SHOW_DAILOG_FLAG ,FtDialogActivity.SHOW_CANCEL_TABLE_DIALOG);
//                    toDialogActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    this.startActivity(toDialogActivity);
//                    break;
//
//
//            }
//
//        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Object mHelperUtils;
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();

                } else {
                    finish();
//                FanTuanApplication.getInstance().exit();
                }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*
        * 获取当前程序的版本号
        */
    private String getVersionName() throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        return packInfo.versionName;
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATA_CLIENT:
                    //对话框通知用户升级程序
                    showUpdataDialog();
                    break;
                case VERSION_UPDATE_MUST:
                    //版本大升级，必须更新
                    isUpdateMust = true;
                    //int version_new = Integer.parseInt(Common.VERSION_URL)+1;
                    //Common.getSystemVersionOfAndroid(mQueue, listener, errorListener, version_new+"",Common.URL_SYSTEM_VERSION);
                    Log.i("version", "获取服务器更新信息失败");
                    break;
                case DOWN_ERROR:
                    //下载apk失败
                    Log.i("downApk", "下载新版本失败!");
                    break;
            }
        }
    };

    //安装apk
    protected void installApk(File file) {

        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    /*
    * 从服务器中下载APK
    */
    protected void downLoadApk() {
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        new Thread() {
            @Override
            public void run() {
                try {
//                    File file = Common.getFileFromServer(apkDownUrl, pd);
//                    sleep(3000);
//                    if (file == null) {
//                        pd.dismiss();
//                        Message msg = new Message();
//                        msg.what = DOWN_ERROR;
//                        mHandler.sendMessage(msg);
//                    } else {
//                        installApk(file);
//                        pd.dismiss(); //结束掉进度条对话框
//                    }
                } catch (Exception e) {
                    Message msg = new Message();
                    msg.what = DOWN_ERROR;
                    mHandler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.start();
    }

    protected void showUpdataDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("版本升级");
        builder.setMessage(apkDownUrl);
        //当点确定按钮时从服务器上下载 新的apk 然后安装
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                downLoadApk();
                dialog.dismiss();
            }
        });
        if(!isUpdateMust) {
            //当点取消按钮时进行登录
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.id_tab_bottom_my:
                openLogActivity();
                break;
            default:
                break;
        }
        return false;
    }

    private void openLogActivity(){
        //Intent intent = new Intent(this, LogActivity.class);
        //startActivity(intent);
    }
}
