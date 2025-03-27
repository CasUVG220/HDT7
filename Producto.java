import java.util.Map;
import java.util.HashMap;

public class Producto {
    private String sku;
    private String nombre;
    private String descripcion;
    private Map<String, Integer> tallas;

    // Aqui es donde se construyen los productos
    public Producto(String sku, String nombre, String descripcion) {
        this.sku = sku;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tallas = new HashMap<>();
    }

    public String getSku() {
        return sku;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String nuevaDescripcion) {
        this.descripcion = nuevaDescripcion;
    }

    // Agrega las tallas junto a sus cantidades
    public void agregarTalla(String talla, int cantidad) {
        tallas.put(talla.toLowerCase(), cantidad);
    }

    // Modificas las tallas que ya hay
    public void editarCantidad(String talla, int nuevaCantidad) {
        if (tallas.containsKey(talla.toLowerCase())) {
            tallas.put(talla.toLowerCase(), nuevaCantidad);
        }
    }

    public Map<String, Integer> getTallas() {
        return tallas;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("SKU: ").append(sku).append("\n");
        info.append("Nombre: ").append(nombre).append("\n");
        info.append("Descripción: ").append(descripcion).append("\n");
        info.append("Tallas:\n");
        for (String talla : tallas.keySet()) {
            info.append("  ").append(talla.toUpperCase()).append(": ").append(tallas.get(talla)).append("\n");
        }
        return info.toString();
    }
}
