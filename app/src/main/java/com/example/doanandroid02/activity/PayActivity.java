package com.example.doanandroid02.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.doanandroid02.adapter.TotalItemAdapter;
import com.example.doanandroid02.models.Order;
import com.example.doanandroid02.models.OrderDetail;
import com.example.doanandroid02.models.Category;
import com.example.doanandroid02.models.Customer;
import com.example.doanandroid02.models.Product;
import com.example.doanandroid02.models.Profile;
import com.example.doanandroid02.models.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid02.R;

import java.text.DecimalFormat;
import java.util.List;

public class PayActivity extends AppCompatActivity implements MainContract.View {
    RecyclerView recyclerView;
    TextView textTotalPrice, totalOrder, textNameRecipient, textEmail, textPhoneNumber, textAddressRecipient;
    public static EditText editMessages;
    Button btPay;
    TotalItemAdapter totalItemAdapter;
    MainContract.Presenter mPresenter;
    public static SharedPreferences sharedPreferences;
    public static String fullname, email, address, phonenumber, notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        mPresenter = new MainPresenter(this);


        sharedPreferences = getSharedPreferences("USER_FILE.txt", MODE_PRIVATE);
        recyclerView = findViewById(R.id.rvPay);
        textTotalPrice = findViewById(R.id.textTotalPrice);
        textNameRecipient = findViewById(R.id.textNameRecipient);
        textPhoneNumber = findViewById(R.id.textPhoneNumber);
        textEmail = findViewById(R.id.textEmailPay);
        textAddressRecipient = findViewById(R.id.textAddressRecipient);
        editMessages = findViewById(R.id.editMessages);
        totalOrder = findViewById(R.id.total);
        btPay = findViewById(R.id.btPay);

        getDataAddress();

        mPresenter.postCustomer();

        Long total = sharedPreferences.getLong("total", 0);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textTotalPrice.setText(decimalFormat.format(total) + "VND");
        totalOrder.setText(decimalFormat.format(total) + "VND");


        totalItemAdapter = new TotalItemAdapter(MainActivity.cartArrayList, getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recyclerView.setAdapter(totalItemAdapter);


        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.postOrder();
            }
        });
    }


    public void getDataAddress() {
        Intent intent = getIntent();
        fullname = intent.getStringExtra("fullname");
        phonenumber = intent.getStringExtra("numberphone");
        email = intent.getStringExtra("email");
        address = intent.getStringExtra("address");

        textNameRecipient.setText(fullname);
        textPhoneNumber.setText(String.valueOf(phonenumber));
        textAddressRecipient.setText(address);
        textEmail.setText(email);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void updateListProduct(List<Product> products) {

    }

    @Override
    public void updateListCategories(List<Category> categories) {

    }

    @Override
    public void updateListProductId(List<Product> productList) {

    }

    @Override
    public void login(User userList) {

    }

    @Override
    public void details(Profile profleList) {

    }

    @Override
    public void logout(User userList) {

    }

    @Override
    public void register(Profile profleRegiser) {

    }

    @Override
    public void postCustomer(Customer customer) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id_customer", customer.id);
        editor.putString("address", customer.dia_chi);
        editor.commit();
//        Log.d("TAG", "postCustomer: " + customer.toString());
        Toast.makeText(this, "upload successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postOrder(Order Order) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id_Order",Order.getId());
        editor.commit();
        mPresenter.postOrderDetail();
    }

    @Override
    public void postOrderDetail(OrderDetail orderDetail) {
        Log.d("TAG", "postOrderDetail: ");
    }

    @Override
    public void searchProduct(List<Product> products) {

    }
}