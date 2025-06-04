/**
 * Objeto tipo Dulce con sabor "super8".
 */

class Super8 extends Dulce{

    /**
     * Construye el dulce con un numero de serie especifico.
     * @param serie Numero de serie.
     */
    public Super8(int serie){
        super(serie);
    }

    /**
     * Retorna el sabor del dulce.
     * @return Sabor del dulce.
     */
    public String consumir(){
        return "super8";
    }
}
