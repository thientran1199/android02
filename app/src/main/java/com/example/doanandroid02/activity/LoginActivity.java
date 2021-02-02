package com.example.doanandroid02.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanandroid02.R;
import com.example.doanandroid02.models.Order;
import com.example.doanandroid02.models.OrderDetail;
import com.example.doanandroid02.models.Category;
import com.example.doanandroid02.models.Customer;
import com.example.doanandroid02.models.Product;
import com.example.doanandroid02.models.Profile;
import com.example.doanandroid02.models.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements MainContract.View {

    public static EditText editEmail, editPassword;
    TextView textSignup;
    Button btLogin;
    MainContract.Presenter mPresenter;
    public static String username;
    public static String password;
    public static SharedPreferences sharedPreferences;
    public static Context context;
    ImageView imageViewBackLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        LoginActivity.context = getApplicationContext();

        editEmail = findViewById(R.id.editEmailLogin);
        editPassword = findViewById(R.id.editPasswordLogin);
        textSignup = findViewById(R.id.tv_sign_up);
        btLogin = findViewById(R.id.btLogin);
        imageViewBackLogin = findViewById(R.id.imageViewBackLogin);
        mPresenter = new MainPresenter(this);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editEmail.getText().toString().length() == 0 || editPassword.getText().toString().length() == 0) {
                    editEmail.setError("Email is required!");
                    editPassword.setError("Password is required");
                } else {
                    mPresenter.login();
                }
            }
        });

        textSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        imageViewBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public static Context getAppContext() {
        return LoginActivity.context;
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
        rememberUser(editEmail.getText().toString(), editPassword.getText().toString(), btLogin.isClickable(), userList.access_token);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        mPresenter.details();
    }

    @Override
    public void details(Profile profleList) {
        int id = profleList.getId();
        String name = profleList.getName();
        rememberName(id,name);
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


    public void rememberName(int id, String username) {
        sharedPreferences = getSharedPreferences("USER_FILE.txt", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putInt("user_id", id);
        editor.commit();
    }


    public void rememberUser(String email, String password, boolean status, String token) {
        sharedPreferences = getSharedPreferences("USER_FILE.txt", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putBoolean("status", status == true);
        editor.putString("token", token);
        editor.commit();
    }
}