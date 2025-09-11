package edu.dosw.taller.Taller_Evaluativo_DOSW.Comportamiento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {
    private HashMap<String, Product> products = new HashMap<>();
    private List<StockObserver> observers = new ArrayList<>();

    public Inventory() {
        observers.add(new AlertAgent());
        observers.add(new LogAgent());
    }

    public Inventory(StockObserver... observers) {
        this.observers = new ArrayList<>();
        for (StockObserver observer : observers) {
            this.observers.add(observer);
        }
    }

    public void addProduct(String name, double price, int stock, String category) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
        }
        if (category == null || category.trim().isEmpty()) {
            System.out.println("La categoría no puede estar vacía.");
        }
        if (price < 0 || stock < 0) {
            System.out.println("Precio y stock deben ser no negativos.");
        }
        name = name.toLowerCase();
        if (products.containsKey(name)) {
            System.out.println("El producto " + name + " ya existe.");
        }
        Product product = new Product(name, price, stock, category);
        for (StockObserver observer : observers) {
            product.addObserver(observer);
        }
        products.put(name, product);
    }

    public void modifyStock(String product, int stock) {
        product = product.toLowerCase();
        Product p = products.get(product);
        if (p == null) {
            System.out.println("Producto " + product + " no encontrado.");
        }
        p.modifyStock(stock);
    }

    public Product getProduct(String product) {
        return products.get(product.toLowerCase());
    }

    public void getStock(String product) {
        product = product.toLowerCase();
        Product p = products.get(product);
        if (p == null) {
            System.out.println("Producto " + product + " no encontrado.");
            return;
        }
        System.out.println(product + " Stock: " + p.getStock());
    }

    public void getAllStock() {
        if (products.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
            return;
        }

        String result = products.values().stream()
                .map(p -> "Producto: " + p.getName() + " Stock: " + p.getStock())
                .collect(Collectors.joining("\n"));
        System.out.println(result);
    }
}