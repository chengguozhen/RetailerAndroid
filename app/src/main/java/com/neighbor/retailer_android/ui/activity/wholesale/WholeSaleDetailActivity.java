package com.neighbor.retailer_android.ui.activity.wholesale;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;

public class WholeSaleDetailActivity extends Activity implements View.OnClickListener{

    private TextView title;
    private ImageButton back;
    private ImageView wholeImg;
    private TextView wholeName;
    private TextView wholeId;
    private TextView wholeCode;
    private TextView wholeAddress;
    private TextView wholePhoneNum;
    private TextView wholeConnectName;
    private TextView wholeMail;
    private TextView wholeKind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_sale_detail);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

        }
    }
}
