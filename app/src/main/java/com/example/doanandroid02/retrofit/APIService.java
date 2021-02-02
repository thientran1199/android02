package com.example.doanandroid02.retrofit;

public class APIService {

    private static String base_url = "http://192.168.1.7/Doan-Laravel/public/";

    public static DataClient getService(){

        return RetrofitClientInstance.getClient(base_url).create(DataClient.class);
    }
}
