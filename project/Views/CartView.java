package Views;

import Models.Cart;
import Models.CartItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Cart view for displaying and managing shopping cart contents.
 * Shows cart items and provides options to modify quantities or proceed to checkout.
 */
public class CartView extends JDialog {
    private Cart cart;
    
    // Table components
    private JTable cartTable;
    private DefaultTableModel tableModel;
    
    // Labels
    private JLabel subtotalLabel;
    private JLabel taxLabel;
    private JLabel totalLabel;
    
    // Buttons
    private JButton updateQuantityButton;
    private JButton removeItemButton;
    private JButton clearCartButton;
    private JButton continueShoppingButton;
    private JButton checkoutButton;
    
    // Tax rate (example: 16%)
    private static final double TAX_RATE = 0.16;

    /**
     * Creates a new CartView for the specified cart.
     * 
     * @param parent Parent frame
     * @param cart   The shopping cart to display
     */
    public CartView(JFrame parent, Cart cart) {
        super(parent, "Shopping Cart", true);
        this.cart = cart;
        initializeComponents();
        setupLayout();
        configureDialog();
        updateCart();
    }

    /**
     * Initializes the UI components.
     */
    private void initializeComponents() {
        // Table
        String[] columns = {"Product", "Price", "Quantity", "Subtotal"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        cartTable = new JTable(tableModel);
        cartTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cartTable.setRowHeight(25);
        
        // Labels
        subtotalLabel = new JLabel("Subtotal: $0.00");
        taxLabel = new JLabel("Tax (16%): $0.00");
        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        // Buttons
        updateQuantityButton = new JButton("Update Quantity");
        removeItemButton = new JButton("Remove Item");
        clearCartButton = new JButton("Clear Cart");
        continueShoppingButton = new JButton("Continue Shopping");
        checkoutButton = new JButton("Proceed to Checkout");
    }

    /**
     * Sets up the layout.
     */
    private void setupLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Header
        JLabel headerLabel = new JLabel("Your Shopping Cart", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Table
        JScrollPane tableScrollPane = new JScrollPane(cartTable);
        tableScrollPane.setPreferredSize(new Dimension(500, 250));
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Right panel with totals and actions
        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        
        // Totals panel
        JPanel totalsPanel = new JPanel();
        totalsPanel.setLayout(new BoxLayout(totalsPanel, BoxLayout.Y_AXIS));
        totalsPanel.setBorder(BorderFactory.createTitledBorder("Order Summary"));
        totalsPanel.add(subtotalLabel);
        totalsPanel.add(Box.createVerticalStrut(5));
        totalsPanel.add(taxLabel);
        totalsPanel.add(Box.createVerticalStrut(10));
        totalsPanel.add(new JSeparator());
        totalsPanel.add(Box.createVerticalStrut(10));
        totalsPanel.add(totalLabel);
        rightPanel.add(totalsPanel, BorderLayout.NORTH);

        // Action buttons
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        updateQuantityButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateQuantityButton.setMaximumSize(new Dimension(180, 30));
        actionPanel.add(updateQuantityButton);
        actionPanel.add(Box.createVerticalStrut(5));
        
        removeItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeItemButton.setMaximumSize(new Dimension(180, 30));
        removeItemButton.setBackground(new Color(231, 76, 60));
        removeItemButton.setForeground(Color.WHITE);
        actionPanel.add(removeItemButton);
        actionPanel.add(Box.createVerticalStrut(5));
        
        clearCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clearCartButton.setMaximumSize(new Dimension(180, 30));
        clearCartButton.setBackground(new Color(149, 165, 166));
        clearCartButton.setForeground(Color.WHITE);
        actionPanel.add(clearCartButton);
        
        rightPanel.add(actionPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        // Bottom buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        continueShoppingButton.setPreferredSize(new Dimension(160, 35));
        bottomPanel.add(continueShoppingButton);
        
        checkoutButton.setPreferredSize(new Dimension(180, 35));
        checkoutButton.setBackground(new Color(46, 204, 113));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        bottomPanel.add(checkoutButton);
        
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Configures the dialog properties.
     */
    private void configureDialog() {
        setSize(700, 450);
        setLocationRelativeTo(getParent());
        setResizable(false);
    }

    /**
     * Updates the cart display with current cart contents.
     */
    public void updateCart() {
        tableModel.setRowCount(0);
        
        if (cart != null) {
            for (CartItem item : cart.getItems()) {
                Object[] row = {
                    item.getProduct().getName(),
                    String.format("$%.2f", item.getProduct().getPrice()),
                    item.getQuantity(),
                    String.format("$%.2f", item.getSubtotal())
                };
                tableModel.addRow(row);
            }
            
            double subtotal = cart.getTotal();
            double tax = subtotal * TAX_RATE;
            double total = subtotal + tax;
            
            subtotalLabel.setText(String.format("Subtotal: $%.2f", subtotal));
            taxLabel.setText(String.format("Tax (16%%): $%.2f", tax));
            totalLabel.setText(String.format("Total: $%.2f", total));
        }
    }

    /**
     * Gets the selected row in the cart table.
     * 
     * @return Selected row index or -1 if none selected
     */
    public int getSelectedRow() {
        return cartTable.getSelectedRow();
    }

    /**
     * Shows a dialog to update quantity.
     * 
     * @return New quantity or -1 if cancelled
     */
    public int showQuantityDialog() {
        String input = JOptionPane.showInputDialog(this, 
                "Enter new quantity:", 
                "Update Quantity", 
                JOptionPane.QUESTION_MESSAGE);
        if (input != null) {
            try {
                int quantity = Integer.parseInt(input);
                if (quantity > 0) {
                    return quantity;
                }
            } catch (NumberFormatException e) {
                // Invalid input
            }
        }
        return -1;
    }

    /**
     * Gets the calculated total including tax.
     * 
     * @return Total amount
     */
    public double getTotal() {
        if (cart != null) {
            double subtotal = cart.getTotal();
            return subtotal + (subtotal * TAX_RATE);
        }
        return 0;
    }

    // Button action listeners
    public void addUpdateQuantityListener(ActionListener listener) {
        updateQuantityButton.addActionListener(listener);
    }

    public void addRemoveItemListener(ActionListener listener) {
        removeItemButton.addActionListener(listener);
    }

    public void addClearCartListener(ActionListener listener) {
        clearCartButton.addActionListener(listener);
    }

    public void addContinueShoppingListener(ActionListener listener) {
        continueShoppingButton.addActionListener(listener);
    }

    public void addCheckoutListener(ActionListener listener) {
        checkoutButton.addActionListener(listener);
    }

    /**
     * Shows the cart view.
     */
    public void showView() {
        setVisible(true);
    }

    /**
     * Hides the cart view.
     */
    public void hideView() {
        setVisible(false);
    }

    /**
     * Shows a message dialog.
     * 
     * @param message Message to display
     * @param title   Dialog title
     * @param type    Message type
     */
    public void showMessage(String message, String title, int type) {
        JOptionPane.showMessageDialog(this, message, title, type);
    }
}
