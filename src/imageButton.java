import java.awt.*;
import javax.swing.*;

public class imageButton extends JButton {
    private ImageIcon iReleased, iPressed;
    imageButton(String imagePath1) {
        super();
        iReleased = new ImageIcon(imagePath1);
        setIcon(iReleased);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }
    imageButton(String imagePath1, String imagePath2) {
        super();
        iReleased = new ImageIcon(imagePath1);
        iPressed = new ImageIcon(imagePath2);
        setIcon(iReleased);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }
    // Sets alternate images for buttons
    public void setAltImage(boolean alt) {
        if (alt) {
            setIcon(iPressed);
        }
        else {
            setIcon(iReleased);
        }
    }
    // Changes primary or alternate images manually
    public void addImageIcon(boolean priImage, String inPath) {
        if (priImage) {
            iReleased = new ImageIcon(inPath);
        }
        else {
            iPressed = new ImageIcon(inPath);
        }
    }
}