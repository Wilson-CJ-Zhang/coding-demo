package pers.wilson.demo.service;

import org.springframework.stereotype.Service;
import pers.wilson.demo.model.Cart;
import pers.wilson.demo.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isInvalid(List<String> productNames) {
        return productNames.stream()
                .anyMatch(productName -> productRepository.getByName(productName) == null);
    }

    @Override
    public List<Cart> mapProducts(List<String> productNames) {
        ArrayList<Cart> result = new ArrayList<>();
        if (!isInvalid(productNames)) {
            productNames.stream()
                    .collect(Collectors.groupingBy(productRepository::getByName, Collectors.summingInt(number -> 1)))
                    .forEach((key, value) -> result.add(new Cart(key, value)));
        }
        return result;
    }
}
