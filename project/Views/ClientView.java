package Views;

import Models.Product;
import Models.User;
import Models.CartItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Client view for shopping functionality.
 * Provides interface for browsing products and adding them to cart.
 */
public class ClientView extends JFrame {
    private User currentUser;
    
    // Product display components
    private JTable productTable;
    private DefaultTableModel productTableModel;
    private JTextField searchField;
    private JComboBox<String> categoryFilter;
    
    // Cart components
    private JTable cartTable;
    private DefaultTableModel cartTableModel;
    private JLabel cartTotalLabel;
    private JSpinner quantitySpinner;
    
    // Buttons
    private JButton addToCartButton;
    private JButton removeFromCartButton;
    private JButton checkoutButton;
    private JButton searchButton;
    private JButton logoutButton;

    /**
     * Creates a new ClientView for the specified user.
     * 
     * @param user The logged-in client user
     */
    public ClientView(User user) {
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
        String[] productColumns = {"ID", "Name", "Price", "Stock", "Category"};
        productTableModel = new DefaultTableModel(productColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        productTable = new JTable(productTableModel);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Cart table
        String[] cartColumns = {"Product", "Quantity", "Subtotal"};
        cartTableModel = new DefaultTableModel(cartColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        cartTable = new JTable(cartTableModel);
        
        // Search and filter
        searchField = new JTextField(20);
        categoryFilter = new JComboBox<>(new String[]{"All Categories", "Electronics", "Clothing", "Food", "Other"});
        
        // Quantity spinner
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        
        // Labels
        cartTotalLabel = new JLabel("Total: $0.00");
        cartTotalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Buttons
        addToCartButton = new JButton("Add to Cart");
        removeFromCartButton = new JButton("Remove");
        checkoutButton = new JButton("Checkout");
        searchButton = new JButton("Search");
        logoutButton = new JButton("Logout");
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

        // Split pane for products and cart
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                createProductsPanel(), createCartPanel());
        splitPane.setDividerLocation(550);
        splitPane.setResizeWeight(0.6);
        
        mainPanel.add(splitPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    /**
     * Creates the header panel.
     * 
     * @return Header panel
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(46, 204, 113));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel titleLabel = new JLabel("Shop");
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
     * Creates the products panel.
     * 
     * @return Products panel
     */
    private JPanel createProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Available Products"));

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(new JLabel("Category:"));
        searchPanel.add(categoryFilter);
        panel.add(searchPanel, BorderLayout.NORTH);

        // Product table
        JScrollPane tableScrollPane = new JScrollPane(productTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Add to cart panel
        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addPanel.add(new JLabel("Quantity:"));
        quantitySpinner.setPreferredSize(new Dimension(60, 25));
        addPanel.add(quantitySpinner);
        
        addToCartButton.setBackground(new Color(46, 204, 113));
        addToCartButton.setForeground(Color.WHITE);
        addPanel.add(addToCartButton);
        
        panel.add(addPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates the shopping cart panel.
     * 
     * @return Cart panel
     */
    private JPanel createCartPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Shopping Cart"));

        // Cart table
        JScrollPane tableScrollPane = new JScrollPane(cartTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Bottom panel with total and buttons
        JPanel bottomPanel = new JPanel(new BorderLayout());
        
        // Remove button
        JPanel removePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        removeFromCartButton.setBackground(new Color(231, 76, 60));
        removeFromCartButton.setForeground(Color.WHITE);
        removePanel.add(removeFromCartButton);
        bottomPanel.add(removePanel, BorderLayout.WEST);
        
        // Total and checkout
        JPanel checkoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        checkoutPanel.add(cartTotalLabel);
        
        checkoutButton.setBackground(new Color(52, 152, 219));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        checkoutPanel.add(checkoutButton);
        
        bottomPanel.add(checkoutPanel, BorderLayout.EAST);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Configures the frame properties.
     */
    private void configureFrame() {
        setTitle("POS System - Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600);
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
     * Updates the cart table with the provided items.
     * 
     * @param items List of cart items
     */
    public void updateCartTable(List<CartItem> items) {
        cartTableModel.setRowCount(0);
        double total = 0;
        for (CartItem item : items) {
            Object[] row = {
                item.getProduct().getName(),
                item.getQuantity(),
                String.format("$%.2f", item.getSubtotal())
            };
            cartTableModel.addRow(row);
            total += item.getSubtotal();
        }
        cartTotalLabel.setText(String.format("Total: $%.2f", total));
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
     * Gets the selected cart item index.
     * 
     * @return Selected cart row or -1 if none selected
     */
    public int getSelectedCartRow() {
        return cartTable.getSelectedRow();
    }

    /**
     * Gets the quantity from the spinner.
     * 
     * @return Selected quantity
     */
    public int getQuantity() {
        return (Integer) quantitySpinner.getValue();
    }

    /**
     * Gets the search text.
     * 
     * @return Search text
     */
    public String getSearchText() {
        return searchField.getText();
    }

    /**
     * Gets the selected category.
     * 
     * @return Selected category
     */
    public String getSelectedCategory() {
        return (String) categoryFilter.getSelectedItem();
    }

    // Button action listeners
    public void addAddToCartListener(ActionListener listener) {
        addToCartButton.addActionListener(listener);
    }

    public void addRemoveFromCartListener(ActionListener listener) {
        removeFromCartButton.addActionListener(listener);
    }

    public void addCheckoutListener(ActionListener listener) {
        checkoutButton.addActionListener(listener);
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
        searchField.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    /**
     * Shows the client view.
     */
    public void showView() {
        setVisible(true);
    }

    /**
     * Hides the client view.
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
