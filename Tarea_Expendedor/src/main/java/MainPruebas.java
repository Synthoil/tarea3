import java.util.ArrayList;

/**
 * Clase main usada para el testeo de cada situacion posible.
 * mediante un expendedor y distintos tipos de moneda.
 * Se simulan distintas situaciones como compras exitosas, errores por falta de dinero, moneda nula y falta de stock.
 */

public class MainPruebas {

    /**
     * Metodo principal del programa. Crea un expendedor y realiza las pruebas.
     * @param args Argumentos (Sin uso).
     */
    public static void main(String[] args) {
        Expendedor exp = new Expendedor(1); // solo un producto de cada tipo

        // Monedas
        Moneda m1000 = new Moneda1000();
        Moneda m500 = new Moneda500();
        Moneda m100 = new Moneda100();

        // compra exitosa
        try {
            Comprador c1 = new Comprador(m1000, Expendedor.COCA, exp);
            System.out.println("comprador 1 consumió: " + c1.queConsumiste());
            System.out.println("Vuelto de comprador 1 :" + c1.cuantoVuelto());
        }
        catch (Exception e) {
            System.out.println("Error en comprador 1: " + e.getMessage());
            System.out.println("Aqui tiene su moneda: " + m1000.getValor());
        }

        // Compra sin suficiente dinero
        try {
            Comprador c2 = new Comprador(m100, Expendedor.SUPER8, exp);
            System.out.println("comprador 2 consumió: " + c2.queConsumiste());
            System.out.println("Vuelto de comprador 2:" + c2.cuantoVuelto());
        } catch (PagoInsuficienteException e) {
            System.out.println("Pago insuficiente: " + e.getMessage());
            System.out.println("Aqui tiene su moneda: " + m100.getValor());
        } catch (Exception e) {
            System.out.println("Error en comprador 2: " + e.getMessage());
            System.out.println("Aqui tiene su moneda: " + m100.getValor());
        }

        // Compra con moneda null
        try {
            Comprador c3 = new Comprador(null, Expendedor.FANTA, exp);
        } catch (PagoIncorrectoException e) {
            System.out.println("Pago incorrecto de comprador 3: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error en comprador 3: " + e.getMessage());
        }

        // Compra cuando no hay stock (ya se compró antes)
        try {
            Comprador c4 = new Comprador(m500, Expendedor.COCA, exp);
        } catch (NoHayProductoException e) {
            System.out.println("No hay producto en la compra 4: " + e.getMessage());
            System.out.println("Aqui tiene su moneda: " + m500.getValor());
        } catch (Exception e) {
            System.out.println("Error en comprador 4: " + e.getMessage());
            System.out.println("Aqui tiene su moneda: " + m500.getValor());
        }

        ArrayList<Moneda> monedas = new ArrayList<>();
        monedas.add(new Moneda1000());
        monedas.add(new Moneda500());
        monedas.add(new Moneda100());

        monedas.sort(null);
        for(Moneda m :monedas){
            System.out.println("$" + m.getValor());
        }
    }
}