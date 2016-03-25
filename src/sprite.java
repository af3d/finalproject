
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Eric
 */
public abstract class sprite extends JButton {

    Point mouseLoc;
    ImageIcon icon;

    public sprite(String imagePath) {
        super();
        icon = new ImageIcon(imagePath);
        setIcon(icon);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

    }

    // Calls move functions for drawing
    public abstract void update();

    public void setPosition(int x, int y) {
        setBounds(x, y, getBounds().width, getBounds().height);
    }
}
