/**
 * Excepcion lanzada cuando la moneda es tipo "null".
 */

public class PagoIncorrectoException extends Exception {

    /**
     * Crea la excepción con un mensaje.
     *
     * @param mensaje Mensaje que describe el error.
     */
    public PagoIncorrectoException(String mensaje) {
        super(mensaje);
    }
}
