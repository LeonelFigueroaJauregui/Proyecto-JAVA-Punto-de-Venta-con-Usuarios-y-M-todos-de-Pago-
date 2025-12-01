import Models.*;
import Views.*;
import Payments.*;

import javax.swing.*;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * Main entry point for the Point of Sale (POS) System.
 * This class initializes the application and manages the main flow.
 * 
 * Features:
 * - User authentication with role-based access (Admin/Client)
 * - Product management (Admin)
 * - Shopping cart functionality (Client)
 * - Multiple payment methods (Cash, Card, Bank Transfer)
 * 
 * @author POS System Development Team
 * @version 1.0
 */
public class Main {
    
    // Sample data for demonstration
    private static List<User> users = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();
    private static User currentUser = null;
    private static Cart currentCart = null;
    
    /**
     * Main method - Application entry point.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize sample data
        initializeSampleData();
        
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Could not set system look and feel: " + e.getMessage());
        }
        
        // Start application on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            showLoginView();
        });
    }
    
    /**
     * Initializes sample users and products for demonstration.
     */
    private static void initializeSampleData() {
        // Create sample users
        users.add(new User(1, "admin", "admin123", "System Administrator", User.Role.ADMIN, "admin@pos.com"));
        users.add(new User(2, "client", "client123", "John Doe", User.Role.CLIENT, "john@email.com"));
        users.add(new User(3, "maria", "maria123", "Maria Garcia", User.Role.CLIENT, "maria@email.com"));
        
        // Create sample products
        products.add(new Product(1, "Laptop", "High-performance laptop", 999.99, 10, "Electronics"));
        products.add(new Product(2, "Smartphone", "Latest smartphone model", 699.99, 25, "Electronics"));
        products.add(new Product(3, "Headphones", "Wireless headphones", 149.99, 50, "Electronics"));
        products.add(new Product(4, "T-Shirt", "Cotton t-shirt", 29.99, 100, "Clothing"));
        products.add(new Product(5, "Jeans", "Denim jeans", 59.99, 75, "Clothing"));
        products.add(new Product(6, "Coffee", "Premium coffee beans", 14.99, 200, "Food"));
        products.add(new Product(7, "Chocolate", "Dark chocolate bar", 4.99, 150, "Food"));
        products.add(new Product(8, "Notebook", "Spiral notebook", 5.99, 300, "Other"));
        
        System.out.println("POS System initialized with sample data.");
        System.out.println("Sample login credentials:");
        System.out.println("  Admin: admin / admin123");
        System.out.println("  Client: client / client123");
    }
    
    /**
     * Shows the login view and handles authentication.
     */
    private static void showLoginView() {
        LoginView loginView = new LoginView();
        
        loginView.addLoginListener(e -> {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            
            // Authenticate user
            User user = authenticateUser(username, password);
            
            if (user != null) {
                currentUser = user;
                loginView.setStatus("Login successful!", false);
                loginView.hideView();
                
                // Show appropriate view based on role
                if (user.isAdmin()) {
                    showAdminView();
                } else {
                    currentCart = new Cart(user);
                    showClientView();
                }
            } else {
                loginView.setStatus("Invalid username or password", true);
            }
        });
        
        loginView.showView();
    }
    
