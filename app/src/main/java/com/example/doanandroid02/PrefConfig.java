package com.example.doanandroid02;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.doanandroid02.models.Cart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PrefConfig {
    private static final String LIST_CART = "list_cart";

    public static void writeList(Context context, List<Cart> carts){
        Gson gson = new Gson();
        String jsonString = gson.toJson(carts);
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_FILE.txt",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LIST_CART,jsonString);
        editor.apply();
    }

    public static List<Cart> readList(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_FILE.txt",Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(LIST_CART,"");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Cart>>() {}.getType();
        List<Cart> cartList = gson.fromJson(json, type);
        return cartList;
    }
}
