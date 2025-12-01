
package Views;
import Models.Inventory;
import Models.Product;
import javax.swing.DefaultComboBoxModel;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class ClientView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ClientView.class.getName());
    
    
    double precio= 0;
    int cantidad = 0;
    DefaultTableModel modelo = new DefaultTableModel();
    ArrayList<Venta> listaVentas = new ArrayList<Venta>(); //para que se pasen los datos de Productos y Precio e inicialice en 0

    public ClientView() {
        initComponents();
        setSize(1208,680);
        setResizable(false);
        setTitle("Panel de Administración");
        setLocationRelativeTo(null); //para que no se pueda mover la pantalla
        this.repaint();
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        // cargar productos desde Inventory
        for(Product p: Inventory.getProductos()){
            comboModel.addElement(p.getName());
        }
        cboProducto.setModel(comboModel);
        modelo.addColumn("DESCRIPCIÓN");
        modelo.addColumn("PRECIO U,");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("IMPORTE"); //le añade los titulos a las columnas
        actualizarTabla();
        calcularPrecio();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menu = new javax.swing.JPanel();
        jPanelProducs = new javax.swing.JPanel();
        jLabelProducts = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        Cabecera = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_loginOut = new javax.swing.JButton();
        lbl_photo = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        lblImporte = new javax.swing.JLabel();
        cboProducto = new javax.swing.JComboBox<>();
        spnCantidad = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblIva = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnPagar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Menu.setBackground(new java.awt.Color(111, 77, 56));
        Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelProducs.setBackground(new java.awt.Color(97, 120, 145));
        jPanelProducs.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelProducts.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelProducts.setForeground(new java.awt.Color(37, 52, 79));
        jLabelProducts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cesta_40x40-removebg-preview.png"))); // NOI18N
        jLabelProducts.setText("PRODUCTOS");
        jPanelProducs.add(jLabelProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 30));

        Menu.add(jPanelProducs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 35));

        getContentPane().add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 200, 580));

        jPanel2.setBackground(new java.awt.Color(213, 184, 147));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo_200x100-removebg-preview.png"))); // NOI18N
        jPanel2.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 100));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 100));

        Cabecera.setBackground(new java.awt.Color(111, 77, 56));
        Cabecera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(213, 184, 147));
        jLabel2.setText("TIENDA DE ABARROTES");
        Cabecera.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 340, 100));

        txt_loginOut.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_loginOut.setText("SALIR");
        txt_loginOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txt_loginOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_loginOutActionPerformed(evt);
            }
        });
        Cabecera.add(txt_loginOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 35, -1, 30));

        lbl_photo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/silueta_80x65-removebg-preview.png"))); // NOI18N
        Cabecera.add(lbl_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 80, 65));

        getContentPane().add(Cabecera, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1010, 100));

        jTabbedPane1.setForeground(new java.awt.Color(37, 52, 79));

        jPanel1.setBackground(new java.awt.Color(97, 120, 145));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(37, 52, 79));
        jLabel1.setText("PRODUCTO:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(37, 52, 79));
        jLabel3.setText("CANTIDAD:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        tblProductos.setForeground(new java.awt.Color(37, 52, 79));
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblProductos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 920, 250));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(37, 52, 79));
        jLabel4.setText("PRECIO:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(37, 52, 79));
        jLabel5.setText("IMPORTE:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, -1, -1));

        lblPrecio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(37, 52, 79));
        lblPrecio.setText("$0.00 MXN");
        jPanel1.add(lblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 140, -1));

        lblImporte.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblImporte.setForeground(new java.awt.Color(37, 52, 79));
        lblImporte.setText("$0.00 MXN");
        jPanel1.add(lblImporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 140, -1));

        cboProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cboProducto.setForeground(new java.awt.Color(37, 52, 79));
        cboProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboProductoActionPerformed(evt);
            }
        });
        jPanel1.add(cboProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        spnCantidad.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        spnCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 30, 1));
        spnCantidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        spnCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnCantidadStateChanged(evt);
            }
        });
        jPanel1.add(spnCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(37, 52, 79));
        jLabel8.setText("SUBTOTAL:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 400, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(37, 52, 79));
        jLabel9.setText("IVA:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(37, 52, 79));
        jLabel10.setText("TOTAL:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 480, -1, -1));

        lblSubtotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSubtotal.setForeground(new java.awt.Color(37, 52, 79));
        lblSubtotal.setText("$0.00 MXN");
        jPanel1.add(lblSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 400, 140, -1));

        lblIva.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblIva.setForeground(new java.awt.Color(37, 52, 79));
        lblIva.setText("$0.00 MXN");
        jPanel1.add(lblIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 440, 140, -1));

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(37, 52, 79));
        lblTotal.setText("$0.00 MXN");
        jPanel1.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 480, 140, -1));

        btnAgregar.setBackground(new java.awt.Color(99, 32, 36));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(37, 52, 79));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/agregar_70x70-removebg-preview.png"))); // NOI18N
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 30, 70, 70));

        btnPagar.setBackground(new java.awt.Color(37, 52, 79));
        btnPagar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnPagar.setForeground(new java.awt.Color(213, 184, 147));
        btnPagar.setText("PAGAR");
        btnPagar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });
        jPanel1.add(btnPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 470, -1, -1));

        jLabel6.setText("jLabel6");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, -1, -1));

        jTabbedPane1.addTab("PRODUCTOS", jPanel1);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 1010, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_loginOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_loginOutActionPerformed
        LoginView login = new LoginView();  
        login.setVisible(true);
        this.dispose(); //poder regresar al login si se presiona salir
    }//GEN-LAST:event_txt_loginOutActionPerformed

    private void cboProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboProductoActionPerformed
        calcularPrecio();
    }//GEN-LAST:event_cboProductoActionPerformed

    private void spnCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnCantidadStateChanged
        calcularPrecio();
    }//GEN-LAST:event_spnCantidadStateChanged
 public void calcularPrecio() {
        int idx = cboProducto.getSelectedIndex();
        if(idx>=0 && idx < Inventory.getProductos().size()){
            precio = Inventory.getProductos().get(idx).getPrice();
        } else { precio = 0; }
        cantidad = Integer.parseInt(spnCantidad.getValue().toString());
        lblPrecio.setText(aMoneda(precio));
        lblImporte.setText(aMoneda(precio * cantidad)); //poder calcular los precios multiplicandolo por la cantidad de productos agregados
    }
    
    public String aMoneda(double precio){
        return "$ " + Math.round(precio * 100.00)/100.0 + " MXN"; //redondea el precio
    }
    
    public void actualizarTabla(){
    while(modelo.getRowCount() > 0){
        modelo.removeRow(0);
    }

    double subtotal = 0;

    for (Venta v : listaVentas) {
        Object fila[] = new Object[4];
        fila[0] = v.getDescripcion();
        fila[1] = aMoneda(v.getPrecio());
        fila[2] = v.getCantidad();
        fila[3] = aMoneda(v.getImporte());
        subtotal += v.getImporte();
        modelo.addRow(fila); //se recorren los productos agregados a la tabla
    }

    double iva = subtotal * 0.16;
    double total = subtotal + iva; //para calcular y agregar el iva

    lblSubtotal.setText(aMoneda(subtotal));
    lblIva.setText(aMoneda(iva));
    lblTotal.setText(aMoneda(total)); //se actualizan las etiquetas

    tblProductos.setModel(modelo); //coloca ya lo actualizado
}

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        Venta venta = new Venta();
    venta.setId(cboProducto.getSelectedIndex());
    venta.setDescripcion(cboProducto.getSelectedItem().toString());
    venta.setPrecio(precio);
    venta.setCantidad(cantidad);
    venta.setImporte(precio * cantidad); //asigna datos desde la interfaz

    // Solo agregar si no se encontró (no duplicar filas)
    if (!buscarVenta(venta)) {
        listaVentas.add(venta);
    }

    actualizarTabla();
    borrarVenta(); //rastaura la tabla
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        double subtotal = 0;

    for (Venta v : listaVentas) {
        subtotal += v.getImporte();
    }

    double iva = subtotal * 0.16;
    double total = subtotal + iva;

    PayView payView = new PayView(total); //AQUÍ VA EL TOTAL REAL
    payView.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnPagarActionPerformed

    public boolean buscarVenta(Venta nueva){
    for (Venta v : listaVentas) {

        // Si el producto ya está en la lista
        if (v.getId() == nueva.getId()) {

            // Sumar cantidades
            int nuevaCantidad = v.getCantidad() + nueva.getCantidad();
            v.setCantidad(nuevaCantidad);

            // Recalcular importe
            v.setImporte(v.getPrecio() * nuevaCantidad);

            return true; //busca si ya esta un productoa gregado para saber si sumarlo o añadirlo
        }
    }
    return false;
}

    
    public void borrarVenta(){
    precio = 0;
    cantidad = 1;
    cboProducto.setSelectedIndex(0); //hace que el rpoducto por defecto sean las sabritas
    spnCantidad.setValue(1); //se debe de añadir minimo un producto
    calcularPrecio(); 
}

    /**
     * @param args the command line arguments
     */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ClientView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cabecera;
    private javax.swing.JLabel Logo;
    private javax.swing.JPanel Menu;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnPagar;
    private javax.swing.JComboBox<String> cboProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelProducts;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelProducs;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblImporte;
    private javax.swing.JLabel lblIva;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lbl_photo;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblProductos;
    private javax.swing.JButton txt_loginOut;
    // End of variables declaration//GEN-END:variables

    private static class Venta {

        public Venta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
    int id;
    String descripcion;
    int cantidad;
    double precio;
    double importe;
    }
}
