package edu.dosw.taller.Taller_Evaluativo_DOSW;
    
public class AlertAgent implements StockObserver{
    @Override
    public void writeUpdate(Product product){
        if (product.getStock() < 5){
            System.out.println("ALERTA: Quedan menos de 5 unidades del producto " + product.getName());
        } 
    }
}