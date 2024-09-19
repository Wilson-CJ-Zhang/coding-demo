package pers.wilson.demo.repository;

import org.springframework.stereotype.Repository;
import pers.wilson.demo.discount.*;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DiscountRepositoryImpl implements DiscountRepository {
    private final ConcurrentHashMap<String, DiscountType> discountTypeCache;

    public DiscountRepositoryImpl() {
        //Since this is a demo, I use a ConcurrentHashMap as datastore instead of connecting to real database
        discountTypeCache = new ConcurrentHashMap<>();
        discountTypeCache.put("Melon", DiscountType.BUY_ONE_GET_ONE_FREE);
        discountTypeCache.put("Lime", DiscountType.BUY_THREE_FOR_PRICE_OF_TWO);
    }

    @Override
    public Discount getByProductName(String productName) {
        DiscountType discountType = discountTypeCache.getOrDefault(productName, DiscountType.NONE);
        return DiscountFactory.getDiscountByType(discountType);
    }
}
