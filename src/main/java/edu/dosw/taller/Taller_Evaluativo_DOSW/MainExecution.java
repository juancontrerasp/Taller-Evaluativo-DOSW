
package edu.dosw.taller.Taller_Evaluativo_DOSW;
import edu.dosw.taller.Taller_Evaluativo_DOSW.Comportamiento.*;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class MainExecution {
    private Inventory inventory;

    public MainExecution() {
        inventory = new Inventory(Arrays.asList(new AlertAgent(), new LogAgent()));
    }

    @PostConstruct
    public void main1() {
        inventory.addProduct("Laptop", 999.99, 10, "Electrónicos");
        inventory.addProduct("Libro", 19.99, 3, "Libros");
        inventory.addProduct("Telefono", 599.99, 7, "Electrónicos");
        inventory.getAllStock();
        inventory.modifyStock("Laptop", 8);
        inventory.modifyStock("Libro", 4);
        inventory.modifyStock("Telefono", 6);
        inventory.getStock("Laptop");
        inventory.getStock("Libro");
        Product laptop = inventory.getProduct("Laptop");
        System.out.println("Verificación: Producto Laptop tiene stock " + laptop.getStock());
    }
}