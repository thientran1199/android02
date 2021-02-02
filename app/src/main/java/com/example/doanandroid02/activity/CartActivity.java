package com.example.doanandroid02.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid02.R;
import com.example.doanandroid02.adapter.CartAdapter;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public static CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = findViewById(R.id.recyclerviewCart);
        cartAdapter = new CartAdapter(MainActivity.cartArrayList, getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recyclerView.setAdapter(cartAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
            case R.id.check:
                CheckoutDialog checkoutDialog = new CheckoutDialog(this);
                checkoutDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void countPrice(Context context) {
        long total = 0;
        for (int i = 0; i < MainActivity.cartArrayList.size(); i++) {
            total += MainActivity.cartArrayList.get(i).getGiasp();
        }
        Log.d("TAG", "countPrice: " + total);
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_FILE.txt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("total",total);
        editor.commit();
    }

//    new SweetAlertDialog(MainActivity.this);
//    .setTitleText("Success!");
//    .show();
}