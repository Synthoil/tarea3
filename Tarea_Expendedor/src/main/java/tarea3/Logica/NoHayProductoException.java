/**
 * Excepcion lanzada cuando el producto solicitado no esta disponible o no existe.
 */
public class NoHayProductoException extends Exception {

    /**
     * Crea la excepción con un mensaje.
     *
     * @param mensaje Mensaje que describe el error.
     */
    public NoHayProductoException(String mensaje) {
        super(mensaje);
    }
}
