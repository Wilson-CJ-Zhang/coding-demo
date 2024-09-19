package pers.wilson.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pers.wilson.demo.model.Product;
import pers.wilson.demo.repository.ProductRepository;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    private ProductService testObject;

    @Before
    public void setUp() {
        testObject = new ProductServiceImpl(productRepository);
        when(productRepository.getByName(eq("Apple"))).thenReturn(new Product("Apple", 35));
        when(productRepository.getByName(eq("Banana"))).thenReturn(new Product("Banana", 20));
        when(productRepository.getByName(eq("Melon"))).thenReturn(new Product("Melon", 50));
        when(productRepository.getByName(eq("Lime"))).thenReturn(new Product("Lime", 15));
    }

    @Test
    public void testInvalidProducts() {
        assertFalse(testObject.isInvalid(Arrays.asList("Apple", "Banana", "Melon", "Lime")));
        assertTrue(testObject.isInvalid(Arrays.asList("Apple", "Banana", "Melon", "Lime", "Phone")));
    }

    /*@Test
    public void testMapProducts() {
        Map<Product, Integer> productIntegerMap = testObject.mapProducts(Arrays.asList("Apple", "Banana", "Melon", "Lime"));
        assertTrue(productIntegerMap.containsKey(new Product("Apple", 35)));
        assertEquals(1, productIntegerMap.get(new Product("Apple", 35)).intValue());
        assertTrue(productIntegerMap.containsKey(new Product("Banana", 20)));
        assertEquals(1, productIntegerMap.get(new Product("Banana", 20)).intValue());
        assertTrue(productIntegerMap.containsKey(new Product("Melon", 50)));
        assertEquals(1, productIntegerMap.get(new Product("Melon", 50)).intValue());
        assertTrue(productIntegerMap.containsKey(new Product("Lime", 15)));
        assertEquals(1, productIntegerMap.get(new Product("Lime", 15)).intValue());

        productIntegerMap = testObject.mapProducts(Arrays.asList("Apple", "Banana", "Melon", "Lime", "Banana", "Melon", "Lime", "Lime"));
        assertTrue(productIntegerMap.containsKey(new Product("Apple", 35)));
        assertEquals(1, productIntegerMap.get(new Product("Apple", 35)).intValue());
        assertTrue(productIntegerMap.containsKey(new Product("Banana", 20)));
        assertEquals(2, productIntegerMap.get(new Product("Banana", 20)).intValue());
        assertTrue(productIntegerMap.containsKey(new Product("Melon", 50)));
        assertEquals(2, productIntegerMap.get(new Product("Melon", 50)).intValue());
        assertTrue(productIntegerMap.containsKey(new Product("Lime", 15)));
        assertEquals(3, productIntegerMap.get(new Product("Lime", 15)).intValue());
    }*/
}