import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

class ImagePanel extends JPanel{
    private Image image;

    public ImagePanel(ImageIcon icon) {
        this.image = icon.getImage();
        setLayout(null);
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0,0,getWidth(),getHeight(), this);
    }
}
class GUI {
    private int sueldo = 2000;
    private Moneda moneda = null;
    private Producto producto = new Sprite(1);
    Comprador comprador = null;
    Expendedor expendedor = new Expendedor(100);
    private int vuelto = 0;

    public GUI() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(null);
        frame.add(panel, BorderLayout.CENTER);
        Icon icon100 = new ImageIcon(getClass().getResource("/Imagenes/Monedas/100.png"));
        Icon icon500 = new ImageIcon(getClass().getResource("/Imagenes/Monedas/500.png"));
        Icon icon1000 = new ImageIcon(getClass().getResource("/Imagenes/Monedas/1000.png"));
        Icon iconExp = new ImageIcon(getClass().getResource("/Imagenes/Productos/Exp.png"));
        Icon iconPersona1 = new ImageIcon(getClass().getResource("/Imagenes/Productos/persona1.png"));
        Icon iconPersona100 = new ImageIcon(getClass().getResource("/Imagenes/Productos/persona100.png"));
        Icon iconPersona500 = new ImageIcon(getClass().getResource("/Imagenes/Productos/persona500.png"));
        Icon iconPersona1000 = new ImageIcon(getClass().getResource("/Imagenes/Productos/persona1000.png"));
        JButton boton100 = new JButton(icon100);
        JButton boton500 = new JButton(icon500);
        JButton boton1000 = new JButton(icon1000);
        JButton boton0 = new JButton("Cancelar");
        JButton botonNum = new JButton();
        JButton botonVue = new JButton();
        JLabel SueldoActual = new JLabel("Sueldo: " + sueldo);

        JLabel persona = new JLabel();
        persona.setOpaque(false);
        persona.setIcon(iconPersona1);
        persona.setBounds(250, 263, 150, 400);
        persona.setVisible(true);
        panel.add(persona);

        //Botones invisibles
       /* botonNum.setContentAreaFilled(false);
        botonNum.setBorderPainted(false);
        botonNum.setFocusPainted(false);
        botonNum.setOpaque(false);*/
        botonNum.setText("Num");
        botonVue.setText("Vuelto");
        /*
        botonVue.setContentAreaFilled(false);
        botonVue.setBorderPainted(false);
        botonVue.setFocusPainted(false);
        botonVue.setOpaque(false);*/

        JPanel panelMoneda = new JPanel(new GridLayout(2, 2));

        panelMoneda.setBounds(10, 60, 200, 200);

        panel.add(panelMoneda);
        panelMoneda.add(boton100);
        panelMoneda.add(boton500);
        panelMoneda.add(boton1000);
        panelMoneda.add(boton0);


