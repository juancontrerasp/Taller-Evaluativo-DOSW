package edu.dosw.taller.Taller_Evaluativo_DOSW;

public class LogAgent implements StockObserver{
    @Override
    public void writeUpdate(Product product){
        System.out.println(product.getName() + ": " + product.getStock() + " unidades");
    }
}