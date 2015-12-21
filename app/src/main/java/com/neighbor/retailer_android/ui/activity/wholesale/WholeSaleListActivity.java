package com.neighbor.retailer_android.ui.activity.wholesale;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.WholeSale;
import com.neighbor.retailer_android.ui.adapter.WholeSaleAdapter;
import com.neighbor.retailer_android.ui.view.FtLoadingDialog;
import java.util.ArrayList;
import java.util.List;

public class WholeSaleListActivity extends Activity implements View.OnClickListener{

    private ImageButton back;
    /**
     * listview
     */
    private ListView saleListView = null;
    private List<WholeSale> mList = new ArrayList<WholeSale>();
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
    //搜索关键词
    private String key;
    private TextView title;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_sale_list);

        key = getIntent().getStringExtra("KEY");
        back = (ImageButton)findViewById(R.id.wholesaler_back);
        title = (TextView)findViewById(R.id.wholesaler_title);
        saleListView = (ListView) findViewById(R.id.whole_listview);
        listviewLayout = (LinearLayout)findViewById(R.id.whole_lv_layout);
        noNetWork=(LinearLayout)findViewById(R.id.whole_not_network);
        doLoddingBtn=(Button)findViewById(R.id.ms_tab_do_lodding_btn);
        noNetWork.setVisibility(View.GONE);
        doLoddingBtn.setOnClickListener(this);
        back.setOnClickListener(this);
        adapter = new WholeSaleAdapter(this,mList);
        saleListView.setAdapter(adapter);
        for(int i = 0;i < 10;i++){
            WholeSale info = new WholeSale();
            mList.add(info);
        }
        adapter.notifyDataSetChanged();
        title.setText(key);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.wholesaler_back:
                finish();
                break;
            case R.id.ms_tab_do_lodding_btn:
                //重新加载逻辑
                //getMsListData(1);
                break;
        }
    }
}
