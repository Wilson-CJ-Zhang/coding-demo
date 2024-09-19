package pers.wilson.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import pers.wilson.demo.model.Bill;
import pers.wilson.demo.model.Cart;
import pers.wilson.demo.model.Product;
import pers.wilson.demo.service.BillService;
import pers.wilson.demo.service.ProductService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BillControllerTest {
    @Mock
    private ProductService productService;
    @Mock
    private BillService billService;
    private BillController testObject;

    @Before
    public void setUp() {
        testObject = new BillController(productService, billService);
    }

    @Test
    public void testBadRequest() {
        List<String> productNames = Arrays.asList("Apple", "Banana", "Melon", "Lime", "Phone");

        when(productService.isInvalid(eq(productNames))).thenReturn(true);

        ResponseEntity<Bill> response = testObject.bill(productNames);
        assertEquals(HttpStatusCode.valueOf(400), response.getStatusCode());
    }

    @Test
    public void testOKRequest() {
        List<String> productNames = Arrays.asList("Apple", "Banana", "Melon", "Lime");
        List<Cart> carts = Arrays.asList(
                new Cart(new Product("Apple", 35), 1),
                new Cart(new Product("Banana", 20), 1),
                new Cart(new Product("Melon", 50), 1),
                new Cart(new Product("Lime", 15), 1)
        );

        when(productService.isInvalid(eq(productNames))).thenReturn(false);
        when(billService.calculate(eq(productNames))).thenReturn(new Bill(carts, 120));

        ResponseEntity<Bill> response = testObject.bill(productNames);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println(response.getBody());
        assertEquals(120, response.getBody().getTotal(), 0.01);
    }
}