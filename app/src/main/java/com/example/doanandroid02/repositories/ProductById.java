package com.example.doanandroid02.repositories;

import com.example.doanandroid02.activity.ProductByIdActivity;
import com.example.doanandroid02.models.Product;
import com.example.doanandroid02.retrofit.APIService;
import com.example.doanandroid02.retrofit.DataClient;
import com.example.doanandroid02.retrofit.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductById {

    DataClient api = APIService.getService();
    String n = ProductByIdActivity.category_id;


    public void loadAll(DataCallBack<Product> dataCallBack) {
        api.getProductById(n).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productsId;
                productsId = response.body();
                String item[] = new String[productsId.size()];
                for (int i = 0; i < productsId.size(); i++) {
                    item[i] = productsId.get(i).getImage();
                    item[i] = productsId.get(i).getName();
                    item[i] = String.valueOf(productsId.get(i).getPrice());
                }
                dataCallBack.response(productsId);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        });
    }

}

