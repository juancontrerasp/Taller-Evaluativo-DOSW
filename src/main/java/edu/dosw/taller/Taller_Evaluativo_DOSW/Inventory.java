package edu.dosw.taller.Taller_Evaluativo_DOSW;
import java.util.*;
import java.util.stream.Collectors;

public class Inventory {
    private HashMap<String, Product> products;

    public void getAllStock() {
        String result = products.values().stream()
                .map(p -> "Producto: " + p.getName() + " Stock: " + p.getStock())
                .collect(Collectors.joining("\n"));
        System.out.println(result);
    }


    public void getStock(String product) {
        System.out.println(product + "Stock: " + products.get(product).getStock());
    }
    public void modifyStock(String product, int stock){
        products.get(product).modifyStock(stock);
    }
    
    public void addProduct(String name, double price, int stock, String category){
        products.put(name, new Product(name,price,stock,category));
    }
    
    
}