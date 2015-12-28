package com.neighbor.retailer_android.ui.activity.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.WholeSale;
import com.neighbor.retailer_android.ui.activity.shopcart.SubmitOrderActivity;
import com.neighbor.retailer_android.ui.adapter.MyAddressAdapter;
import com.neighbor.retailer_android.ui.view.FtLoadingDialog;

import java.util.ArrayList;
import java.util.List;

public class AddressListActivity extends Activity implements View.OnClickListener{

    /* 返回键 */
    private ImageButton back;
    /* 标题 */
    private TextView title;
    /* 添加地址按钮 */
    private ImageButton addAddress;
    /**
     * listview
     */
    private ListView addressListView = null;
    /* 数据源 */
    private List<WholeSale> mList = new ArrayList<WholeSale>();
    /* 适配器 */
    private MyAddressAdapter adapter;
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
    /* 跳转标志位 */
    private boolean flag = true;

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
        setContentView(R.layout.activity_address_list);

        flag = (boolean)getIntent().getBooleanExtra("FLAG", true);
        back = (ImageButton)findViewById(R.id.address_back);
        title = (TextView)findViewById(R.id.address_title);
        title.setText("地址管理");
        addAddress = (ImageButton)findViewById(R.id.address_add);
        addressListView = (ListView) findViewById(R.id.address_listview);
        listviewLayout = (LinearLayout)findViewById(R.id.address_lv_layout);
        noNetWork=(LinearLayout)findViewById(R.id.address_not_network);
        doLoddingBtn=(Button)findViewById(R.id.ms_tab_do_lodding_btn);
        noNetWork.setVisibility(View.GONE);
        doLoddingBtn.setOnClickListener(this);
        back.setOnClickListener(this);
        addAddress.setOnClickListener(this);
        adapter = new MyAddressAdapter(this,mList);
        addressListView.setAdapter(adapter);
        for(int i = 0;i < 10;i++){
            WholeSale info = new WholeSale();
            info.setMidName("幸福便利店" + i);
            mList.add(info);
        }
        adapter.notifyDataSetChanged();
        addressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(flag){
                    Intent intent = new Intent(AddressListActivity.this, SubmitOrderActivity.class);
                    intent.putExtra("ADDRESS","山东省 威海市 环翠区 世昌大道一号 青岛中路107-2号");
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    //String name = mList.get(position).getName();
                    //Intent intent = new Intent(AddressListActivity.this, AddressEditActivity.class);
                    //intent.putExtra("NAME", name);
                    //startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.address_back:
                if(flag){
                    Intent intent = new Intent(AddressListActivity.this, SubmitOrderActivity.class);
                    //传一个空地址（即未选中地址）
                    intent.putExtra("ADDRESS","");
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    finish();
                }
                break;
            case R.id.ms_tab_do_lodding_btn:
                //重新加载逻辑
                //getMsListData(1);
                break;
            case R.id.address_add:
                //跳转至添加地址页面
                Intent intent = new Intent(this,NewAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(flag){
            Intent intent = new Intent(AddressListActivity.this, SubmitOrderActivity.class);
            intent.putExtra("ADDRESS", "");
            setResult(RESULT_OK, intent);
            finish();
        }
        super.onBackPressed();
    }
}
