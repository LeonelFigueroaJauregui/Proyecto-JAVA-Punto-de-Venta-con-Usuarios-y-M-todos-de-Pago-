package Models;

/**
 * Represents an item in the shopping cart.
 * Contains a product and the quantity selected by the user.
 */
public class CartItem {
    private Product product;
    private int quantity;

    /**
     * Creates a new CartItem with the specified product and quantity.
     * 
     * @param product  The product to add to cart
     * @param quantity The quantity of the product
     */
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calculates the subtotal for this cart item.
     * 
     * @return Subtotal (price * quantity)
     */
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    /**
     * Increases the quantity by the specified amount.
     * 
     * @param amount Amount to add
     */
    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    /**
     * Decreases the quantity by the specified amount.
     * 
     * @param amount Amount to subtract
     * @return true if successful, false if would result in negative quantity
     */
    public boolean removeQuantity(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("CartItem{product='%s', quantity=%d, subtotal=%.2f}", 
                product.getName(), quantity, getSubtotal());
    }
}
