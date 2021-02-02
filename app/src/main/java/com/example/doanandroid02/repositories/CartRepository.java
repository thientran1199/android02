package com.example.doanandroid02.repositories;

import android.util.Log;

import com.example.doanandroid02.activity.MainActivity;
import com.example.doanandroid02.activity.PayActivity;
import com.example.doanandroid02.models.Order;
import com.example.doanandroid02.models.OrderDetail;
import com.example.doanandroid02.models.Customer;
import com.example.doanandroid02.retrofit.APIService;
import com.example.doanandroid02.retrofit.DataClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository extends PayActivity {
    DataClient api;
    public CartRepository(){
        api = APIService.getService();
    }

    public void postCustomer(DataUserCallBack<Customer> dataCallBack){
        api.postCustomer(fullname,phonenumber,address,email)
                .enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        Customer customer = response.body();
                        dataCallBack.user(customer);
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {
                        Log.d("TAG", "FailurePostCus: ");
                    }
                });
    }


    public void postOrder(DataUserCallBack<Order> dataUserCallBack){
        int id = sharedPreferences.getInt("id_customer",0);
        int user_id = sharedPreferences.getInt("user_id",0);
        String address = sharedPreferences.getString("address","");
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Long total = sharedPreferences.getLong("total",0);
        api.postOrder(id,date,address,total,notes = editMessages.getText().toString(),user_id)
                .enqueue(new Callback<Order>() {
                    @Override
                    public void onResponse(Call<Order> call, Response<Order> response) {
                        Order order = response.body();
                        dataUserCallBack.user(order);
                        Log.d("TAG", "onResponse: " + order);
                    }

                    @Override
                    public void onFailure(Call<Order> call, Throwable t) {
                        Log.d("TAG", "onFailureOrder: ");
                    }
                });
    }

    public void getOrder(DataUserCallBack<Order> dataUserCallBack){

    }

    public void postOrderDetail(DataUserCallBack<OrderDetail> dataUserCallBack){
        int Order_id = sharedPreferences.getInt("id_Order",0);
        int id_product;
        int sl;
        long dongia;
        for (int i=0; i < MainActivity.cartArrayList.size(); i++){
            id_product = MainActivity.cartArrayList.get(i).idsp;
            sl = MainActivity.cartArrayList.get(i).soluongsp;
            dongia = MainActivity.cartArrayList.get(i).giasp;

            Log.d("TAG", "postOrderDetailId: " + id_product);

            api.postOrderDetail(Order_id,id_product,sl,dongia)
                    .enqueue(new Callback<OrderDetail>() {
                        @Override
                        public void onResponse(Call<OrderDetail> call, Response<OrderDetail> response) {
                            OrderDetail orderDetail = response.body();
                            dataUserCallBack.user(orderDetail);
                        }

                        @Override
                        public void onFailure(Call<OrderDetail> call, Throwable t) {
                            Log.d("TAG", "onFailureDetail: ");
                        }
                    });
        }

    }
}