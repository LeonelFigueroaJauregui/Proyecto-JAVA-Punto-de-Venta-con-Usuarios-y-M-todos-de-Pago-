
package Models;

/**
 * Representa un producto en el inventario.
 */
public class Product {
    private static int NEXT_ID = 1;
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.id = NEXT_ID++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // getters y setters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int q) { this.quantity = q; }
    public void setPrice(double p) { this.price = p; }
    public void setName(String n) { this.name = n; }
}
