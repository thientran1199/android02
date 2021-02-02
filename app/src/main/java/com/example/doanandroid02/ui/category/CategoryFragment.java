package com.example.doanandroid02.ui.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.doanandroid02.CheckLoginRemember;
import com.example.doanandroid02.R;
import com.example.doanandroid02.activity.CartActivity;
import com.example.doanandroid02.activity.LoginActivity;
import com.example.doanandroid02.activity.MainContract;
import com.example.doanandroid02.activity.MainPresenter;
import com.example.doanandroid02.activity.ProductByIdActivity;
import com.example.doanandroid02.adapter.CategoryAdapter;
import com.example.doanandroid02.models.Order;
import com.example.doanandroid02.models.OrderDetail;
import com.example.doanandroid02.models.Category;
import com.example.doanandroid02.models.Customer;
import com.example.doanandroid02.models.Product;

import com.example.doanandroid02.models.Profile;

import com.example.doanandroid02.models.User;
import com.example.doanandroid02.repositories.CategoryRepository;

import java.util.List;

public class CategoryFragment extends Fragment implements MainContract.View,AdapterView.OnItemClickListener {

    MainContract.Presenter mPresenter;
    ListView listView;
    CategoryAdapter categoryAdapter;
    ProgressBar progressBar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category,container,false);
        progressBar = view.findViewById(R.id.progressCate);
        listView = view.findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        mPresenter = new MainPresenter(this);
        mPresenter.loadCategories();
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.card:
                if(CheckLoginRemember.checkLoginRemember(getActivity().getApplicationContext())>0){
                    Intent intent = new Intent(getActivity(), CartActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateListProduct(List<Product> products) {

    }

    @Override
    public void updateListCategories(List<Category> categories) {
        showProgressBar();
        hideProgressBar();
        categoryAdapter = new CategoryAdapter(categories, getActivity());
        listView.setAdapter(categoryAdapter);
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ProductByIdActivity.class);
        int category_id = CategoryRepository.categories.get(position).getId();
        String category_name = CategoryRepository.categories.get(position).getName();
        intent.putExtra("category_id",String.valueOf(category_id));
        intent.putExtra("category_name", category_name);
        startActivity(intent);
    }
}
