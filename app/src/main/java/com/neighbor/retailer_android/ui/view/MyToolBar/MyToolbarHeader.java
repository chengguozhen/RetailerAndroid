package com.neighbor.retailer_android.ui.view.MyToolBar;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.neighbor.retailer_android.R;

/**
 * Created by Vicky on 2015/10/13.
 * WeiFa
 * contact way: 317461087@qq.com
 */
public class MyToolbarHeader {

    Toolbar toolbar;
    Context context;

    public MyToolbarHeader(Context context,Toolbar toolbar)
    {
        this.toolbar = toolbar;
        this.context = context;
    }

    public void setHeaderTitle(String title)
    {
        TextView headerTitle = new TextView(context);
        headerTitle.setTextColor(context.getResources().getColor(R.color.white));
        headerTitle.setTextAppearance(context,R.style.toolbar_title);
        headerTitle.setText(title);
        Toolbar.LayoutParams params = new Toolbar.LayoutParams(
                Toolbar.LayoutParams.WRAP_CONTENT,
                Toolbar.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
        );
        //params.setMargins(3,3,3,3);
        headerTitle.setLayoutParams(params);
        toolbar.addView(headerTitle);
    }

    public void setNavigation(int icon,final MyToolbarListener listener)
    {
        toolbar.setNavigationIcon(icon);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.addNavigation();
                    }
                });
    }

    public void setSearchMenu()
    {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_search:

                        break;
                    /*case R.id.action_test:
                        Toast.makeText(context,"test",Toast.LENGTH_LONG).show();
                        break;*/
                    default:
                        break;
                }
                return true;
            }
        });
    }

}
