package Models;

/**
 * Represents a product in the Point of Sale system.
 * Contains product information such as ID, name, price, and stock quantity.
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;

    /**
     * Creates a new Product with the specified details.
     * 
     * @param id          Unique identifier for the product
     * @param name        Name of the product
     * @param description Description of the product
     * @param price       Price of the product
     * @param stock       Available stock quantity
     * @param category    Category of the product
     */
    public Product(int id, String name, String description, double price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Reduces stock by the specified quantity.
     * 
     * @param quantity Amount to reduce from stock
     * @return true if successful, false if insufficient stock
     */
    public boolean reduceStock(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
            return true;
        }
        return false;
    }

    /**
     * Increases stock by the specified quantity.
     * 
     * @param quantity Amount to add to stock
     */
    public void addStock(int quantity) {
        stock += quantity;
    }

    @Override
    public String toString() {
        return String.format("Product{id=%d, name='%s', price=%.2f, stock=%d}", id, name, price, stock);
    }
}
