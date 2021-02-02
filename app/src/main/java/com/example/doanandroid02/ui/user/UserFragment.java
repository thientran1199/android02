package com.example.doanandroid02.ui.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doanandroid02.R;
import com.example.doanandroid02.activity.LoginActivity;
import com.example.doanandroid02.activity.MainContract;
import com.example.doanandroid02.activity.MainPresenter;
import com.example.doanandroid02.activity.RegisterActivity;
import com.example.doanandroid02.models.Order;
import com.example.doanandroid02.models.OrderDetail;
import com.example.doanandroid02.models.Category;
import com.example.doanandroid02.models.Customer;
import com.example.doanandroid02.models.Product;
import com.example.doanandroid02.models.Profile;
import com.example.doanandroid02.models.User;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static android.view.View.GONE;


public class UserFragment extends Fragment implements MainContract.View {
    TextView textLogin, textRegister, textEmail, textLogout;
    ImageView imgLogout;
    MainContract.Presenter mPresenter;
    public static SharedPreferences sharedPreferences;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,container,false);
        mPresenter = new MainPresenter(this);
        sharedPreferences = getActivity().getSharedPreferences("USER_FILE.txt", Context.MODE_PRIVATE);
        textLogin = view.findViewById(R.id.textLogin);
        textLogout = view.findViewById(R.id.logout);
        textEmail = view.findViewById(R.id.textEmail);
        imgLogout = view.findViewById(R.id.imageLogout);
        textLogout = view.findViewById(R.id.logout);
        textRegister = view.findViewById(R.id.textRegister);

        if (checkLoginRemember() > 0) {
            textRegister.setVisibility(GONE);
            textLogin.setText(sharedPreferences.getString("username", ""));
            textEmail.setText(sharedPreferences.getString("email", ""));
            textLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.logout();
                }
            });
        } else {
            textLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });

            textRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), RegisterActivity.class);
                    startActivity(intent);
                }
            });
            textLogout.setVisibility(GONE);
            imgLogout.setVisibility(GONE);

        }

        return view;
    }

    public int checkLoginRemember() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE.txt", MODE_PRIVATE);
        boolean check = sharedPreferences.getBoolean("status", false);
        if (check) {
            sharedPreferences.getString("name", "");
            sharedPreferences.getString("email", "");
            sharedPreferences.getString("password", "");
            sharedPreferences.getString("token", "");
            return 1;
        }
        return -1;
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
        String name =  userList.toString();
        Log.d("TAG", "logout: " + name);
        sharedPreferences.edit().clear().commit();
        Toast.makeText(getActivity(), "Logout success", Toast.LENGTH_SHORT).show();
        Intent intent = getActivity().getIntent();
        getActivity().finish();
        startActivity(intent);
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