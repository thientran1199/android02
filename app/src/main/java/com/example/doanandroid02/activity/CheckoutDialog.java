package com.example.doanandroid02.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doanandroid02.R;

import java.text.DecimalFormat;

public class CheckoutDialog extends Dialog {

    public Context context;
    TextView textTotalCount;
    Button btCheckout, btBack;


    public CheckoutDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_checkout_dialog);

        this.textTotalCount = findViewById(R.id.textTotalCount);
        this.btCheckout = findViewById(R.id.btCheckout);
        this.btBack = findViewById(R.id.btBack);
        getTotal();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_FILE.txt",Context.MODE_PRIVATE);
        Long total = sharedPreferences.getLong("total",0);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textTotalCount.setText((decimalFormat.format(total)) + "VND");
        this.btCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.cartArrayList.size() == 0){
                    Toast.makeText(context, "Try again", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getContext(),AddressActivity.class);
                    getContext().startActivity(intent);
                }
            }
        });
        this.btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCancelClick();
            }
        });
    }

    private void buttonCancelClick()  {
        this.dismiss();
    }

    public void getTotal(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_FILE.txt",Context.MODE_PRIVATE);
        sharedPreferences.getLong("total",0);
        Log.d("TAG", "getTotal: " + sharedPreferences.getLong("total",0));
    }
}
