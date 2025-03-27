import java.util.Scanner;

public class InventarioApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Árboles por SKU y por Nombre
        InventarioBST<String, Producto> arbolPorSKU = new InventarioBST<>();
        InventarioBST<String, Producto> arbolPorNombre = new InventarioBST<>();

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n Menu de la TIENDA");
            System.out.println("1. Agregar nuevo producto");
            System.out.println("2. Buscar producto por SKU");
            System.out.println("3. Buscar producto por Nombre");
            System.out.println("4. Mostrar inventario ordenado por Nombre");
            System.out.println("5. Mostrar inventario ordenado por SKU");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("SKU: ");
                    String sku = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();

                    Producto nuevo = new Producto(sku, nombre, descripcion);

                    System.out.println("Agrega tallas 'talla:numero' si termino escriba 'fin':");
                    while (true) {
                        System.out.print("Talla: ");
                        String linea = sc.nextLine();
                        if (linea.equalsIgnoreCase("fin")) break;
                        String[] partes = linea.split(":");
                        if (partes.length == 2) {
                            try {
                                nuevo.agregarTalla(partes[0], Integer.parseInt(partes[1]));
                            } catch (NumberFormatException e) {
                                System.out.println("Cantidad inválida.");
                            }
                        } else {
                            System.out.println("Formato incorrecto.");
                        }
                    }

                    arbolPorSKU.insertar(sku, nuevo);
                    arbolPorNombre.insertar(nombre, nuevo);
                    System.out.println("Producto agregado.");
                    break;

                case "2":
                    System.out.print("SKU a buscar: ");
                    String buscarSKU = sc.nextLine();
                    Producto encontradoSKU = arbolPorSKU.buscar(buscarSKU);
                    if (encontradoSKU != null) {
                        System.out.println(encontradoSKU);
                    } else {
                        System.out.println("No eziste");
                    }
                    break;

                case "3":
                    System.out.print("Nombre a buscar: ");
                    String buscarNombre = sc.nextLine();
                    Producto encontradoNombre = arbolPorNombre.buscar(buscarNombre);
                    if (encontradoNombre != null) {
                        System.out.println(encontradoNombre);
                    } else {
                        System.out.println("No existe");
                    }
                    break;

                case "4":
                    System.out.println("Inventario por NOMBRE:");
                    arbolPorNombre.recorridoInOrden();
                    break;

                case "5":
                    System.out.println("Inventario por SKU:");
                    arbolPorSKU.recorridoInOrden();
                    break;

                case "6":
                    continuar = false;
                    System.out.println("Orale");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }

        sc.close();
    }
}
