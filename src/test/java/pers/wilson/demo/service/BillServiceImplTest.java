package pers.wilson.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pers.wilson.demo.discount.BuyOneGetOneFreeDiscount;
import pers.wilson.demo.discount.BuyThreeForPriceOfTwoDiscount;
import pers.wilson.demo.discount.NoneDiscount;
import pers.wilson.demo.model.Bill;
import pers.wilson.demo.model.Cart;
import pers.wilson.demo.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceImplTest {
    @Mock
    private ProductService productService;
    @Mock
    private DiscountService discountService;

    private BillService testObject;

    @Before
    public void setUp() {
        testObject = new BillServiceImpl(productService, discountService);
    }

    @Test
    public void testCalculateForInvalidProduct() {
        when(productService.mapProducts(anyList())).thenReturn(new ArrayList<>());

        Bill bill = testObject.calculate(Arrays.asList("Phone", "Apple"));
        assertEquals(0, bill.getCarts().size());
        assertEquals(0.0, bill.getTotal(), 0.01);
    }

    @Test
    public void testCalculateForNoneDiscount() {
        Product apple = new Product("Apple", 35);
        Product banana = new Product("Banana", 20);
        List<String> products = Arrays.asList("Apple", "Banana");
        List<Cart> carts = Arrays.asList(new Cart(apple, 1), new Cart(banana, 1));
        when(productService.mapProducts(eq(products))).thenReturn(carts);
        when(discountService.mapAndApply(eq(apple.getName()), eq(apple.getPrice()), eq(1))).thenReturn(new NoneDiscount().apply(apple.getPrice(), 1));
        when(discountService.mapAndApply(eq(banana.getName()), eq(banana.getPrice()), eq(1))).thenReturn(new NoneDiscount().apply(banana.getPrice(), 1));

        Bill bill = testObject.calculate(products);

        assertEquals(apple, bill.getCarts().get(0).getProduct());
        assertEquals(1, bill.getCarts().get(0).getNumber());
        assertEquals(banana, bill.getCarts().get(1).getProduct());
        assertEquals(1, bill.getCarts().get(1).getNumber());
        assertEquals(55.0, bill.getTotal(), 0.01);
    }

    @Test
    public void testCalculateForBuyOneGetOneFreeDiscount() {
        Product apple = new Product("Apple", 35);
        Product melon = new Product("Melon", 50);
        List<String> products = Arrays.asList("Apple", "Melon");
        List<Cart> carts = Arrays.asList(new Cart(apple, 1), new Cart(melon, 1));
        when(productService.mapProducts(eq(products))).thenReturn(carts);
        when(discountService.mapAndApply(eq(apple.getName()), eq(apple.getPrice()), eq(1))).thenReturn(new NoneDiscount().apply(apple.getPrice(), 1));
        when(discountService.mapAndApply(eq(melon.getName()), eq(melon.getPrice()), eq(1))).thenReturn(new BuyOneGetOneFreeDiscount().apply(melon.getPrice(), 1));

        Bill bill = testObject.calculate(products);

        assertEquals(apple, bill.getCarts().get(0).getProduct());
        assertEquals(1, bill.getCarts().get(0).getNumber());
        assertEquals(melon, bill.getCarts().get(1).getProduct());
        assertEquals(1, bill.getCarts().get(1).getNumber());
        assertEquals(85.0, bill.getTotal(), 0.01);

        List<String> twoMelons = Arrays.asList("Apple", "Melon", "Melon");
        carts = Arrays.asList(new Cart(apple, 1), new Cart(melon, 2));
        when(productService.mapProducts(eq(twoMelons))).thenReturn(carts);
        when(discountService.mapAndApply(eq(apple.getName()), eq(apple.getPrice()), eq(1))).thenReturn(new NoneDiscount().apply(apple.getPrice(), 1));
        when(discountService.mapAndApply(eq(melon.getName()), eq(melon.getPrice()), eq(2))).thenReturn(new BuyOneGetOneFreeDiscount().apply(melon.getPrice(), 2));

        bill = testObject.calculate(twoMelons);

        assertEquals(apple, bill.getCarts().get(0).getProduct());
        assertEquals(1, bill.getCarts().get(0).getNumber());
        assertEquals(melon, bill.getCarts().get(1).getProduct());
        assertEquals(2, bill.getCarts().get(1).getNumber());
        assertEquals(85.0, bill.getTotal(), 0.01);
    }

    @Test
    public void testCalculateForBuyThreeForPriceOfTwoDiscount() {
        Product apple = new Product("Apple", 35);
        Product lime = new Product("Lime", 15);
        List<String> products = Arrays.asList("Apple", "Lime");
        List<Cart> carts = Arrays.asList(new Cart(apple, 1), new Cart(lime, 1));
        when(productService.mapProducts(eq(products))).thenReturn(carts);
        when(discountService.mapAndApply(eq(apple.getName()), eq(apple.getPrice()), eq(1))).thenReturn(new NoneDiscount().apply(apple.getPrice(), 1));
        when(discountService.mapAndApply(eq(lime.getName()), eq(lime.getPrice()), eq(1))).thenReturn(new BuyThreeForPriceOfTwoDiscount().apply(lime.getPrice(), 1));

        Bill bill = testObject.calculate(products);

        assertEquals(apple, bill.getCarts().get(0).getProduct());
        assertEquals(1, bill.getCarts().get(0).getNumber());
        assertEquals(lime, bill.getCarts().get(1).getProduct());
        assertEquals(1, bill.getCarts().get(1).getNumber());
        assertEquals(50.0, bill.getTotal(), 0.01);

        List<String> twoLimes = Arrays.asList("Apple", "Lime", "Lime");
        carts = Arrays.asList(new Cart(apple, 1), new Cart(lime, 2));
        when(productService.mapProducts(eq(twoLimes))).thenReturn(carts);
        when(discountService.mapAndApply(eq(apple.getName()), eq(apple.getPrice()), eq(1))).thenReturn(new NoneDiscount().apply(apple.getPrice(), 1));
        when(discountService.mapAndApply(eq(lime.getName()), eq(lime.getPrice()), eq(2))).thenReturn(new BuyThreeForPriceOfTwoDiscount().apply(lime.getPrice(), 2));

        bill = testObject.calculate(twoLimes);

        assertEquals(apple, bill.getCarts().get(0).getProduct());
        assertEquals(1, bill.getCarts().get(0).getNumber());
        assertEquals(lime, bill.getCarts().get(1).getProduct());
        assertEquals(2, bill.getCarts().get(1).getNumber());
        assertEquals(65.0, bill.getTotal(), 0.01);

        List<String> threeLimes = Arrays.asList("Apple", "Lime", "Lime", "Lime");
        carts = Arrays.asList(new Cart(apple, 1), new Cart(lime, 3));
        when(productService.mapProducts(eq(threeLimes))).thenReturn(carts);
        when(discountService.mapAndApply(eq(apple.getName()), eq(apple.getPrice()), eq(1))).thenReturn(new NoneDiscount().apply(apple.getPrice(), 1));
        when(discountService.mapAndApply(eq(lime.getName()), eq(lime.getPrice()), eq(3))).thenReturn(new BuyThreeForPriceOfTwoDiscount().apply(lime.getPrice(), 3));

        bill = testObject.calculate(threeLimes);

        assertEquals(apple, bill.getCarts().get(0).getProduct());
        assertEquals(1, bill.getCarts().get(0).getNumber());
        assertEquals(lime, bill.getCarts().get(1).getProduct());
        assertEquals(3, bill.getCarts().get(1).getNumber());
        assertEquals(65.0, bill.getTotal(), 0.01);
    }
}