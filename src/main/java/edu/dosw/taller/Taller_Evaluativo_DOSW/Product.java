package edu.dosw.taller.Taller_Evaluativo_DOSW;

import java.util.ArrayList;


public class Product{
    private String name;
    private double price;
    private int stock;
    private String category;
    private ArrayList<StockObserver> observers;
    
    public Product (String name, double price, int stock, String category){
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        observers = new ArrayList<>();
    }
    
    public void modifyStock(int stock){
        this.stock = stock;     
        notifyObservers();
    }
    
    public String getName(){
        return  name;
    }
        
    public double getPrice(){
        return  price;
    }
    
    public int getStock(){
        return  stock;
    }
    
    public String getCategory(){
        return  category;
    }
    
    public void addObserver(StockObserver observer){
        observers.add(observer);
    }
    
    public void notifyObservers(){
        for (StockObserver b : observers){
            b.writeUpdate(this);
        }
    }
    
}






