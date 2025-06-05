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



public class Main {
}
