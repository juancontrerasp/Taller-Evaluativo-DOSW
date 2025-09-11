package edu.dosw.taller.Taller_Evaluativo_DOSW;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LogAgentTest {
    private LogAgent logAgent;
    private Product mockProduct;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        logAgent = new LogAgent();
        mockProduct = mock(Product.class);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testLogStock() {
        when(mockProduct.getName()).thenReturn("Laptop");
        when(mockProduct.getStock()).thenReturn(10);
        logAgent.writeUpdate(mockProduct);
        assertTrue(outContent.toString().contains("Laptop: 10 unidades"));
    }

    @Test
    void testLogZeroStock() {
        when(mockProduct.getName()).thenReturn("Laptop");
        when(mockProduct.getStock()).thenReturn(0);
        logAgent.writeUpdate(mockProduct);
        assertTrue(outContent.toString().contains("Laptop: 0 unidades"));
    }
}