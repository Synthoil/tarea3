/**
 * Superclase que agrupa los dulces en una unica clase comun.
 */
abstract class Dulce extends Producto{
    /**
     * Construye el dulce con un numero de serie especifico.
     * @param serie Numero de serie
     */
    public Dulce(int serie){
        this.serie = serie;
    }
}
