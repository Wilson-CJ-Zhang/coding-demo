package pers.wilson.demo.repository;

import pers.wilson.demo.model.Product;

public interface ProductRepository {
    Product getByName(String name);
}
