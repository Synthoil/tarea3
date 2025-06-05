/**
 * Objeto tipo Bebida con sabor "cocacola".
 */
class CocaCola extends Bebida{

    /**
     * Construye la bebida del sabor con un numero de serie especifico.
     * @param serie Numero de serie
     */
    public CocaCola(int serie){
        super(serie);
    }

    /**
     * Retorna el sabor de la bebida
     * @return sabor de la bebida.
     */
    public String consumir(){
        return "cocacola";
    }
}