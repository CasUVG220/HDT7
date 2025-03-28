// InventarioBSTTest.java

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InventarioBSTTest {

    private InventarioBST<String, Producto> bstNombre;
    private InventarioBST<String, Producto> bstSKU;

    @BeforeEach
    public void setUp() {
        bstNombre = new InventarioBST<>();
        bstSKU = new InventarioBST<>();

        Producto p1 = new Producto("123", "Camiseta", "Prenda deportiva");
        p1.agregarTalla("M", 10);
        Producto p2 = new Producto("456", "Pantalón", "Ropa cómoda");
        p2.agregarTalla("L", 5);

        bstNombre.insertar(p1.getNombre(), p1);
        bstNombre.insertar(p2.getNombre(), p2);

        bstSKU.insertar(p1.getSku(), p1);
        bstSKU.insertar(p2.getSku(), p2);
    }

    @Test
    public void testBuscarProductoPorNombre() {
        Producto p = bstNombre.buscar("Camiseta");
        assertNotNull(p);
        assertEquals("Camiseta", p.getNombre());
    }

    @Test
    public void testBuscarProductoPorSKU() {
        Producto p = bstSKU.buscar("456");
        assertNotNull(p);
        assertEquals("456", p.getSku());
    }
}
