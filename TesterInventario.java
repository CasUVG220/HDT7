
// esta clase es un tester de el inventario con ejemplos ya puestos
public class TesterInventario {
    public static void main(String[] args) {
        // inventario ordenado por nombre
        InventarioBST<String, Producto> inventarioPorNombre = new InventarioBST<>();

        // Producto 1
        Producto prod1 = new Producto("111", "Gorra", "Gorra básica");
        prod1.agregarTalla("única", 20);

        // Producto 2
        Producto prod2 = new Producto("222", "Polo", "Polo casual");
        prod2.agregarTalla("m", 12);
        prod2.agregarTalla("l", 10);

        // Producto 3
        Producto prod3 = new Producto("333", "Botella", "Botella plástica");
        prod3.agregarTalla("500ml", 25);

        // Insertar productos en el BST
        inventarioPorNombre.insertar(prod1.getNombre(), prod1);
        inventarioPorNombre.insertar(prod2.getNombre(), prod2);
        inventarioPorNombre.insertar(prod3.getNombre(), prod3);

        // Buscar el producto
        System.out.println("Buscando 'Polo':");
        Producto encontrado = inventarioPorNombre.buscar("Polo");
        if (encontrado != null) {
            System.out.println(encontrado);
        } else {
            System.out.println("Producto no encontrado.");
        }

        // Recorrer el árbol todo en orden
        System.out.println("\nInventario (ordenado por nombre):");
        inventarioPorNombre.recorridoInOrden();
    }
}
