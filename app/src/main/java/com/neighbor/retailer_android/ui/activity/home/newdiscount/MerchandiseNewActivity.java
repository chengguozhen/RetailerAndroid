package com.neighbor.retailer_android.ui.activity.home.newdiscount;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.ui.activity.kind.SearchTransparentActivity;
import com.neighbor.retailer_android.ui.fragment.newmerchandise.MerchandiseNewCountFragment;
import com.neighbor.retailer_android.ui.fragment.newmerchandise.MerchandiseNewPriceFragment;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarHeader;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarListener;

public class MerchandiseNewActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchandise_new);
        initView();
        initFragment();
    }

    private View view;
    /**
     * 销量 价格排序
     */
    private Button count,price;

    /**
     * 两个排序后的list fragment
     */
    private FragmentManager fragmentManager;
    private MerchandiseNewCountFragment countFragment;
    private MerchandiseNewPriceFragment priceFragment;

    private void initView()
    {
        /**
         * toolbar title
         */
        view = findViewById(R.id.merchandisenew_header);
        if(view != null)
        {
            Toolbar toolbar = (Toolbar)view;
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            MyToolbarHeader toolbarHeader = new MyToolbarHeader(this,toolbar);
            toolbarHeader.setHeaderTitle(getString(R.string.merchandise_new_title));
            MyToolbarListener listener = new MyToolbarListener() {
                @Override
                public void addNavigation() {
                    finish();
                }
            };
            toolbarHeader.setNavigation(R.mipmap.back,listener);
            //toolbarHeader.setSearchMenu();
        }
        count = (Button)findViewById(R.id.count);
        price = (Button)findViewById(R.id.price);
        count.setOnClickListener(this);
        price.setOnClickListener(this);
    }

    /**
     * 初始化fragment
     */
    private void initFragment()
    {
        fragmentManager = getFragmentManager();
        //在这里设置两个按钮的backgroundresource
        setTabSelection(0);
    }

    /**
     * 点击销量 价格 排序list
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.count:
                setTabSelection(0);
                break;
            case R.id.price:
                setTabSelection(1);
                break;
        }
    }

    /**
     * 设置fragment的位置
     * @param index
     */
    private void setTabSelection(int index){
        //开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //先隐藏掉所有的Fragment，以防止多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index){
            case 0:
                if(countFragment == null){
                    //如果MessageFragment为空，则创建一个添加到界面上
                    countFragment = new MerchandiseNewCountFragment();
                    transaction.add(R.id.merchandise_new_list,countFragment);
                }else{
                    transaction.show(countFragment);
                }
                break;
            case 1:
                if (priceFragment == null){
                    priceFragment = new MerchandiseNewPriceFragment();
                    transaction.add(R.id.merchandise_new_list,priceFragment);
                }else {
                    transaction.show(priceFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏所有的fragment
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction){
        if (countFragment != null){
            transaction.hide(countFragment);
        }
        if (priceFragment != null){
            transaction.hide(priceFragment);
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);

        //获取MenuItem搜索的实例
        MenuItem menuItem = menu.findItem(R.id.action_search);
        menuItem.setVisible(false);

        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setSubmitButtonEnabled(true);//显示确认搜索按钮
        searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MerchandiseNewActivity.this,"click",Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_search:
                Intent intent = new Intent(this, SearchTransparentActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    */
}
