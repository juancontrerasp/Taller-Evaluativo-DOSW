package edu.dosw.taller.Taller_Evaluativo_DOSW;
import java.util.*;
import java.util.stream.Collectors;

public class Inventory {
    private HashMap<String, Product> products = new HashMap<>();
    private StockObserver alertAgent = new AlertAgent();
    private StockObserver logAgent = new LogAgent();

    public void getAllStock() {
        String result = products.values().stream()
                .map(p -> "Producto: " + p.getName() + " Stock: " + p.getStock())
                .collect(Collectors.joining("\n"));
        System.out.println(result);
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
    
    public void modifyStock(String product, int stock){
        products.get(product).modifyStock(stock);
    }
    
    public void addProduct(String name, double price, int stock, String category){
        name = name.toLowerCase();
        Product p = new Product(name,price,stock,category);
        p.addObserver(alertAgent);
        p.addObserver(logAgent);

        products.put(name, p);
    }

    public Product getProduct(String product){
        return products.get(product.toLowerCase());
    }
    
    
}