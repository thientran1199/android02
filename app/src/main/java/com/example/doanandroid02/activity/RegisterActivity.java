package com.example.doanandroid02.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid02.R;
import com.example.doanandroid02.models.Order;
import com.example.doanandroid02.models.OrderDetail;
import com.example.doanandroid02.models.Category;
import com.example.doanandroid02.models.Customer;
import com.example.doanandroid02.models.Product;
import com.example.doanandroid02.models.Profile;
import com.example.doanandroid02.models.User;

import java.util.List;

public class RegisterActivity extends AppCompatActivity implements MainContract.View{

    TextView signin;
    public static EditText editTextName, editTextEmail, editTextPass, editTextConfPass;
    public static String name, email, password, c_password;
    Button btRegister;
    MainPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        signin = findViewById(R.id.tv_sign_up);
        mPresenter = new MainPresenter(this);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editEmailRegister);
        editTextPass = findViewById(R.id.editPasswordRegister);
        editTextConfPass = findViewById(R.id.editConfPass);
        btRegister = findViewById(R.id.btRegister);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkError();
                mPresenter.register();
            }
        });
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
    public void details(Profile profileList) {

    }


    @Override
    public void logout(User userList) {

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

    @Override
    public void register(Profile profleRegiser) {
        Toast.makeText(this, "Register successfully, you can login now ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void checkError() {
        if (editTextName.getText().toString().length() == 0 || editTextEmail.getText().toString().length() == 0
                || editTextPass.getText().toString().length() == 0 || editTextConfPass.getText().toString().length() == 0) {
            editTextName.setError("Name is required!");
            editTextEmail.setError("Email is required");
            editTextPass.setError("Password is required");
            editTextConfPass.setError("Password is required");
            if (editTextPass.getText().toString().equals(editTextConfPass.getText().toString())) {

            }else {
                editTextConfPass.setError("Password does not match");
            }
        }
    }
}