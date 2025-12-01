
package Views;
import Payments.*;
import javax.swing.JOptionPane;

public class PayView extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger =
        java.util.logging.Logger.getLogger(PayView.class.getName());

    private double total;
        

// Constructor que recibe el TOTAL desde ClientView
    public PayView(double total) {
        initComponents();
        this.total = total;
        lblTotal.setText("$" + total);
        setLocationRelativeTo(null);
    }

      //constructor
    public PayView() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel0 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnTarjeta = new javax.swing.JButton();
        btnPayPal = new javax.swing.JButton();
        btnEfectivo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNumeroTarjeta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombreTitular = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmailPayPal = new javax.swing.JTextField();
        btnPagar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(97, 120, 145));

        jPanel2.setBackground(new java.awt.Color(51, 102, 0));

        jLabel0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/silueta_80x65-removebg-preview.png"))); // NOI18N

        btnVolver.setBackground(new java.awt.Color(255, 255, 255));
        btnVolver.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 0, 0));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVolver)
                .addGap(112, 112, 112)
                .addComponent(jLabel0)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVolver))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel0)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Total a pagar:");

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 0, 0));
        lblTotal.setText("$0.00 MXN");

        btnTarjeta.setText("Pagar con tarjeta");
        btnTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarjetaActionPerformed(evt);
            }
        });

        btnPayPal.setText("Pagar con PayPal");
        btnPayPal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayPalActionPerformed(evt);
            }
        });

        btnEfectivo.setText("Pagar en Efectivo");
        btnEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEfectivoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(213, 184, 147));
        jLabel2.setText("Numero de tarjeta:");

        txtNumeroTarjeta.setBackground(new java.awt.Color(255, 255, 255));
        txtNumeroTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroTarjetaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(213, 184, 147));
        jLabel3.setText("Nombre del Titular:");

        txtNombreTitular.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreTitular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreTitularActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(213, 184, 147));
        jLabel4.setText("Email PayPal:");

        txtEmailPayPal.setBackground(new java.awt.Color(255, 255, 255));
        txtEmailPayPal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailPayPalActionPerformed(evt);
            }
        });

        btnPagar.setBackground(new java.awt.Color(204, 255, 204));
        btnPagar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnPagar.setForeground(new java.awt.Color(51, 153, 0));
        btnPagar.setText("PAGAR");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnEfectivo)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblTotal)
                            .addGap(50, 50, 50)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnPayPal)
                                .addComponent(btnTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnPagar)
                            .addGap(179, 179, 179)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNumeroTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNombreTitular)
                                .addComponent(txtEmailPayPal)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTotal)
                    .addComponent(btnTarjeta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPayPal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEfectivo)
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNumeroTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombreTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmailPayPal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnPagar)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarjetaActionPerformed
         String numero = txtNumeroTarjeta.getText();
        String nombre = txtNombreTitular.getText();

        Pago pago = new PagoTarjeta(total, numero, nombre);

        if (pago.realizarPago()) {
            JOptionPane.showMessageDialog(this, "Compra finalizada con tarjeta");
            this.dispose();
            new ClientView().setVisible(true);
        }
    }//GEN-LAST:event_btnTarjetaActionPerformed

    private void btnPayPalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayPalActionPerformed
        String email = txtEmailPayPal.getText();

        Pago pago = new PagoPayPal(total, email);

        if (pago.realizarPago()) {
            JOptionPane.showMessageDialog(this, "Compra finalizada con PayPal");
            this.dispose();
            new ClientView().setVisible(true);
        }
    }//GEN-LAST:event_btnPayPalActionPerformed

    private void btnEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEfectivoActionPerformed
        Pago pago = new PagoEfectivo(total);

        if (pago.realizarPago()) {
            JOptionPane.showMessageDialog(this, "Compra finalizada en efectivo");
            this.dispose();
            new ClientView().setVisible(true);
        }
    }//GEN-LAST:event_btnEfectivoActionPerformed

    private void txtNumeroTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroTarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroTarjetaActionPerformed

    private void txtNombreTitularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreTitularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreTitularActionPerformed

    private void txtEmailPayPalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailPayPalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailPayPalActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        new ClientView().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        JOptionPane.showMessageDialog(this, 
        "Usa los botones:\nTarjeta, PayPal o Efectivo", 
        "Selecciona un método de pago",
        JOptionPane.INFORMATION_MESSAGE
    );
    }//GEN-LAST:event_btnPagarActionPerformed

  
    public static void main(String args[]) {
    
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        

        
        java.awt.EventQueue.invokeLater(() -> new PayView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEfectivo;
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton btnPayPal;
    private javax.swing.JButton btnTarjeta;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel0;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtEmailPayPal;
    private javax.swing.JTextField txtNombreTitular;
    private javax.swing.JTextField txtNumeroTarjeta;
    // End of variables declaration//GEN-END:variables
}
