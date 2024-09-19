package pers.wilson.demo.repository;

import org.junit.Before;
import org.junit.Test;
import pers.wilson.demo.model.Product;

import static org.junit.Assert.*;

public class ProductRepositoryImplTest {
    private ProductRepository testObject;

    @Before
    public void setUp() {
        testObject = new ProductRepositoryImpl();
    }

    @Test
    public void testGetByName() {
        Product product = testObject.getByName("Phone");
        assertNull(product);

        Product apple = testObject.getByName("Apple");
        assertEquals("Apple", apple.getName());
        assertEquals(35, apple.getPrice(), 0.01);
    }
}