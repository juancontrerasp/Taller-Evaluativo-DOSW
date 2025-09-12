package edu.dosw.taller.Taller_Evaluativo_DOSW.Comportamiento;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LogAgentTest {
    @Mock
    private Product product;

    private LogAgent logAgent;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        logAgent = new LogAgent();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }

    @Test
    void testLogStockUpdate() {
        when(product.getName()).thenReturn("Laptop");
        when(product.getStock()).thenReturn(8);

        logAgent.update(product);

        String output = outContent.toString().trim();
        assertTrue(output.contains("Laptop: 8 unidades"),
                "Se esperaba 'Laptop: 8 unidades', pero la salida fue: " + output);
    }

    @Test
    void testLogDifferentStock() {
        when(product.getName()).thenReturn("Libro");
        when(product.getStock()).thenReturn(3);

        logAgent.update(product);

        String output = outContent.toString().trim();
        assertTrue(output.contains("Libro: 3 unidades"),
                "Se esperaba 'Libro: 3 unidades', pero la salida fue: " + output);
    }
}