    /**
     * Authenticates a user with the given credentials.
     * 
     * @param username Username
     * @param password Password
     * @return User object if authenticated, null otherwise
     */
    private static User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.validatePassword(password)) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * Shows the admin dashboard view.
     */
    private static void showAdminView() {
        AdminView adminView = new AdminView(currentUser);
        
        // Populate product table
        adminView.updateProductTable(products);
        
        // Add product button
        adminView.addAddProductListener(e -> {
            try {
                String name = adminView.getProductName();
                double price = Double.parseDouble(adminView.getProductPrice());
                int stock = Integer.parseInt(adminView.getProductStock());
                String category = adminView.getProductCategory();
                String description = adminView.getProductDescription();
                
                if (name.isEmpty() || category.isEmpty()) {
                    adminView.showMessage("Please fill all required fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int newId = products.size() + 1;
                Product newProduct = new Product(newId, name, description, price, stock, category);
                products.add(newProduct);
                adminView.updateProductTable(products);
                adminView.clearProductForm();
                adminView.showMessage("Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                adminView.showMessage("Please enter valid price and stock values", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Delete product button
        adminView.addDeleteProductListener(e -> {
            int productId = adminView.getSelectedProductId();
            if (productId > 0) {
                int confirm = JOptionPane.showConfirmDialog(adminView, 
                        "Are you sure you want to delete this product?", 
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    products.removeIf(p -> p.getId() == productId);
                    adminView.updateProductTable(products);
                    adminView.showMessage("Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                adminView.showMessage("Please select a product to delete", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        // Refresh button
        adminView.addRefreshListener(e -> {
            adminView.updateProductTable(products);
        });
        
        // Logout button
        adminView.addLogoutListener(e -> {
            currentUser = null;
            adminView.hideView();
            adminView.dispose();
            showLoginView();
        });
        
        adminView.showView();
    }
    
    /**
     * Shows the client shopping view.
     */
    private static void showClientView() {
        ClientView clientView = new ClientView(currentUser);
        
        // Populate product table
        clientView.updateProductTable(products);
        
        // Add to cart button
        clientView.addAddToCartListener(e -> {
            int productId = clientView.getSelectedProductId();
            int quantity = clientView.getQuantity();
            
            if (productId > 0) {
                Product product = findProductById(productId);
                if (product != null) {
                    if (product.getStock() >= quantity) {
                        currentCart.addItem(product, quantity);
                        clientView.updateCartTable(currentCart.getItems());
                        clientView.showMessage("Added to cart!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        clientView.showMessage("Insufficient stock", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                clientView.showMessage("Please select a product", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        // Remove from cart button
        clientView.addRemoveFromCartListener(e -> {
            int selectedRow = clientView.getSelectedCartRow();
            if (selectedRow >= 0 && selectedRow < currentCart.getItems().size()) {
                CartItem item = currentCart.getItems().get(selectedRow);
                currentCart.removeItem(item.getProduct().getId());
                clientView.updateCartTable(currentCart.getItems());
            } else {
                clientView.showMessage("Please select an item to remove", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        // Checkout button
        clientView.addCheckoutListener(e -> {
            if (currentCart.isEmpty()) {
                clientView.showMessage("Your cart is empty", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Show payment view
            PaymentView paymentView = new PaymentView(clientView, currentCart.getTotal());
            
            paymentView.addProcessPaymentListener(event -> {
                PaymentMethod paymentMethod = paymentView.createPaymentMethod();
                if (paymentMethod != null) {
                    if (paymentMethod.processPayment(currentCart.getTotal())) {
                        // Create order
                        Order order = new Order(1, currentCart);
                        order.markAsPaid(paymentMethod.getPaymentMethodName());
                        
                        // Update stock
                        for (CartItem item : currentCart.getItems()) {
                            item.getProduct().reduceStock(item.getQuantity());
                        }
                        
                        // Show receipt
                        String receipt = order.generateReceipt() + "\n" + paymentMethod.getTransactionDetails();
                        JTextArea receiptArea = new JTextArea(receipt);
                        receiptArea.setEditable(false);
                        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                        JScrollPane scrollPane = new JScrollPane(receiptArea);
                        scrollPane.setPreferredSize(new java.awt.Dimension(400, 400));
                        
                        JOptionPane.showMessageDialog(paymentView, scrollPane, 
                                "Payment Successful - Receipt", JOptionPane.INFORMATION_MESSAGE);
                        
                        // Clear cart
                        currentCart.clear();
                        clientView.updateCartTable(currentCart.getItems());
                        clientView.updateProductTable(products);
                        
                        paymentView.setPaymentProcessed(true);
                        paymentView.closeView();
                    } else {
                        paymentView.showMessage("Payment failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            
            paymentView.setVisible(true);
        });
        
        // Search button
        clientView.addSearchListener(e -> {
            String searchText = clientView.getSearchText().toLowerCase();
            String category = clientView.getSelectedCategory();
            
            List<Product> filteredProducts = new ArrayList<>();
            for (Product product : products) {
                boolean matchesSearch = searchText.isEmpty() || 
                        product.getName().toLowerCase().contains(searchText);
                boolean matchesCategory = category.equals("All Categories") || 
                        product.getCategory().equals(category);
                
                if (matchesSearch && matchesCategory) {
                    filteredProducts.add(product);
                }
            }
            clientView.updateProductTable(filteredProducts);
        });
        
        // Logout button
        clientView.addLogoutListener(e -> {
            currentUser = null;
            currentCart = null;
            clientView.hideView();
            clientView.dispose();
            showLoginView();
        });
        
        clientView.showView();
    }
    
    /**
     * Finds a product by its ID.
     * 
     * @param id Product ID
     * @return Product object or null if not found
     */
    private static Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
