package com.neighbor.retailer_android.ui.activity.my;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarHeader;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarListener;
import com.neighbor.retailer_android.util.cascade.activity.ModifyLocationActivity;

public class AddressEditActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * 删除 保存地址按钮
     */
    private Button deleteAddress;
    private Button saveAddress;
    /**
     * 所在区域 详细地址
     */
    private TextView area,address;
    /**
     * 收货人 电话 邮编
     */
    private TextView charger,telephone,zipCode;
    /**
     * LinearLayout 点击区域
     */
    private LinearLayout areaLl,addressLl,chargerLl,telephoneLl,zipCodeLl;
    /**
     * 修改的是哪一项 0:area  1:address  2:charger  3:telephone  4:zip code
     */
    private int editPosition = 0;
    /**
     * 修改地址对话框
     */
    private AlertDialog editDialog;
    /**
     * 对话框标题
     */
    private String dialogTitle="";
    /**
     * 修改地址信息
     */
    public static final int LOCALITY = 1001;
    /**
     * toolbar
     */
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit);
        initToolbar();
        initView();
    }

    private void initToolbar()
    {
        view = findViewById(R.id.address_header);
        if(view != null)
        {
            Toolbar toolbar = (Toolbar)view;
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            MyToolbarHeader toolbarHeader = new MyToolbarHeader(this,toolbar);
            toolbarHeader.setHeaderTitle(/*getString(R.string.)*/"" +
                    "编辑地址");
            toolbarHeader.setSearchMenu();
            MyToolbarListener listener = new MyToolbarListener() {
                @Override
                public void addNavigation() {
                    finish();
                }
            };
            toolbarHeader.setNavigation(R.mipmap.back, listener);
            //toolbarHeader.setSearchMenu();
        }
    }


    private void initView()
    {
        area = (TextView)findViewById(R.id.area);
        address = (TextView)findViewById(R.id.address);
        charger = (TextView)findViewById(R.id.charger);
        telephone = (TextView)findViewById(R.id.telephone);
        zipCode = (TextView)findViewById(R.id.zip_code);

        areaLl = (LinearLayout)findViewById(R.id.area_ll);
        addressLl = (LinearLayout)findViewById(R.id.address_ll);
        chargerLl = (LinearLayout)findViewById(R.id.charger_ll);
        telephoneLl = (LinearLayout)findViewById(R.id.telephone_ll);
        zipCodeLl = (LinearLayout)findViewById(R.id.zip_code_ll);

        saveAddress = (Button)findViewById(R.id.save_address);
        deleteAddress = (Button)findViewById(R.id.delete_address);

        areaLl.setOnClickListener(this);
        addressLl.setOnClickListener(this);
        chargerLl.setOnClickListener(this);
        telephoneLl.setOnClickListener(this);
        zipCodeLl.setOnClickListener(this);
        saveAddress.setOnClickListener(this);
        deleteAddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.area_ll:
                Intent areaIntent  = new Intent(AddressEditActivity.this,ModifyLocationActivity.class);
                areaIntent.putExtra("key",LOCALITY);
                areaIntent.putExtra("data", area.getText().toString().replace(" ", "|"));
                startActivityForResult(areaIntent,LOCALITY);
                break;
            case R.id.charger_ll:
                editPosition = 2;
                dialogTitle = "收货人";
                editInfoDialog(dialogTitle,charger.getText().toString());
                break;
            case R.id.address_ll:
                editPosition = 1;
                dialogTitle = "详细地址";
                editInfoDialog(dialogTitle,address.getText().toString());
                break;
            case R.id.telephone_ll:
                editPosition = 3;
                dialogTitle = "电话";
                editInfoDialog(dialogTitle,telephone.getText().toString());
                break;
            case R.id.zip_code_ll:
                editPosition =  4;
                dialogTitle = "邮编";
                editInfoDialog(dialogTitle,zipCode.getText().toString());
                break;
            case R.id.delete_address:

                finish();
                break;
            case R.id.save_address:
                if(isAddressInfoValid())
                {

                    finish();
                }
                break;

            case R.id.cancel_edit:
                editDialog.cancel();
                break;
            default:
                break;
        }
    }

    /**
     * 修改地址信息的对话框
     */
    private void editInfoDialog(String title, String message)
    {
        editDialog = new AlertDialog.Builder(this).create();
        editDialog.show();
        Window window = editDialog.getWindow();
        window.setContentView(R.layout.dialog_edit_address);
        TextView dialogTitle = (TextView)window.findViewById(R.id.dialog_title);
        final EditText dialogInfo = (EditText)window.findViewById(R.id.dialog_message);
        if(editPosition ==3 || editPosition ==4)
        {
            dialogInfo.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        }
        Button cancel = (Button)window.findViewById(R.id.cancel_edit);
        Button save = (Button)window.findViewById(R.id.save_edit);
        dialogTitle.setText(title);
        dialogInfo.setText(message);
        dialogInfo.setSelection(message.length());
        cancel.setOnClickListener(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = dialogInfo.getText().toString();
                switch (editPosition)
                {
                    case 1:
                        //address
                        address.setText(content);
                        break;
                    case 2:
                        //charger
                        charger.setText(content);
                        break;
                    case 3:
                    //telephone
                        telephone.setText(content);
                        break;
                    case 4:
                    //zipcode
                        zipCode.setText(content);
                        break;
                    default:
                        break;
                }
                editDialog.cancel();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED)
        {
            switch (requestCode)
            {
                case LOCALITY:
                    area.setText(data.getExtras().getString("back").replace("|", " "));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 地址信息所有必填项不得为空
     * @return
     */
    private boolean isAddressInfoValid()
    {
        String areaStr = area.getText().toString();
        String addressStr = area.getText().toString();
        String chargerStr = charger.getText().toString();
        String telephoneStr = telephone.getText().toString();
        String zipCodeStr = zipCode.getText().toString();
        if( !TextUtils.isEmpty(areaStr) && !TextUtils.isEmpty(addressStr) &&
                !TextUtils.isEmpty(chargerStr) && !TextUtils.isEmpty(telephoneStr) &&
                !TextUtils.isEmpty(zipCodeStr) )
        {
            return true;
        }
        Toast.makeText(this,"所有项不得为空",Toast.LENGTH_SHORT).show();
        return false;
    }
}
