
package Payments;
import javax.swing.JOptionPane;
public class PagoPayPal extends Pago {
    private String email;
    public PagoPayPal(double monto, String email){
        super(monto);
        this.email = email;
    }
    @Override
    public boolean realizarPago(){
        if(email==null || !email.contains("@")){
            JOptionPane.showMessageDialog(null, "Email PayPal inválido");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Pago con PayPal procesado: $" + monto);
        return true;
    }
}
