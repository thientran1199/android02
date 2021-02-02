package com.example.doanandroid02.repositories;

import java.util.List;

public interface DataCallBack<T> {
    void response(List<T> data);
}
