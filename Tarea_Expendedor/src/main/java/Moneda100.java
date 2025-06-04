/**
 * Clase tipo moneda con un valor de 100.
 */

class Moneda100 extends Moneda{

    /**
     * Obtiene las propiedades de moneda.
     */
    public Moneda100(){
        super();
    }

    /**
     * Define el metodo de moneda para retornar un valor.
     * @return Valor de la moneda.
     */
    @Override
    public int getValor(){
        return 100;
    }
}