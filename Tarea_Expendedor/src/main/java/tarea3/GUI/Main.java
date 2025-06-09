
import javax.swing.*;
import java.awt.*;
import java.util.Random;
/**
 * Panel personalizado que permite mostrar una imagen de fondo redimensionada al tamaño del panel.
 * Se utiliza para representar gráficamente elementos como la máquina expendedora.
 */
class ImagePanel extends JPanel {
    private Image image;

    /**
     * Crea un nuevo panel con una imagen de fondo obtenida desde un ImageIcon.
     *
     * @param icon Icono que contiene la imagen a mostrar como fondo.
     */
    public ImagePanel(ImageIcon icon) {
        this.image = icon.getImage();
        setLayout(null);
    }
    /**
     * Sobrescribe el metodo paintComponent para dibujar la imagen de fondo
     * escalada al tamaño actual del panel.
     *
     * @param g Objeto Graphics usado para dibujar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
/**
 * Clase que representa la interfaz gráfica de una máquina expendedora.
 * Permite insertar monedas, seleccionar productos, tomar el producto y recibir el vuelto.
 * Se encarga de mostrar imágenes, actualizar el estado del usuario, stock y saldo.
 */
class GUI {
    // Atributos de interfaz y lógica de negocio
    private int sueldo = 2000;
    private Moneda moneda = null;
    private Comprador comprador = null;
    private Expendedor expendedor = new Expendedor(100);
    private int vuelto = 0;

    // Componentes gráficos
    private JFrame frame;
    private JLabel persona;
    private JLabel SueldoActual;
    private Producto productoEnMano = null;
    private JLabel lblProductoEnMano;
    private JLabel lblSerie;

    // Íconos gráficos
    private Icon iconCoca, iconSprite, iconFanta, iconSuper8, iconSnickers;
    private Icon icon100, icon500, icon1000, iconExp, iconPersona1, iconPersona100, iconPersona500, iconPersona1000;

    // Íconos gráficos
    private Runnable postInsertarMonedaListener;
    private Runnable postCancelarCompraListener;
    private Runnable postConsumirListener;
    private Runnable postActualizarProductoEnManoListener;
    private JLabel stockCoca, stockSprite, stockFanta, stockSuper8, stockSnickers;

    /**
     * Constructor que inicializa la ventana principal y todos los componentes de la GUI.
     */
    public GUI() {
        frame = new JFrame("Maquina Expendedora");
        cargarImagenes();
        inicializarGUI();
    }
    /**
     * Carga todas las imágenes necesarias como íconos redimensionados.
     */
    private void cargarImagenes() {
        iconCoca = cargarIcono("/Imagenes/Productos/cocacola.png", 70, 120);
        iconSprite = cargarIcono("/Imagenes/Productos/sprite.png", 60, 120);
        iconFanta = cargarIcono("/Imagenes/Productos/fanta.png", 80, 120);
        iconSuper8 = cargarIcono("/Imagenes/Productos/super8.png", 120, 120);
        iconSnickers = cargarIcono("/Imagenes/Productos/snickers.png", 86, 120);

        icon100 = new ImageIcon(getClass().getResource("/Imagenes/Monedas/100.png"));
        icon500 = new ImageIcon(getClass().getResource("/Imagenes/Monedas/500.png"));
        icon1000 = new ImageIcon(getClass().getResource("/Imagenes/Monedas/1000.png"));
        iconExp = new ImageIcon(getClass().getResource("/Imagenes/Productos/Exp.png"));
        iconPersona1 = new ImageIcon(getClass().getResource("/Imagenes/Productos/persona1.png"));
        iconPersona100 = new ImageIcon(getClass().getResource("/Imagenes/Productos/persona100.png"));
        iconPersona500 = new ImageIcon(getClass().getResource("/Imagenes/Productos/persona500.png"));
        iconPersona1000 = new ImageIcon(getClass().getResource("/Imagenes/Productos/persona1000.png"));
    }

