package edu.dosw.taller.Taller_Evaluativo_DOSW.Comportamiento;

public class LogAgent implements StockObserver{
    @Override
    public void update(Product product){
        System.out.println(product.getName() + ": " + product.getStock() + " unidades");
    }
}