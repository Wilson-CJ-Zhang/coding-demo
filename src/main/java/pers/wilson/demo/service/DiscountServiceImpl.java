package pers.wilson.demo.service;

import org.springframework.stereotype.Service;
import pers.wilson.demo.discount.Discount;
import pers.wilson.demo.repository.DiscountRepository;

@Service
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public double mapAndApply(String productName, double price, int number) {
        Discount discount = discountRepository.getByProductName(productName);
        return discount.apply(price, number);
    }
}
