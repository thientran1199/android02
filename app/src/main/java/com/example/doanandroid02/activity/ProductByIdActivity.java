package com.example.doanandroid02.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid02.R;
import com.example.doanandroid02.adapter.ProductByIdAdapter;
import com.example.doanandroid02.models.Order;
import com.example.doanandroid02.models.OrderDetail;
import com.example.doanandroid02.models.Category;
import com.example.doanandroid02.models.Customer;
import com.example.doanandroid02.models.Product;

import com.example.doanandroid02.models.Profile;

import com.example.doanandroid02.models.User;

import java.util.List;

public class ProductByIdActivity extends AppCompatActivity implements MainContract.View {


    RecyclerView recyclerView;
    ProductByIdAdapter productByIdAdapter;
    MainContract.Presenter mPresenter;
    public static String category_id;
    String category_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_id);

        Intent intent = getIntent();
        recyclerView = findViewById(R.id.recyclerViewProductId);
        category_id = intent.getStringExtra("category_id");
        category_name = intent.getStringExtra("category_name");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(category_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter = new MainPresenter(this);
        mPresenter.findProducts();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
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
        productByIdAdapter = new ProductByIdAdapter(productList, getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(productByIdAdapter);
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
