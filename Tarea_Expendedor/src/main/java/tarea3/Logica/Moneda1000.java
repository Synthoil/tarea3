/**
 * Clase tipo moneda con un valor de 1000
 */

class Moneda1000 extends Moneda{

    /**
     * Obtiene las propiedades de moneda.
     */
    public Moneda1000(){
        super();
    }

    /**
     * Define el metodo de moneda para retornar un valor.
     * @return Valor de la moneda.
     */
    @Override
    public int getValor(){
        return 1000;
    }
}