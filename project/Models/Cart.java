package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart in the Point of Sale system.
 * Contains a list of cart items and provides methods for cart management.
 */
public class Cart {
    private List<CartItem> items;
    private User user;

    /**
     * Creates an empty cart for the specified user.
     * 
     * @param user The user who owns this cart
     */
    public Cart(User user) {
        this.user = user;
        this.items = new ArrayList<>();
    }

    /**
     * Adds a product to the cart with the specified quantity.
     * If the product already exists in the cart, increases the quantity.
     * 
     * @param product  The product to add
     * @param quantity The quantity to add
     * @return true if successful
     */
    public boolean addItem(Product product, int quantity) {
        // Check if product already exists in cart
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.addQuantity(quantity);
                return true;
            }
        }
        // Add new item if not found
        items.add(new CartItem(product, quantity));
        return true;
    }

    /**
     * Removes an item from the cart by product ID.
     * 
     * @param productId The ID of the product to remove
     * @return true if successful, false if product not found
     */
    public boolean removeItem(int productId) {
        return items.removeIf(item -> item.getProduct().getId() == productId);
    }

    /**
     * Updates the quantity of a product in the cart.
     * 
     * @param productId   The ID of the product to update
     * @param newQuantity The new quantity
     * @return true if successful, false if product not found
     */
    public boolean updateQuantity(int productId, int newQuantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == productId) {
                if (newQuantity <= 0) {
                    return removeItem(productId);
                }
                item.setQuantity(newQuantity);
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the total price of all items in the cart.
     * 
     * @return Total price
     */
    public double getTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    /**
     * Gets the number of items in the cart.
     * 
     * @return Number of items
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Gets the total quantity of all products in the cart.
     * 
     * @return Total quantity
     */
    public int getTotalQuantity() {
        int total = 0;
        for (CartItem item : items) {
            total += item.getQuantity();
        }
        return total;
    }

    /**
     * Clears all items from the cart.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Checks if the cart is empty.
     * 
     * @return true if cart is empty
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Getters
    public List<CartItem> getItems() {
        return items;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cart for: ").append(user.getUsername()).append("\n");
        sb.append("Items:\n");
        for (CartItem item : items) {
            sb.append("  - ").append(item.toString()).append("\n");
        }
        sb.append("Total: $").append(String.format("%.2f", getTotal()));
        return sb.toString();
    }
}
