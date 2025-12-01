
package Payments;
import javax.swing.JOptionPane;
public class PagoEfectivo extends Pago {
    public PagoEfectivo(double monto){ super(monto); }
    @Override
    public boolean realizarPago(){
        // Efectivo: siempre OK en esta simulación
        JOptionPane.showMessageDialog(null, "Pago en efectivo registrado: $" + monto);
        return true;
    }
}
