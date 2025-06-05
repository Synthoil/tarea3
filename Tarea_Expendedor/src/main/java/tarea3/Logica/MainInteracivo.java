import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase main usada para simular la situacion de comprar y comer productos
 * mediante un expendedor, un selector de producto y moneda.
 * Se controlan las excepciones como errores por falta de dinero, moneda nula y falta de stock.
 */
public class MainInteracivo {

    /**
     * Metodo principal del programa. Crea un expendedor y permite interactuar con el.
     * @param args Argumentos (Sin uso).
     */
    public static void main(String[] args){
        Expendedor exp = new Expendedor(0); // 5 productos de cada tipo
        Scanner scanner = new Scanner(System.in);
        int opcion=-1;

        while (opcion !=0) {
            System.out.println("-------------Bienvenido a nuestro expendedor de productos-------------");
            System.out.println("Las opciones del producto son : \n1.Bebida CocaCola");
            System.out.println("2.Bebida Fanta");
            System.out.println("3.Bebida Sprite");
            System.out.println("4.Dulce Super8");
            System.out.println("5.Dulce Snickers");

            System.out.println("Digite su opcion de compra o el numero '0' si desea salir");

            opcion = scanner.nextInt();//seleccion producto para comprar

            if (opcion == 0) {//Salir del programa
                System.out.println("Presionó la opcion de salir,gracias por su compra.");
                break;
            }

            System.out.println("Introduzca el valor de su moneda (Monedas aceptadas : 100,500,1000):");//introducir el valor de la moneda
            int valorMoneda = scanner.nextInt();

            Moneda moneda =null;
            switch (valorMoneda){//selecciona el valor de la moneda de compra
                case 100:
                    moneda = new Moneda100();
                    break;
                case 500:
                    moneda = new Moneda500();
                    break;
                case 1000:
                    moneda = new Moneda1000();
                    break;
                default: break;
            }
            try{
                Comprador c = new Comprador(moneda,opcion,exp);//Compra el producto con el valor y en el deposito correspondiente
                System.out.println("Producto que consumió : "+ c.queConsumiste());//retorna el producto comprado
                System.out.println("Su vuelto es de : "+c.cuantoVuelto());//retorna el vuelto despues de la compra
            }
            catch (PagoIncorrectoException e){//Exception en caso de que la moneda no sea valida
                System.out.println("Lo sentimos "+ e.getMessage());
            }
            catch (PagoInsuficienteException e){//Exception en caso que el valor de la moneda sea inferior al precio del producto
                System.out.println("Lo sentimos " + e.getMessage());
                System.out.println("Aqui tiene su moneda: " + moneda.getValor());
            }
            catch (NoHayProductoException e){//Exception en caso que no existan productos en el deposito
                System.out.println("Lo sentimos "+ e.getMessage());
                System.out.println("Aqui tiene su moneda: " + moneda.getValor());
            }
            catch (Exception e){
                System.out.println("Error : "+ e.getMessage());
                System.out.println("Aqui tiene su moneda: " + moneda.getValor());
            }

            System.out.println("\n¿Desea comprar otro producto? Ingrese '0' para salir o cualquier otro número para continuar:");
            opcion = scanner.nextInt();
        }
        scanner.close();
    }
}
