package com.example.doanandroid02.repositories;


import android.util.Log;

import com.example.doanandroid02.activity.ProductByIdActivity;
import com.example.doanandroid02.activity.SearchResult;
import com.example.doanandroid02.retrofit.APIService;
import com.example.doanandroid02.retrofit.DataClient;
import com.example.doanandroid02.retrofit.RetrofitClientInstance;
import com.example.doanandroid02.models.Product;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository {
    DataClient api = APIService.getService();
    String n = ProductByIdActivity.category_id;
    public static List<Product> products;

    public void loadAll(DataCallBack<Product> dataCallBack) {
        api.getProduct().enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                String item[] = new String[products.size()];
                for (int i = 0; i < products.size(); i++) {
                    item[i] = products.get(i).getImage();
                    item[i] = products.get(i).getName();
                    item[i] = String.valueOf(products.get(i).getPrice());
                }
                dataCallBack.response(products);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }


    public void findId(DataCallBack<Product> dataCallBack) {
        api.getProductById(n).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productsId;
                productsId = response.body();
                String item[] = new String[productsId.size()];
                for (int i = 0; i < productsId.size(); i++) {
                    item[i] = String.valueOf(productsId.get(i).getId());
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

    public void searchProduct(DataCallBack<Product> dataCallBack) {
        api.searchProduct(SearchResult.titleSearch).enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productsSearch;
                productsSearch = response.body();
                String item[] = new String[productsSearch.size()];
                for (int i = 0; i < productsSearch.size(); i++) {
                    item[i] = String.valueOf(productsSearch.get(i).getId());
                    item[i] = productsSearch.get(i).getImage();
                    item[i] = productsSearch.get(i).getName();
                    item[i] = String.valueOf(productsSearch.get(i).getPrice());
                }
                Log.d("TAG","onResponse: " + productsSearch );
                dataCallBack.response(productsSearch);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("TAG","onFailureGetSearch: ");
            }
        });
    }
}
