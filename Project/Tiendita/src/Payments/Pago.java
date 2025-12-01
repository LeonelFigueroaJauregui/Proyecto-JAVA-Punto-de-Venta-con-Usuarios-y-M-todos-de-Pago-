
package Payments;

/**
 * Clase abstracta Pago: define realizarPago() para implementar polimorfismo.
 */
public abstract class Pago {
    protected double monto;
    public Pago(double monto){ this.monto = monto; }
    public abstract boolean realizarPago();
}
