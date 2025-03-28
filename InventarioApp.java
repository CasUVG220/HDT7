// InventarioApp.java

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class InventarioApp {
    public static void main(String[] args) {
        InventarioBST<String, Producto> inventarioPorNombre = new InventarioBST<>();
        InventarioBST<String, Producto> inventarioPorSKU = new InventarioBST<>();

        // Cargar automáticamente todos los archivos .csv del directorio
        File actualDir = new File(".");
        File[] archivos = actualDir.listFiles((dir, name) -> name.endsWith(".csv"));
        if (archivos != null) {
            for (File archivo : archivos) {
                cargarDesdeCSV(archivo.getName(), inventarioPorNombre, inventarioPorSKU);
            }
        }

        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("Menú:");
            System.out.println("1. Agregar producto");
            System.out.println("2. Buscar por nombre");
            System.out.println("3. Buscar por SKU");
            System.out.println("4. Mostrar inventario ordenado por nombre");
            System.out.println("5. Mostrar inventario ordenado por SKU");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("SKU: ");
                    String sku = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();
                    Producto nuevo = new Producto(sku, nombre, descripcion);
                    System.out.println("Ingresa tallas en formato talla:cantidad (escribe 'fin' para terminar):");
                    while (true) {
                        String entrada = sc.nextLine();
                        if (entrada.equalsIgnoreCase("fin")) break;
                        if (entrada.contains(":")) {
                            String[] partes = entrada.split(":");
                            if (partes.length == 2) {
                                String talla = partes[0].trim();
                                int cantidad = Integer.parseInt(partes[1].trim());
                                nuevo.agregarTalla(talla, cantidad);
                            }
                        }
                    }
                    inventarioPorNombre.insertar(nuevo.getNombre(), nuevo);
                    inventarioPorSKU.insertar(nuevo.getSku(), nuevo);
                    System.out.println("Producto agregado correctamente.\n");
                    break;
                case 2:
                    System.out.print("Nombre a buscar: ");
                    String nomBuscar = sc.nextLine();
                    Producto encontradoNombre = inventarioPorNombre.buscar(nomBuscar);
                    System.out.println(encontradoNombre != null ? encontradoNombre : "No encontrado.");
                    break;
                case 3:
                    System.out.print("SKU a buscar: ");
                    String skuBuscar = sc.nextLine();
                    Producto encontradoSKU = inventarioPorSKU.buscar(skuBuscar);
                    System.out.println(encontradoSKU != null ? encontradoSKU : "No encontrado.");
                    break;
                case 4:
                    System.out.println("Inventario ordenado por nombre:");
                    inventarioPorNombre.recorridoInOrden();
                    break;
                case 5:
                    System.out.println("Inventario ordenado por SKU:");
                    inventarioPorSKU.recorridoInOrden();
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }

        sc.close();
    }

    // ✅ CORREGIDO: ahora sí guarda las tallas desde el CSV
    public static void cargarDesdeCSV(String archivo, InventarioBST<String, Producto> porNombre, InventarioBST<String, Producto> porSKU) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean primera = true;
    
            while ((linea = br.readLine()) != null) {
                if (primera) { 
                    primera = false; 
                    continue; 
                }
    
                String[] partes = linea.split(",", 4); // solo separar en 4 partes para evitar romper las tallas
                if (partes.length >= 4) {
                    String sku = partes[0].trim();
                    String nombre = partes[1].trim();
                    String descripcion = partes[2].trim();
                    String datosTallas = partes[3].trim();
    
                    Producto prod = new Producto(sku, nombre, descripcion);
    
                    // Separar tallas del campo por el caracter "|"
                    String[] tallas = datosTallas.split("\\|");
                    for (String par : tallas) {
                        String[] tallaCantidad = par.split(":");
                        if (tallaCantidad.length == 2) {
                            String talla = tallaCantidad[0].trim();
                            int cantidad = Integer.parseInt(tallaCantidad[1].trim());
                            prod.agregarTalla(talla, cantidad);
                        }
                    }
    
                    porNombre.insertar(nombre, prod);
                    porSKU.insertar(sku, prod);
                }
            }
    
            System.out.println("Productos cargados correctamente desde " + archivo + "\n");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + archivo + ": " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de cantidad en tallas: " + e.getMessage());
        }
    }    
}