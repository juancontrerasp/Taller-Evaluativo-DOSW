package edu.dosw.taller.Taller_Evaluativo_DOSW;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InventoryTest {
    private Inventory inventory;
    private Product mockProduct;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        mockProduct = mock(Product.class);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testAddProduct() {
        inventory.addProduct("Laptop", 999.99, 10, "Electrónicos");
        Product product = inventory.getProduct("Laptop");
        assertEquals("laptop", product.getName());
    }

    @Test
    void testAddProductDuplicate() {
        inventory.addProduct("Laptop", 999.99, 10, "Electrónicos");
        assertThrows(IllegalArgumentException.class, () ->
                inventory.addProduct("laptop", 499.99, 5, "Electrónicos"));
    }

    @Test
    void testModifyStock() {
        when(mockProduct.getName()).thenReturn("laptop");
        inventory = new Inventory() {{
            products.put("laptop", mockProduct);
        }};
        inventory.modifyStock("Laptop", 8);
        verify(mockProduct).modifyStock(8);
    }

    @Test
    void testModifyStockNonExistent() {
        assertThrows(IllegalArgumentException.class, () ->
                inventory.modifyStock("Laptop", 8));
    }

    @Test
    void testGetProduct() {
        inventory.addProduct("Laptop", 999.99, 10, "Electrónicos");
        assertNotNull(inventory.getProduct("laptop"));
    }

    @Test
    void testGetStock() {
        when(mockProduct.getName()).thenReturn("laptop");
        when(mockProduct.getStock()).thenReturn(10);
        inventory = new Inventory() {{
            products.put("laptop", mockProduct);
        }};
        inventory.getStock("Laptop");
        assertTrue(outContent.toString().contains("laptop Stock: 10"));
    }

    @Test
    void testGetAllStockEmpty() {
        inventory.getAllStock();
        assertTrue(outContent.toString().contains("No hay productos"));
    }

    @Test
    void testGetAllStock() {
        inventory.addProduct("Laptop", 999.99, 10, "Electrónicos");
        inventory.getAllStock();
        assertTrue(outContent.toString().contains("laptop Stock: 10"));
    }
}