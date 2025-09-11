package edu.dosw.taller.Taller_Evaluativo_DOSW;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductTest {
    private Product product;
    private StockObserver mockObserver;

    @BeforeEach
    void setUp() {
        product = new Product("Laptop", 999.99, 10, "Electrónicos");
        mockObserver = mock(StockObserver.class);
    }

    @Test
    void testGetName() {
        assertEquals("Laptop", product.getName());
    }

    @Test
    void testGetPrice() {
        assertEquals(999.99, product.getPrice(), 0.01);
    }

    @Test
    void testGetStock() {
        assertEquals(10, product.getStock());
    }

    @Test
    void testGetCategory() {
        assertEquals("Electrónicos", product.getCategory());
    }

    @Test
    void testAddObserver() {
        product.addObserver(mockObserver);
        product.modifyStock(8);
        verify(mockObserver).writeUpdate(product);
    }

    @Test
    void testModifyStock() {
        product.modifyStock(5);
        assertEquals(5, product.getStock());
    }

    @Test
    void testNotifyObservers() {
        product.addObserver(mockObserver);
        product.modifyStock(3);
        verify(mockObserver).writeUpdate(product);
    }

    @Test
    void testMultipleModifications() {
        product.addObserver(mockObserver);
        product.modifyStock(7);
        product.modifyStock(4);
        verify(mockObserver, times(2)).writeUpdate(product);
    }

    @Test
    void testNoObservers() {
        product.modifyStock(6);
        assertEquals(6, product.getStock());
    }
}