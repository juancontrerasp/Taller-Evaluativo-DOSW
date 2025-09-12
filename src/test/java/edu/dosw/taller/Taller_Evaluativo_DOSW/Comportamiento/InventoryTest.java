package edu.dosw.taller.Taller_Evaluativo_DOSW.Comportamiento;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class InventoryTest {
    private Inventory inventory;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        inventory = new Inventory(new ArrayList<>());
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testAddProductValid() {
        inventory.addProduct("Laptop", 999.99, 10, "Electrónicos");
        Product product = inventory.getProduct("Laptop");
        assertNotNull(product);
        assertEquals("laptop", product.getName());
        assertEquals(999.99, product.getPrice());
        assertEquals(10, product.getStock());
        assertEquals("Electrónicos", product.getCategory());
    }

    @Test
    void testAddProductInvalidName() {
        inventory.addProduct("", 999.99, 10, "Electrónicos");
        assertTrue(outContent.toString().contains("El nombre no puede estar vacío."));
    }

    @Test
    void testAddProductInvalidCategory() {
        inventory.addProduct("Laptop", 999.99, 10, "");
        assertTrue(outContent.toString().contains("La categoría no puede estar vacía."));
    }

    @Test
    void testAddProductNegativePriceOrStock() {
        inventory.addProduct("Laptop", -10.0, 10, "Electrónicos");
        assertTrue(outContent.toString().contains("Precio y stock deben ser no negativos."));
    }

    @Test
    void testAddProductDuplicate() {
        inventory.addProduct("Laptop", 999.99, 10, "Electrónicos");
        outContent.reset();
        inventory.addProduct("Laptop", 999.99, 5, "Electrónicos");
        assertTrue(outContent.toString().contains("El producto laptop ya existe."));
    }

    @Test
    void testModifyStockValid() {
        inventory.addProduct("Laptop", 999.99, 10, "Electrónicos");
        inventory.modifyStock("Laptop", 8);
        Product product = inventory.getProduct("Laptop");
        assertEquals(8, product.getStock());
    }

    @Test
    void testGetStockValid() {
        inventory.addProduct("Laptop", 999.99, 10, "Electrónicos");
        inventory.getStock("Laptop");
        assertTrue(outContent.toString().contains("laptop Stock: 10"));
    }

    @Test
    void testGetStockNonExistent() {
        inventory.getStock("Laptop");
        assertTrue(outContent.toString().contains("Producto laptop no encontrado."));
    }

    @Test
    void testGetAllStock() {
        inventory.addProduct("Laptop", 999.99, 10, "Electrónicos");
        inventory.addProduct("Libro", 19.99, 3, "Libros");
        inventory.getAllStock();
        String output = outContent.toString();
        assertTrue(output.contains("Producto: laptop Stock: 10"));
        assertTrue(output.contains("Producto: libro Stock: 3"));
    }

    @Test
    void testGetAllStockEmpty() {
        inventory.getAllStock();
        assertTrue(outContent.toString().contains("No hay productos en el inventario."));
    }
}