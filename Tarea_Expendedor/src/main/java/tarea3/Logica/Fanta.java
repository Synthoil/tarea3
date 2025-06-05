/**
 * Objeto tipo Bebida con sabor "fanta".
 */

class Fanta extends Bebida{

    /**
     * Construye la bebida con un numero de serie especifico.
     * @param serie Numero de serie.
     */
    public Fanta(int serie){
        super(serie);
    }

    /**
     * Retorna el sabor de la bebida.
     * @return Sabor de la bebida.
     */
    public String consumir(){
        return "fanta";
    }
}
