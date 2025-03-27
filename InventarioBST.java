public class InventarioBST<K extends Comparable<K>, V> {

    private class Nodo {
        K clave;
        V valor;
        Nodo izquierdo;
        Nodo derecho;

        Nodo(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    private Nodo raiz;

    public void insertar(K clave, V valor) {
        raiz = insertarRec(raiz, clave, valor);
    }

    private Nodo insertarRec(Nodo actual, K clave, V valor) {
        if (actual == null) {
            return new Nodo(clave, valor);
        }

        int comparacion = clave.compareTo(actual.clave);
        if (comparacion < 0) {
            actual.izquierdo = insertarRec(actual.izquierdo, clave, valor);
        } else if (comparacion > 0) {
            actual.derecho = insertarRec(actual.derecho, clave, valor);
        } else {
            // Si ya existe la clave, actualizamos el valor
            actual.valor = valor;
        }

        return actual;
    }

    public V buscar(K clave) {
        return buscarRec(raiz, clave);
    }

    private V buscarRec(Nodo actual, K clave) {
        if (actual == null) {
            return null;
        }

        int comparacion = clave.compareTo(actual.clave);
        if (comparacion == 0) {
            return actual.valor;
        } else if (comparacion < 0) {
            return buscarRec(actual.izquierdo, clave);
        } else {
            return buscarRec(actual.derecho, clave);
        }
    }

    public void recorridoInOrden() {
        recorridoInOrdenRec(raiz);
    }

    private void recorridoInOrdenRec(Nodo actual) {
        if (actual != null) {
            recorridoInOrdenRec(actual.izquierdo);
            System.out.println(actual.valor.toString());
            recorridoInOrdenRec(actual.derecho);
        }
    }
}
