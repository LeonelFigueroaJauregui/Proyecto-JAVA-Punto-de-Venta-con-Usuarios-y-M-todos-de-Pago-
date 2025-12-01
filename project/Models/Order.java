package Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order in the Point of Sale system.
 * Contains order information including items, user, and payment details.
 */
public class Order {
    
    /**
     * Enumeration of order status.
     */
    public enum Status {
        PENDING,    // Order created but not paid
        PAID,       // Order has been paid
        CANCELLED,  // Order was cancelled
        COMPLETED   // Order completed and delivered
    }

    private int id;
    private User user;
    private List<CartItem> items;
    private double total;
    private String paymentMethod;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Creates a new Order from a cart.
     * 
     * @param id   Unique identifier for the order
     * @param cart The cart containing items to order
     */
    public Order(int id, Cart cart) {
        this.id = id;
        this.user = cart.getUser();
        this.items = new ArrayList<>(cart.getItems());
        this.total = cart.getTotal();
        this.status = Status.PENDING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        this.updatedAt = LocalDateTime.now();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Marks the order as paid with the specified payment method.
     * 
     * @param paymentMethod The payment method used
     */
    public void markAsPaid(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        this.status = Status.PAID;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Cancels the order.
     */
    public void cancel() {
        this.status = Status.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Completes the order.
     */
    public void complete() {
        this.status = Status.COMPLETED;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Gets the number of items in the order.
     * 
     * @return Number of items
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Generates a receipt for the order.
     * 
     * @return Formatted receipt string
     */
    public String generateReceipt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        StringBuilder receipt = new StringBuilder();
        receipt.append("========================================\n");
        receipt.append("           POINT OF SALE RECEIPT        \n");
        receipt.append("========================================\n");
        receipt.append("Order #: ").append(id).append("\n");
        receipt.append("Date: ").append(createdAt.format(formatter)).append("\n");
        receipt.append("Customer: ").append(user.getFullName()).append("\n");
        receipt.append("----------------------------------------\n");
        receipt.append("Items:\n");
        for (CartItem item : items) {
            receipt.append(String.format("  %-20s x%d  $%.2f\n", 
                    item.getProduct().getName(), 
                    item.getQuantity(), 
                    item.getSubtotal()));
        }
        receipt.append("----------------------------------------\n");
        receipt.append(String.format("TOTAL: $%.2f\n", total));
        receipt.append("Payment Method: ").append(paymentMethod != null ? paymentMethod : "N/A").append("\n");
        receipt.append("Status: ").append(status).append("\n");
        receipt.append("========================================\n");
        return receipt.toString();
    }

    @Override
    public String toString() {
        return String.format("Order{id=%d, user='%s', total=%.2f, status=%s}", 
                id, user.getUsername(), total, status);
    }
}
