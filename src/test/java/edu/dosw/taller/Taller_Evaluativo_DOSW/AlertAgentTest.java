package edu.dosw.taller.Taller_Evaluativo_DOSW;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlertAgentTest {
    private AlertAgent alertAgent;
    private Product mockProduct;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        alertAgent = new AlertAgent();
        mockProduct = mock(Product.class);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testAlertLowStock() {
        when(mockProduct.getName()).thenReturn("Laptop");
        when(mockProduct.getStock()).thenReturn(4);
        alertAgent.writeUpdate(mockProduct);
        assertTrue(outContent.toString().contains("ALERTA: Quedan menos de 5 unidades del producto Laptop"));
    }

    @Test
    void testNoAlertHighStock() {
        when(mockProduct.getName()).thenReturn("Laptop");
        when(mockProduct.getStock()).thenReturn(5);
        alertAgent.writeUpdate(mockProduct);
        assertTrue(outContent.toString().isEmpty());
    }
}