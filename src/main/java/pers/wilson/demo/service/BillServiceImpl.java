package pers.wilson.demo.service;

import org.springframework.stereotype.Service;
import pers.wilson.demo.model.Bill;
import pers.wilson.demo.model.Cart;
import pers.wilson.demo.model.Product;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    private final ProductService productService;
    private final DiscountService discountService;

    public BillServiceImpl(ProductService productService, DiscountService discountService) {
        this.productService = productService;
        this.discountService = discountService;
    }

    @Override
    public Bill calculate(List<String> productNames) {
        List<Cart> carts = productService.mapProducts(productNames);
        double total = carts.stream()
                .mapToDouble(cart -> {
                    Product product = cart.getProduct();
                    return discountService.mapAndApply(cart.getProduct().getName(), cart.getProduct().getPrice(), cart.getNumber());
                })
                .sum();
        return new Bill(carts, total);
    }
}
