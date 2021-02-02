package com.example.doanandroid02.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid02.CheckLoginRemember;
import com.example.doanandroid02.R;
import com.example.doanandroid02.activity.CartActivity;
import com.example.doanandroid02.activity.LoginActivity;
import com.example.doanandroid02.activity.MainContract;
import com.example.doanandroid02.activity.MainPresenter;
import com.example.doanandroid02.activity.SearchResult;
import com.example.doanandroid02.adapter.NewProductAdapter;
import com.example.doanandroid02.models.Order;
import com.example.doanandroid02.models.OrderDetail;
import com.example.doanandroid02.models.Category;
import com.example.doanandroid02.models.Customer;
import com.example.doanandroid02.models.Product;

import com.example.doanandroid02.models.Profile;

import com.example.doanandroid02.models.User;

import java.util.List;

import static android.view.View.GONE;

public class HomeFragment extends Fragment implements MainContract.View {

    RecyclerView recyclerView;
    NewProductAdapter newProductAdapter;
    MainContract.Presenter mPresenter;
    ProgressBar progressBar;
    Button btSearch;
    EditText editTextSearch;
    String searchKey;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerviewHome);
        progressBar = view.findViewById(R.id.progressHome);
        mPresenter = new MainPresenter(this);
        mPresenter.loadProducts();
        editTextSearch = view.findViewById(R.id.editSearch);
        btSearch = view.findViewById(R.id.btSearch);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchKey = editTextSearch.getText().toString();
                Intent intent = new Intent(getActivity(), SearchResult.class);
                intent.putExtra("searchKey",searchKey);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(GONE);
    }

    @Override
    public void updateListProduct(List<Product> products) {
        showProgressBar();
        hideProgressBar();
        newProductAdapter = new NewProductAdapter(products, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(newProductAdapter);
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