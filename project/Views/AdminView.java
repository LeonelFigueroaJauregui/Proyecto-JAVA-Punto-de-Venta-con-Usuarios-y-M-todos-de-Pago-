package Views;

import Models.Product;
import Models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Admin view for administrative functions.
 * Provides interface for managing products, users, and viewing reports.
 */
public class AdminView extends JFrame {
    private User currentUser;
    
    // Product management components
    private JTable productTable;
    private DefaultTableModel productTableModel;
    private JTextField productNameField;
    private JTextField productPriceField;
    private JTextField productStockField;
    private JTextField productCategoryField;
    private JTextArea productDescriptionArea;
    
    // Buttons
    private JButton addProductButton;
    private JButton editProductButton;
    private JButton deleteProductButton;
    private JButton refreshButton;
    private JButton logoutButton;
    
    // Panels
    private JTabbedPane tabbedPane;

    /**
     * Creates a new AdminView for the specified user.
     * 
     * @param user The logged-in admin user
     */
    public AdminView(User user) {
        this.currentUser = user;
        initializeComponents();
        setupLayout();
        configureFrame();
    }

    /**
     * Initializes the UI components.
     */
    private void initializeComponents() {
        // Product table
        String[] columnNames = {"ID", "Name", "Price", "Stock", "Category"};
        productTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        productTable = new JTable(productTableModel);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Product form fields
        productNameField = new JTextField(20);
        productPriceField = new JTextField(10);
        productStockField = new JTextField(10);
        productCategoryField = new JTextField(15);
        productDescriptionArea = new JTextArea(3, 20);
        productDescriptionArea.setLineWrap(true);
        
        // Buttons
        addProductButton = new JButton("Add Product");
        editProductButton = new JButton("Edit Product");
        deleteProductButton = new JButton("Delete Product");
        refreshButton = new JButton("Refresh");
        logoutButton = new JButton("Logout");
        
        // Tabbed pane
        tabbedPane = new JTabbedPane();
    }

    /**
     * Sets up the layout.
     */
    private void setupLayout() {
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Header
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Create tabs
        JPanel productsPanel = createProductsPanel();
        JPanel reportsPanel = createReportsPanel();
        
        tabbedPane.addTab("Products", productsPanel);
        tabbedPane.addTab("Reports", reportsPanel);
        
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    /**
     * Creates the header panel.
     * 
     * @return Header panel
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(52, 73, 94));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel titleLabel = new JLabel("Admin Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setOpaque(false);
        
        JLabel userLabel = new JLabel("Welcome, " + currentUser.getFullName());
        userLabel.setForeground(Color.WHITE);
        userPanel.add(userLabel);
        
        logoutButton.setBackground(new Color(231, 76, 60));
        logoutButton.setForeground(Color.WHITE);
        userPanel.add(logoutButton);
        
        headerPanel.add(userPanel, BorderLayout.EAST);

        return headerPanel;
    }

    /**
     * Creates the products management panel.
     * 
     * @return Products panel
     */
    private JPanel createProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table panel
        JScrollPane tableScrollPane = new JScrollPane(productTable);
        tableScrollPane.setPreferredSize(new Dimension(500, 300));
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Product Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(productNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Price:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(productPriceField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Stock:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(productStockField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(productCategoryField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.NORTHEAST;
        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JScrollPane(productDescriptionArea), gbc);

        panel.add(formPanel, BorderLayout.EAST);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        addProductButton.setBackground(new Color(46, 204, 113));
        addProductButton.setForeground(Color.WHITE);
        buttonPanel.add(addProductButton);
        
        editProductButton.setBackground(new Color(52, 152, 219));
        editProductButton.setForeground(Color.WHITE);
        buttonPanel.add(editProductButton);
        
        deleteProductButton.setBackground(new Color(231, 76, 60));
        deleteProductButton.setForeground(Color.WHITE);
        buttonPanel.add(deleteProductButton);
        
        refreshButton.setBackground(new Color(149, 165, 166));
        refreshButton.setForeground(Color.WHITE);
        buttonPanel.add(refreshButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates the reports panel.
     * 
     * @return Reports panel
     */
    private JPanel createReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel label = new JLabel("Sales Reports and Analytics", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(label, BorderLayout.NORTH);
        
        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setText("Sales reports will be displayed here.\n\n" +
                "Features:\n" +
                "- Daily sales summary\n" +
                "- Monthly reports\n" +
                "- Product performance\n" +
                "- Payment method statistics");
        panel.add(new JScrollPane(reportArea), BorderLayout.CENTER);
        
        return panel;
    }

    /**
     * Configures the frame properties.
     */
    private void configureFrame() {
        setTitle("POS System - Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 500));
    }

    /**
     * Updates the product table with the provided list.
     * 
     * @param products List of products to display
     */
    public void updateProductTable(List<Product> products) {
        productTableModel.setRowCount(0);
        for (Product product : products) {
            Object[] row = {
                product.getId(),
                product.getName(),
                String.format("$%.2f", product.getPrice()),
                product.getStock(),
                product.getCategory()
            };
            productTableModel.addRow(row);
        }
    }

    /**
     * Gets the selected product ID from the table.
     * 
     * @return Selected product ID or -1 if none selected
     */
    public int getSelectedProductId() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            return (int) productTableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }

    /**
     * Clears the product form fields.
     */
    public void clearProductForm() {
        productNameField.setText("");
        productPriceField.setText("");
        productStockField.setText("");
        productCategoryField.setText("");
        productDescriptionArea.setText("");
    }

    // Button action listeners
    public void addAddProductListener(ActionListener listener) {
        addProductButton.addActionListener(listener);
    }

    public void addEditProductListener(ActionListener listener) {
        editProductButton.addActionListener(listener);
    }

    public void addDeleteProductListener(ActionListener listener) {
        deleteProductButton.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    // Getters for form fields
    public String getProductName() {
        return productNameField.getText();
    }

    public String getProductPrice() {
        return productPriceField.getText();
    }

    public String getProductStock() {
        return productStockField.getText();
    }

    public String getProductCategory() {
        return productCategoryField.getText();
    }

    public String getProductDescription() {
        return productDescriptionArea.getText();
    }

    /**
     * Shows the admin view.
     */
    public void showView() {
        setVisible(true);
    }

    /**
     * Hides the admin view.
     */
    public void hideView() {
        setVisible(false);
    }

    /**
     * Shows a message dialog.
     * 
     * @param message Message to display
     * @param title   Dialog title
     * @param type    Message type (JOptionPane constant)
     */
    public void showMessage(String message, String title, int type) {
        JOptionPane.showMessageDialog(this, message, title, type);
    }
}
