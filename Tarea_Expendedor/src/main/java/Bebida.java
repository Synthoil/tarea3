/**
 * Clase que agrupa las bebidas en un tipo de objeto comun.
 */
abstract class Bebida extends Producto{

    /**
     * Construye la bebida con un numero de serie especifico.
     * @param serie Numero de serie
     */
    public Bebida(int serie){
        this.serie = serie;
    }
}