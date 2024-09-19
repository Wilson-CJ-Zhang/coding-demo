package pers.wilson.demo.repository;

import org.junit.Before;
import org.junit.Test;
import pers.wilson.demo.discount.BuyOneGetOneFreeDiscount;
import pers.wilson.demo.discount.BuyThreeForPriceOfTwoDiscount;
import pers.wilson.demo.discount.Discount;
import pers.wilson.demo.discount.NoneDiscount;

import static org.junit.Assert.assertTrue;

public class DiscountRepositoryImplTest {
    private DiscountRepository testObject;

    @Before
    public void setUp() {
        testObject = new DiscountRepositoryImpl();
    }

    @Test
    public void testGetNoneDiscount() {
        Discount discount = testObject.getByProductName("Apple");
        assertTrue(discount instanceof NoneDiscount);
    }

    @Test
    public void testGetDiscountByName() {
        Discount discount = testObject.getByProductName("Melon");
        assertTrue(discount instanceof BuyOneGetOneFreeDiscount);

        discount = testObject.getByProductName("Lime");
        assertTrue(discount instanceof BuyThreeForPriceOfTwoDiscount);
    }
}