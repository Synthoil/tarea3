/**
 * Objeto tipo Dulce con sabor "snickers".
 */

class Snickers extends Dulce{

    /**
     * Construye el dulce con un numero de serie especifico.
     * @param serie Numero de serie.
     */
    public Snickers(int serie){
        super(serie);
    }

    /**
     * Retorna el sabor del dulce.
     * @return Sabor del dulce.
     */
    public String consumir(){
        return "snickers";
    }
}
