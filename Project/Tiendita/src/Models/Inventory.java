package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventario en memoria (singleton con métodos estáticos sencillos)
 */
public class Inventory {
    private static final List<Product> productos = new ArrayList<>();

    // productos predeterminados al iniciar la app
    static {
        productos.add(new Product("Sabritas", 18.50, 50));
        productos.add(new Product("Agua 600ml", 12.00, 40));
        productos.add(new Product("Pan Bimbo", 35.00, 20));
        productos.add(new Product("Leche 1L", 28.00, 30));
        productos.add(new Product("Refresco 600ml", 22.00, 25));
    }

    // métodos de manipulación
    public static void addProduct(Product p) { productos.add(p); }
    public static List<Product> getProductos() { return productos; }
    public static Product findById(int id) {
        for (Product p: productos) if (p.getId()==id) return p;
        return null;
    }
    public static Product findByName(String name){
        for(Product p: productos) if(p.getName().equalsIgnoreCase(name)) return p;
        return null;
    }
    public static void clear(){ productos.clear(); }
}
