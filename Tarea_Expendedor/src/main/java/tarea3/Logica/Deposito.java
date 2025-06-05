import java.util.ArrayList;

/**
 * Clase de deposito genérico.
 *
 * @param <T> Tipo de elemento a ser almacenado (clase, int, etc).
 */
class Deposito<T> {

    /**
     * Lista que almacena cada uno de los elementos.
     */
    private ArrayList<T> elementos = new ArrayList<>();

    /**
     * Agrega el elemento al depósito.
     *
     * @param elemento El elemento que se quiere agregar a la lista.
     */
    public void addElemento(T elemento) {
        elementos.add(elemento);
    }

    /**
     * Retorna el primer elemento de la lista (retorna null si no existe).
     *
     * @return Un Producto o null.
     */
    public T getElemento() {
        return elementos.isEmpty() ? null : elementos.remove(0);
    }
}