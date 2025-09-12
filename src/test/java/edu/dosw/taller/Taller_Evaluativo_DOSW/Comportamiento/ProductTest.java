package edu.dosw.taller.Taller_Evaluativo_DOSW.Comportamiento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ProductTest {
    @Mock
    private StockObserver observer;

    private Product product;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        product = new Product("Laptop", 999.99, 10, "Electrónicos");
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testAddObserverAndNotify() {
        product.addObserver(observer);
        product.modifyStock(8);

        verify(observer, times(1)).update(product);
        assertEquals(8, product.getStock());
    }

    @Test
    void testGetters() {
        assertEquals("Laptop", product.getName());
        assertEquals(999.99, product.getPrice());
        assertEquals(10, product.getStock());
        assertEquals("Electrónicos", product.getCategory());
    }
}