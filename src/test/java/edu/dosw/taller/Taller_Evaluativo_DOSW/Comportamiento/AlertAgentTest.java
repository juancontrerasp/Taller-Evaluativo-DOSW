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
public class AlertAgentTest {
    @Mock
    private Product product;

    private AlertAgent alertAgent;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        alertAgent = new AlertAgent();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }

    @Test
    void testAlertLowStock() {
        when(product.getName()).thenReturn("Laptop");
        when(product.getStock()).thenReturn(4);

        alertAgent.update(product);

        String output = outContent.toString().trim();
        assertTrue(output.contains("ALERTA: Quedan menos de 5 unidades del producto Laptop"),
                "Se esperaba una alerta para stock bajo, pero la salida fue: " + output);
    }

    @Test
    void testNoAlertHighStock() {
        when(product.getStock()).thenReturn(5); // Solo stubbing necesario

        alertAgent.update(product);

        String output = outContent.toString().trim();
        assertTrue(output.isEmpty(),
                "No se esperaba alerta para stock alto, pero la salida fue: " + output);
    }
}