        botonNum.addActionListener(e -> {
            JDialog dialog = new JDialog();
            dialog.setModal(true);
            dialog.setUndecorated(true);
            dialog.setSize(600, 600);
            dialog.setLocationRelativeTo(null);

            JPanel ventana = new JPanel();
            ventana.setBackground(Color.LIGHT_GRAY);
            ventana.setLayout(new BorderLayout());

            JPanel gridPanel = new JPanel(new GridLayout(3, 2, 10, 10));
            gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            ArrayList<JButton> botones = new ArrayList<>();
            for (int i = 1; i <= 6; i++) {
                JButton btn = new JButton("Botón " + i);
                botones.add(btn);
                gridPanel.add(btn);
            }
            botones.get(0).setText("Coca");
            botones.get(0).addActionListener(ev -> {
                if (moneda.getValor() >= EnumeracionPrecios.values()[1 - 1].getPrecio()) {
                    try {
                        comprador = new Comprador(moneda, 1, expendedor);
                    } catch (PagoIncorrectoException ex) {
                        throw new RuntimeException(ex);
                    } catch (PagoInsuficienteException ex) {
                        throw new RuntimeException(ex);
                    } catch (NoHayProductoException ex) {
                        throw new RuntimeException(ex);
                    }
                    // System.out.println(comprador.queConsumiste());
                    vuelto += comprador.cuantoVuelto();
                    persona.setIcon(iconPersona1);
                    moneda = null;
                    dialog.dispose();
                }
            });

            botones.get(1).setText("Sprite");
            botones.get(1).addActionListener(ev -> {
                if (moneda.getValor() >= EnumeracionPrecios.values()[2 - 1].getPrecio()) {
                    try {
                        comprador = new Comprador(moneda, 2, expendedor);
                    } catch (PagoIncorrectoException ex) {
                        throw new RuntimeException(ex);
                    } catch (PagoInsuficienteException ex) {
                        throw new RuntimeException(ex);
                    } catch (NoHayProductoException ex) {
                        throw new RuntimeException(ex);
                    }
                    //System.out.println(comprador.queConsumiste());
                    vuelto += comprador.cuantoVuelto();
                    persona.setIcon(iconPersona1);
                    moneda = null;
                    dialog.dispose();
                }
            });

            botones.get(2).setText("Fanta");
            botones.get(2).addActionListener(ev -> {
                if (moneda.getValor() >= EnumeracionPrecios.values()[3 - 1].getPrecio()) {
                    try {
                        comprador = new Comprador(moneda, 3, expendedor);
                    } catch (PagoIncorrectoException ex) {
                        throw new RuntimeException(ex);
                    } catch (PagoInsuficienteException ex) {
                        throw new RuntimeException(ex);
                    } catch (NoHayProductoException ex) {
                        throw new RuntimeException(ex);
                    }
                    //System.out.println(comprador.queConsumiste());
                    vuelto += comprador.cuantoVuelto();
                    persona.setIcon(iconPersona1);
                    moneda = null;
                    dialog.dispose();
                }
            });

            botones.get(3).setText("Super8");
            botones.get(3).addActionListener(ev -> {
                if (moneda.getValor() >= EnumeracionPrecios.values()[4 - 1].getPrecio()) {
                    try {
                        comprador = new Comprador(moneda, 4, expendedor);
                    } catch (PagoIncorrectoException ex) {
                        throw new RuntimeException(ex);
                    } catch (PagoInsuficienteException ex) {
                        throw new RuntimeException(ex);
                    } catch (NoHayProductoException ex) {
                        throw new RuntimeException(ex);
                    }
                    // System.out.println(comprador.queConsumiste());
                    vuelto += comprador.cuantoVuelto();
                    persona.setIcon(iconPersona1);
                    moneda = null;
                    dialog.dispose();
                }
            });

            botones.get(4).setText("Snicker");
            botones.get(4).addActionListener(ev -> {
                if (moneda.getValor() >= EnumeracionPrecios.values()[5 - 1].getPrecio()) {
                    try {
                        comprador = new Comprador(moneda, 5, expendedor);
                    } catch (PagoIncorrectoException ex) {
                        throw new RuntimeException(ex);
                    } catch (PagoInsuficienteException ex) {
                        throw new RuntimeException(ex);
                    } catch (NoHayProductoException ex) {
                        throw new RuntimeException(ex);
                    }
                    // System.out.println(comprador.queConsumiste());
                    vuelto += comprador.cuantoVuelto();
                    persona.setIcon(iconPersona1);
                    moneda = null;
                    dialog.dispose();
                }
            });

            botones.get(5).setText("Cancelar");
            botones.get(5).addActionListener(ev -> {
                dialog.dispose();
            });

            ventana.add(gridPanel, BorderLayout.CENTER);
            dialog.setContentPane(ventana);
            dialog.setVisible(true);

        });


        ImagePanel Exp = new ImagePanel((ImageIcon) iconExp);
        Exp.setBounds(350, 90, 300, 573);
        Exp.setLayout(null);
        panel.add(Exp);
        botonNum.setBounds(235, 200, 65, 90);
        botonVue.setBounds(230, 300, 70, 20);
        Exp.add(botonNum);
        Exp.add(botonVue);


        boton100.addActionListener(e -> {
            if (sueldo >= 100) {
                persona.setIcon(iconPersona100);
                moneda = new Moneda100();
                sueldo -= 100;
                SueldoActual.setText("Sueldo: " + sueldo);
            }
        });

        boton500.addActionListener(e -> {
            if (sueldo >= 500) {
                persona.setIcon(iconPersona500);
                moneda = new Moneda500();
                sueldo -= 500;
                SueldoActual.setText("Sueldo: " + sueldo);
            }
        });
        boton1000.addActionListener(e -> {
            if (sueldo >= 1000) {
                persona.setIcon(iconPersona1000);
                moneda = new Moneda1000();
                sueldo -= 1000;
                SueldoActual.setText("Sueldo: " + sueldo);
            }
        });

        boton0.addActionListener(e -> {
            if (moneda != null) {
                sueldo += moneda.getValor();
                SueldoActual.setText("Sueldo: " + sueldo);
                moneda = null;
                persona.setIcon(iconPersona1);
            }
        });

        botonVue.addActionListener(e -> {
            sueldo += vuelto;
            vuelto = 0;
            SueldoActual.setText("Sueldo: " + sueldo);

        });


        SueldoActual.setFont(new Font("Arial", Font.PLAIN, 20));
        SueldoActual.setBounds(10, 0, 1000, 50);
        panel.add(SueldoActual);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Test");
        frame.setResizable(false);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.setComponentZOrder(persona, 0);
        panel.setComponentZOrder(Exp, 1);
        //System.out.println(frame.getContentPane().getSize().height);
        //System.out.println(frame.getContentPane().getSize().width);
    }

}




public class Main{
    public static void main(String[] args){
        new GUI();
    }
}
