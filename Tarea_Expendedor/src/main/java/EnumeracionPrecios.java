/**
 * Clase que guarda los precios de cada producto.
 * Los productos son Cocacola, Sprite, Fanta, Snickers y Super8 con precios constantes.
 */

public enum EnumeracionPrecios {

    /**
     * Precio de CocaCola.
     */
    COCA(100),
    /**
     * Precio de Sprite.
     */
    SPRITE(200),
    /**
     * Precio de Fanta.
     */
    FANTA(300),
    /**
     * Precio de Snickers.
     */
    SNICKERS(400),
    /**
     * Precio de Super8.
     */
    SUPER8(500);

    /**
     * Precio del producto.
     */
    private int precio;

    /**
     * Incializa el precio
     *
     * @param precio Precio del producto
     */
    EnumeracionPrecios(int precio) {
        this.precio = precio;
    }

    /**
     * Retorna el precio del producto
     *
     * @return Precio del producto.
     */
    public int getPrecio() {
        return precio;
    }
}