    private ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        try {
            Image img = new ImageIcon(getClass().getResource(ruta)).getImage()
                    .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            System.err.println("Error al cargar imagen: " + ruta);
            return new ImageIcon();
        }
    }
    /**
     * Inicializa los componentes de la interfaz gráfica.
     */
    private void inicializarGUI() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(null);
        frame.add(panel, BorderLayout.CENTER);

        persona = new JLabel(iconPersona1);
        persona.setBounds(180, 263, 150, 400);
        panel.add(persona);

        JPanel panelMoneda = new JPanel(new GridLayout(2, 2));
        panelMoneda.setBounds(10, 60, 200, 200);
        panel.add(panelMoneda);

        JButton boton100 = new JButton(icon100);
        JButton boton500 = new JButton(icon500);
        JButton boton1000 = new JButton(icon1000);
        JButton boton0 = new JButton("Cancelar");

        panelMoneda.add(boton100);
        panelMoneda.add(boton500);
        panelMoneda.add(boton1000);
        panelMoneda.add(boton0);

        JButton botonNum = new JButton("Productos");
        JButton botonVue = new JButton("Recibir Vuelto");

        ImagePanel Exp = new ImagePanel((ImageIcon) iconExp);
        Exp.setBounds(350, 90, 300, 573);
        Exp.setLayout(null);
        panel.add(Exp);
        botonNum.setBounds(235, 200, 65, 90);
        botonVue.setBounds(230, 300, 70, 20);
        Exp.add(botonNum);
        Exp.add(botonVue);

        botonNum.addActionListener(e -> mostrarDialogoProductos());
        boton100.addActionListener(e -> insertarMoneda(100, iconPersona100));
        boton500.addActionListener(e -> insertarMoneda(500, iconPersona500));
        boton1000.addActionListener(e -> insertarMoneda(1000, iconPersona1000));
        boton0.addActionListener(e -> cancelarCompra());
        botonVue.addActionListener(e -> recibirVuelto());

        SueldoActual = new JLabel("Saldo: " + sueldo);
        SueldoActual.setFont(new Font("Arial", Font.PLAIN, 20));
        SueldoActual.setBounds(10, 0, 1000, 50);
        panel.add(SueldoActual);


        JPanel panelMano = new JPanel();
        panelMano.setBounds(20, 450, 150, 100);
        panelMano.setLayout(new BorderLayout());
        panelMano.setBackground(new Color(240,240,240));

        panelMano.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        lblProductoEnMano = new JLabel("", SwingConstants.CENTER);
        lblSerie = new JLabel("Serie: -", SwingConstants.CENTER);

        panelMano.add(lblProductoEnMano, BorderLayout.CENTER);
        panelMano.add(lblSerie, BorderLayout.SOUTH);
        panel.add(panelMano);

        JButton botonTomar = new JButton("Tomar");
        botonTomar.setBounds(20, 550, 80, 30);
        botonTomar.addActionListener(e -> tomarProducto());
        panel.add(botonTomar);


        JLabel imgCoca = new JLabel((ImageIcon)iconCoca);
        imgCoca.setBounds(380,120,90,110);

        JLabel imgSprite = new JLabel((ImageIcon)iconSprite);
        imgSprite.setBounds(490,120,90,110);

        JLabel imgFanta = new JLabel((ImageIcon)iconFanta);
        imgFanta.setBounds(380,270,90,110);

        JLabel imgSuper8 = new JLabel((ImageIcon)iconSuper8);
        imgSuper8.setBounds(490,270,80,110);

        JLabel imgSnickers = new JLabel((ImageIcon)iconSnickers);
        imgSnickers.setBounds(380,400,90,110);

        stockCoca    = new JLabel("", SwingConstants.CENTER);
        stockSprite  = new JLabel("", SwingConstants.CENTER);
        stockFanta   = new JLabel("", SwingConstants.CENTER);
        stockSuper8  = new JLabel("", SwingConstants.CENTER);
        stockSnickers= new JLabel("", SwingConstants.CENTER);

        stockCoca   .setOpaque(true);
        stockCoca   .setBackground(new Color(0, 0, 0, 150));  // negro semitransparente
        stockCoca   .setForeground(Color.WHITE);

        stockSprite .setOpaque(true);
        stockSprite .setBackground(new Color(0, 0, 0, 150));
        stockSprite .setForeground(Color.WHITE);

