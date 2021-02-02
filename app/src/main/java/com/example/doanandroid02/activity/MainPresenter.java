package com.example.doanandroid02.activity;


import com.example.doanandroid02.models.Cart;
import com.example.doanandroid02.repositories.CartRepository;
import com.example.doanandroid02.repositories.CategoryRepository;
import com.example.doanandroid02.repositories.ProductById;
import com.example.doanandroid02.repositories.ProductRepository;
import com.example.doanandroid02.repositories.UserRepository;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;
    CategoryRepository categoryRepository;
    ProductRepository productRepository;
    ProductById productById;
    CartRepository cartRepository;
    UserRepository userRepository;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        categoryRepository = new CategoryRepository();
        productRepository = new ProductRepository();
        productById = new ProductById();
        userRepository = new UserRepository();
        cartRepository = new CartRepository();

    }

    @Override
    public void loadCategories() {
        categoryRepository.loadAll(data -> view.updateListCategories(data));
    }

    @Override
    public void loadProducts() {
        productRepository.loadAll(data -> {
            view.updateListProduct(data);
        });
    }

    @Override
    public void findProducts() {
        productRepository.findId(data -> view.updateListProductId(data));
    }



    @Override
    public void postCustomer() {
        cartRepository.postCustomer(dataUser -> view.postCustomer(dataUser));
    }

    @Override
    public void postOrder() {
        cartRepository.postOrder(dataUser -> view.postOrder(dataUser));
    }

    @Override
    public void postOrderDetail() {
        cartRepository.postOrderDetail(dataUser -> view.postOrderDetail(dataUser));
    }

    @Override
    public void searchProduct() {
        productRepository.searchProduct(data -> view.searchProduct(data));
    }

    @Override
    public void login() {
        userRepository.login(dataUser -> view.login(dataUser));
    }

    @Override
    public void details() {
        userRepository.details(dataUser -> view.details(dataUser));
    }

    @Override
    public void logout() {
        userRepository.logout(dataUser -> view.logout(dataUser));
    }

    @Override
    public void register() {
    userRepository.register(dataUser -> view.register(dataUser));
    }



}
