
/**
 * Clase tipo moneda con un valor de 500.
 */

public class Moneda500 extends Moneda{

    /**
     * Obtiene las propiedades de moneda.
     */
    public Moneda500(){
        super();
    }

    /**
     * Define el metodo de moneda para retornar un valor.
     * @return Valor de la moneda.
     */
    @Override
    public int getValor(){
        return 500;
    }
}