

import org.w3c.dom.ls.LSOutput;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

class ImagePanel extends JPanel {
    private Image image;

    public ImagePanel(ImageIcon icon) {
        this.image = icon.getImage();
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}

class GUI {
    private int sueldo = 2000;
    private Moneda moneda = null;
    private Comprador comprador = null;
    private Expendedor expendedor = new Expendedor(100);
    private int vuelto = 0;
    private JFrame frame;
    private JLabel persona;
    private JLabel SueldoActual;

    private Icon iconCoca, iconSprite, iconFanta, iconSuper8, iconSnickers;
    private Icon icon100, icon500, icon1000, iconExp, iconPersona1, iconPersona100, iconPersona500, iconPersona1000;

    public GUI() {
        frame = new JFrame("Maquina Expendedora");
        cargarImagenes();
        inicializarGUI();
    }

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

    private void inicializarGUI() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(null);
        frame.add(panel, BorderLayout.CENTER);

        persona = new JLabel(iconPersona1);
        persona.setBounds(250, 263, 150, 400);
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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.setComponentZOrder(persona, 0);
        panel.setComponentZOrder(Exp, 1);
    }

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

    private JButton crearBotonProducto(Icon icono, int idProducto) {
        JButton btn = new JButton(icono);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);

        btn.addActionListener(e -> {
            try {
                comprador = new Comprador(moneda, idProducto, expendedor);
                vuelto += comprador.cuantoVuelto();
                persona.setIcon(iconPersona1);
                moneda = null;
                JOptionPane.showMessageDialog(frame, "Has comprado: " + comprador.queConsumiste(), "Compra Exitosa", JOptionPane.INFORMATION_MESSAGE);
                ((JDialog) btn.getTopLevelAncestor()).dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        return btn;
    }

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

    private Moneda crearMoneda(int valor) {
        return switch (valor) {
            case 100 -> new Moneda100();
            case 500 -> new Moneda500();
            case 1000 -> new Moneda1000();
            default -> null;
        };
    }

    private void cancelarCompra() {
        if (moneda != null) {
            sueldo += moneda.getValor();
            moneda = null;
            persona.setIcon(iconPersona1);
            actualizarSaldo();
        }
    }

    private void recibirVuelto() {
        sueldo += vuelto;
        vuelto = 0;
        actualizarSaldo();
    }

    private void actualizarSaldo() {
        SueldoActual.setText("Saldo: " + sueldo);
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}