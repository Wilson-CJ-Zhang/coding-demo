package pers.wilson.demo.repository;

import org.springframework.stereotype.Repository;
import pers.wilson.demo.model.Product;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final ConcurrentHashMap<String, Product> productCache;

    public ProductRepositoryImpl() {
        //Since this is a demo, I use a ConcurrentHashMap as datastore instead of connecting to real database
        productCache = new ConcurrentHashMap<>();
        productCache.put("Apple", new Product("Apple", 35));
        productCache.put("Banana", new Product("Banana", 20));
        productCache.put("Melon", new Product("Melon", 50));
        productCache.put("Lime", new Product("Lime", 15));
    }

    @Override
    public Product getByName(String name) {
        return productCache.getOrDefault(name, null);
    }
}