        stockFanta  .setOpaque(true);
        stockFanta  .setBackground(new Color(0, 0, 0, 150));
        stockFanta  .setForeground(Color.WHITE);

        stockSuper8 .setOpaque(true);
        stockSuper8 .setBackground(new Color(0, 0, 0, 150));
        stockSuper8 .setForeground(Color.WHITE);

        stockSnickers.setOpaque(true);
        stockSnickers.setBackground(new Color(0, 0, 0, 150));
        stockSnickers.setForeground(Color.WHITE);

        stockCoca   .setBounds(380, 120 + 110, 90, 20);   // justo debajo de imgCoca
        stockSprite .setBounds(490, 120 + 110, 90, 20);
        stockFanta  .setBounds(380, 270 + 110, 90, 20);
        stockSuper8 .setBounds(490, 270 + 110, 80, 20);
        stockSnickers.setBounds(380, 400 + 110, 90, 20);


        JLayeredPane lp = new JLayeredPane();
        lp.setLayout(null);
        lp.setPreferredSize(new Dimension(700,700));

        lp.add(Exp,Integer.valueOf(0));
        lp.add(imgCoca,Integer.valueOf(1));
        lp.add(imgSprite,Integer.valueOf(1));
        lp.add(imgFanta,Integer.valueOf(1));
        lp.add(imgSuper8,Integer.valueOf(1));
        lp.add(imgSnickers,Integer.valueOf(1));

        lp.add(stockCoca,     Integer.valueOf(1));
        lp.add(stockSprite,   Integer.valueOf(1));
        lp.add(stockFanta,    Integer.valueOf(1));
        lp.add(stockSuper8,   Integer.valueOf(1));
        lp.add(stockSnickers, Integer.valueOf(1));

        lp.add(panelMoneda,Integer.valueOf(1));
        lp.add(SueldoActual,Integer.valueOf(1));
        lp.add(persona,Integer.valueOf(1));
        lp.add(panelMano,Integer.valueOf(1));
        lp.add(botonTomar,Integer.valueOf(1));

