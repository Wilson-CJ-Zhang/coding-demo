package pers.wilson.demo.repository;

import pers.wilson.demo.discount.Discount;

public interface DiscountRepository {
    Discount getByProductName(String productName);
}
