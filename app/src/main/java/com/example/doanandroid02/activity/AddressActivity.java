package com.example.doanandroid02.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.doanandroid02.R;
import com.example.doanandroid02.models.Order;
import com.example.doanandroid02.models.OrderDetail;
import com.example.doanandroid02.models.Category;
import com.example.doanandroid02.models.Customer;
import com.example.doanandroid02.models.Product;
import com.example.doanandroid02.models.Profile;
import com.example.doanandroid02.models.User;

import java.util.List;

public class AddressActivity extends AppCompatActivity implements MainContract.View {

    EditText textFullName, textNumberPhone, textEmailAddr, textFullAddress;
    Button btContinue;
    public String fullname, numberphone, email, address;
    MainContract.Presenter mPresenter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Address");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textFullName = findViewById(R.id.textFullName);
        textNumberPhone = findViewById(R.id.textNumberPhone);
        textEmailAddr = findViewById(R.id.textEmailAddr);
        textFullAddress = findViewById(R.id.textFullAddress);
        btContinue = findViewById(R.id.btContinue);

        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkError();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void checkError() {

        if (textFullName.getText().toString().length() == 0 || textNumberPhone.getText().toString().length() == 0
                || textFullAddress.getText().toString().length() == 0) {
            textFullName.setError("Name is required!");
            textNumberPhone.setError("Phone is required");
            textEmailAddr.setError("Email is required");
            textFullAddress.setError("Address is required");

        } else {
            fullname = textFullName.getText().toString();
            numberphone = textNumberPhone.getText().toString();
            email = textEmailAddr.getText().toString();
            address = textFullAddress.getText().toString();
            try {
                Integer.parseInt(numberphone);
                textNumberPhone.setError("invalid format");
            } catch (NumberFormatException e) {
                Intent intent = new Intent(AddressActivity.this, PayActivity.class);
                intent.putExtra("fullname", fullname);
                intent.putExtra("numberphone", numberphone);
                intent.putExtra("email", email);
                intent.putExtra("address", address);
                startActivity(intent);
            }
        }
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

    }

    @Override
    public void postOrder(Order Order) {

    }

    @Override
    public void postOrderDetail(OrderDetail orderDetail) {

    }

    @Override
    public void searchProduct(List<Product> products) {

    }
}