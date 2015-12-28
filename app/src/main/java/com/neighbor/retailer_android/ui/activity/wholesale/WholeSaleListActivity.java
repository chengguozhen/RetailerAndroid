package com.neighbor.retailer_android.ui.activity.wholesale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.ResponseBean;
import com.neighbor.retailer_android.bean.WholeSale;
import com.neighbor.retailer_android.common.Common;
import com.neighbor.retailer_android.common.utils.JsonUtil;
import com.neighbor.retailer_android.ui.adapter.WholeSaleAdapter;
import com.neighbor.retailer_android.ui.view.FtLoadingDialog;
import com.neighbor.retailer_android.ui.view.pulltorefresh.XListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WholeSaleListActivity extends Activity implements View.OnClickListener,XListView.IXListViewListener{

    /* 返回键 */
    private ImageButton back;
    /**
     * listview
     */
    private XListView saleListView = null;
    /* 数据源 */
    private List<WholeSale> mList = new ArrayList<WholeSale>();
    /* 适配器 */
    private WholeSaleAdapter adapter;
    /**
     * 未连接网络提示布局
     */
    private LinearLayout noNetWork;
    /**
     * listview布局
     */
    private LinearLayout listviewLayout;
    /**
     * 网络错误，重新加载按钮
     */
    private Button doLoddingBtn;
    /**
     *  loading 对话框
     *  */
    private FtLoadingDialog dialog;
    /* 搜索关键词 */
    private String key;
    /* 标题 */
    private TextView title;
    private int mRefreshIndex = 0;
    private int page = 10;

    /**
     * show loading 对话框
     * @param loadingText
     */
    private void showDialog(String loadingText){
        dialog = new FtLoadingDialog(this,loadingText);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    /**
     * 关闭 loading对话框
     */
    private void dismissDialog(){
        if(dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case Common.wholeSaleListSuccess:
                    ResponseBean responseBean = (ResponseBean) msg.obj;
                    try {
                        if(noNetWork.getVisibility() == View.VISIBLE){
                            noNetWork.setVisibility(View.GONE);
                            listviewLayout.setVisibility(View.VISIBLE);
                        }
                        JSONObject jsonObject = new JSONObject(JsonUtil.objToJson(responseBean.getResult()));
                        if(!jsonObject.isNull("rows")){
                            JSONArray array = jsonObject.getJSONArray("rows");
                            List<WholeSale> data = JsonUtil.jsonToList(array.toString(), WholeSale.class);
                            for(int i = 0;i < data.size();i++){
                                mList.add(data.get(i));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dismissDialog();
                    onLoad();
                    break;
                case Common.wholeSaleListFailed:
                    //显示网络异常
                    if(noNetWork.getVisibility() == View.GONE){
                        noNetWork.setVisibility(View.VISIBLE);
                        listviewLayout.setVisibility(View.GONE);
                    }
                    dismissDialog();
                    onLoad();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_sale_list);

        key = getIntent().getStringExtra("KEY");
        back = (ImageButton)findViewById(R.id.wholesaler_back);
        title = (TextView)findViewById(R.id.wholesaler_title);
        title.setText("批发商列表");
        saleListView = (XListView) findViewById(R.id.whole_listview);
        listviewLayout = (LinearLayout)findViewById(R.id.whole_lv_layout);
        noNetWork=(LinearLayout)findViewById(R.id.whole_not_network);
        doLoddingBtn=(Button)findViewById(R.id.ms_tab_do_lodding_btn);
        noNetWork.setVisibility(View.GONE);
        doLoddingBtn.setOnClickListener(this);
        back.setOnClickListener(this);
        adapter = new WholeSaleAdapter(this,mList);
        saleListView.setAdapter(adapter);
        //根据key搜索出满足条件的批发商信息填充数据源
        for(int i = 0;i < 10;i++){
            WholeSale info = new WholeSale();
            info.setMidName("幸福便利店" + i);
            mList.add(info);
        }
        adapter.notifyDataSetChanged();
        //getMsListData(1);
        saleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String wsId = mList.get(position).getMidId();
                String name = mList.get(position).getMidName();
                Intent intent = new Intent(WholeSaleListActivity.this, WholeSaleDetailActivity.class);
                intent.putExtra("ID", wsId);
                intent.putExtra("NAME", name);
                startActivity(intent);
            }
        });
        //showDialog("正在加载数据......");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.wholesaler_back:
                finish();
                break;
            case R.id.ms_tab_do_lodding_btn:
                //重新加载逻辑
                getMsListData(1);
                break;
        }
    }

    /* 加载数据，更新数据源（mode=1重新加载，mode=0上拉加载） */
    private void getMsListData(int mode){
        if(mode==1){
            mRefreshIndex = 0;
            if(mList != null || !mList.isEmpty()){
                mList.clear();
            }
        }
        Common.wholeSaleList(WholeSaleListActivity.this, mHandler, key, mRefreshIndex + "", page + "");
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        getMsListData(1);
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMore() {
        if(mList.size() >= 10) {
            mRefreshIndex++;
            getMsListData(0);
        }else{
            saleListView.stopLoadMore();
        }
    }


    /**
     * 结束刷新或加载
     */
    private void onLoad() {
        saleListView.stopRefresh();
        saleListView.stopLoadMore();
        saleListView.setRefreshTime(getTime());
    }

    /**
     * @return 时间日期
     */
    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

}
