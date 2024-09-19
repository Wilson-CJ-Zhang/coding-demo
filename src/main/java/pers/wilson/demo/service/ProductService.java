package pers.wilson.demo.service;

import pers.wilson.demo.model.Cart;

import java.util.List;

public interface ProductService {
    boolean isInvalid(List<String> productNames);
    List<Cart> mapProducts(List<String> productNames);
}
