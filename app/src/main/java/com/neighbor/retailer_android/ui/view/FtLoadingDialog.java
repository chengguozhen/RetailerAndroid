package com.neighbor.retailer_android.ui.view;/**
 * Created by Administrator on 2015/3/26.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neighbor.retailer_android.R;

/**
 * author: liuke
 * date: 2015-03-26
 * FIXME
 */
public class FtLoadingDialog extends Dialog {


        /** loading TextView*/
        private TextView tv;

        /** loading Text*/
        private String loadingText;


    /**
     * @param context
     * @param loadingText 加载时显示的text
     */
        public FtLoadingDialog(Context context, String loadingText) {
            super(context, R.style.loadingDialogStyle);
            this.loadingText=loadingText;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.loading_layout);
            tv = (TextView)findViewById(R.id.tv);
            tv.setText(loadingText);
            LinearLayout linearLayout = (LinearLayout)this.findViewById(R.id.loading_ly);
            linearLayout.getBackground().setAlpha(210);
        }

    public String getLoadingText() {
        return loadingText;
    }

    public void setLoadingText(String loadingText) {
        this.loadingText = loadingText;
    }

    }

