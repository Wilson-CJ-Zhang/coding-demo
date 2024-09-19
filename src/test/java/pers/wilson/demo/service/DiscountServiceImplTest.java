package pers.wilson.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pers.wilson.demo.discount.BuyOneGetOneFreeDiscount;
import pers.wilson.demo.discount.BuyThreeForPriceOfTwoDiscount;
import pers.wilson.demo.discount.NoneDiscount;
import pers.wilson.demo.repository.DiscountRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceImplTest {
    @Mock
    private DiscountRepository discountRepository;

    private DiscountService testObject;

    @Before
    public void setUp() {
        testObject = new DiscountServiceImpl(discountRepository);
    }

    @Test
    public void testMapAndApplyForNonDiscount() {
        String apple = "Apple";
        when(discountRepository.getByProductName(eq(apple))).thenReturn(new NoneDiscount());

        assertEquals(35.0, testObject.mapAndApply(apple, 35, 1), 0.01);
    }

    @Test
    public void testMapAndApplyForBuyOneGetOneFreeDiscount() {
        String melon = "Melon";
        when(discountRepository.getByProductName(eq(melon))).thenReturn(new BuyOneGetOneFreeDiscount());

        assertEquals(50.0, testObject.mapAndApply(melon, 50, 1), 0.01);
        assertEquals(50.0, testObject.mapAndApply(melon, 50, 2), 0.01);
        assertEquals(100.0, testObject.mapAndApply(melon, 50, 3), 0.01);
    }

    @Test
    public void testMapAndApplyForBuyThreeForPriceOfTwoDiscount() {
        String lime = "Lime";
        when(discountRepository.getByProductName(eq(lime))).thenReturn(new BuyThreeForPriceOfTwoDiscount());

        assertEquals(15.0, testObject.mapAndApply(lime, 15, 1), 0.01);
        assertEquals(30.0, testObject.mapAndApply(lime, 15, 2), 0.01);
        assertEquals(30.0, testObject.mapAndApply(lime, 15, 3), 0.01);
        assertEquals(45.0, testObject.mapAndApply(lime, 15, 4), 0.01);
    }
}