        frame.setContentPane(lp);
        frame.setVisible(true);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        actualizarStocks();

    }
    /**
     * Permite al usuario tomar el producto actualmente en mano.
     * Muestra un mensaje con el resultado de consumir el producto.
     */
    private void tomarProducto() {
        if (productoEnMano != null) {
            JOptionPane.showMessageDialog(frame,
                    "Has bebido/comido: " + productoEnMano.consumir(),
                    "Enjoy",
                    JOptionPane.INFORMATION_MESSAGE);
            productoEnMano = null;
            actualizarProductoEnMano();
        } else {
            JOptionPane.showMessageDialog(frame,
                    "No tienes ningún producto",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Actualiza la vista del producto en mano (imagen y número de serie).
     */
    private void actualizarProductoEnMano() {
        if (productoEnMano != null) {
            if (productoEnMano.consumir().equals("cocacola")) {
                lblProductoEnMano.setIcon(iconCoca);
            } else if (productoEnMano.consumir().equals("fanta")) {
                lblProductoEnMano.setIcon(iconFanta);
            } else if (productoEnMano.consumir().equals("sprite")) {
                lblProductoEnMano.setIcon(iconSprite);
            } else if (productoEnMano.consumir().equals("snickers")) {
                lblProductoEnMano.setIcon(iconSnickers);
            } else if (productoEnMano.consumir().equals("super8")) {
                lblProductoEnMano.setIcon(iconSuper8);
            }

            lblSerie.setText("Serie: " + productoEnMano.getSerie());
        } else {
            lblProductoEnMano.setIcon(null);
            lblSerie.setText("Serie: -");
        }
    }
    /**
     * Muestra un diálogo con los productos disponibles para que el usuario elija uno.
     * Si no se ha insertado una moneda, muestra un mensaje de advertencia.
     */
    private void mostrarDialogoProductos() {
        if (moneda == null) {
            JOptionPane.showMessageDialog(frame, "Primero inserte una moneda", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(frame, "Seleccione un producto", true);
        dialog.setLayout(new GridLayout(2, 3, 10, 10));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);

        JButton btnCoca = crearBotonProducto(iconCoca, 1);
        JButton btnSprite = crearBotonProducto(iconSprite, 2);
        JButton btnFanta = crearBotonProducto(iconFanta, 3);
        JButton btnSuper8 = crearBotonProducto(iconSuper8, 4);
        JButton btnSnickers = crearBotonProducto(iconSnickers, 5);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.add(btnCoca);
        dialog.add(btnSprite);
        dialog.add(btnFanta);
        dialog.add(btnSuper8);
        dialog.add(btnSnickers);
        dialog.add(btnCancelar);

        dialog.setVisible(true);
    }
    /**
     * Actualiza la visualización del stock de cada producto en pantalla.
     */
    private void actualizarStocks() {
        stockCoca   .setText(expendedor.getStock(1) + " disp.");
        stockSprite .setText(expendedor.getStock(2) + " disp.");
        stockFanta  .setText(expendedor.getStock(3) + " disp.");
        stockSuper8 .setText(expendedor.getStock(4) + " disp.");
        stockSnickers.setText(expendedor.getStock(5) + " disp.");
    }


    /**
     * Crea un botón asociado a un producto con ícono y acción de compra.
     *
     * @param icono Ícono que representa el producto.
     * @param idProducto Identificador del producto a comprar.
     * @return Botón configurado.
     */
    private JButton crearBotonProducto(Icon icono, int idProducto) {
        JButton btn = new JButton(icono);
        btn.addActionListener(e -> {
            try {
                comprador = new Comprador(moneda, idProducto, expendedor);
                String tipoProducto = comprador.queConsumiste();

                productoEnMano = new Producto() {
                    @Override
                    public String consumir() {
                        return comprador.queConsumiste();
                    }
                    @Override
                    public int getSerie() {
                        return new Random().nextInt(1000);
                    }
                };
                vuelto += comprador.cuantoVuelto();
                moneda = null;
                persona.setIcon(iconPersona1);
                actualizarProductoEnMano();
                actualizarSaldo();
                actualizarStocks();
                ((JDialog)btn.getTopLevelAncestor()).dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,
                        "Error al comprar: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        return btn;
    }
    /**
     * Permite insertar una moneda en la máquina si el saldo del usuario es suficiente.
     *
     * @param valor Valor de la moneda a insertar (100, 500, 1000).
     * @param iconoPersona Ícono de la persona con la moneda correspondiente.
     */
    private void insertarMoneda(int valor, Icon iconoPersona) {
        if (moneda != null) {
            sueldo += moneda.getValor();
        }
        if (sueldo >= valor) {
            persona.setIcon(iconoPersona);
            moneda = crearMoneda(valor);
            sueldo -= valor;
            actualizarSaldo();
        } else {
            JOptionPane.showMessageDialog(frame, "Saldo insuficiente", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Crea una instancia de una moneda correspondiente al valor entregado.
     *
     * @param valor Valor de la moneda.
     * @return Objeto de tipo Moneda correspondiente.
     */
    private Moneda crearMoneda(int valor) {
        return switch (valor) {
            case 100 -> new Moneda100();
            case 500 -> new Moneda500();
            case 1000 -> new Moneda1000();
            default -> null;
        };
    }
    /**
     * Cancela la compra actual devolviendo el valor de la moneda al saldo.
     */
    private void cancelarCompra() {
        if (moneda != null) {
            sueldo += moneda.getValor();
            moneda = null;
            persona.setIcon(iconPersona1);
            actualizarSaldo();
        }
    }
    /**
     * Suma el vuelto pendiente al saldo del usuario.
     */
    private void recibirVuelto() {
        sueldo += vuelto;
        vuelto = 0;
        actualizarSaldo();
    }
    /**
     * Actualiza la etiqueta de saldo visible en pantalla.
     */
    private void actualizarSaldo() {
        SueldoActual.setText("Saldo: " + sueldo);
    }
}
/**
 * Clase que inicializa la interfaz gráfica de usuario de la máquina expendedora.
 * Contiene solo el metodo main que lanza la ventana Swing.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}