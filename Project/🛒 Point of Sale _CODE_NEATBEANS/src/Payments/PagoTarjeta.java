
package Payments;
import javax.swing.JOptionPane;
public class PagoTarjeta extends Pago {
    private String numero, nombre;
    public PagoTarjeta(double monto, String numero, String nombre){
        super(monto);
        this.numero = numero;
        this.nombre = nombre;
    }
    @Override
    public boolean realizarPago(){
        // Simulación sencilla: verificar longitud mínima y "procesar"
        if(numero==null || numero.replace(" ", "").length() < 12){
            JOptionPane.showMessageDialog(null, "Número de tarjeta inválido");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Pago con tarjeta procesado: $" + monto);
        return true;
    }
}
