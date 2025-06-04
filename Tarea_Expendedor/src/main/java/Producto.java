/**
 * Clase abstracta ocupada para agrupar dulces y bebidas bajo un mismo tipo de objeto (Es superclase).
 */

abstract class Producto {

    /**
     * Numero asociado al producto.
     */
    public int serie;

    /**
     * Devuelve el numero de serie.
     *
     * @return El numero de serie.
     */
    public int getSerie(){
        return serie;
    }

    /**
     * Metodo abstracto que define el consumo del producto.
     */
    public abstract String consumir();
}
