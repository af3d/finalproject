import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class sprite extends JButton {

    ImageIcon icon;
    int x, y, width, height;

    public sprite() {
        super();
    }
    
    public void setImage(String imagePath){
        icon = new ImageIcon(imagePath);
        setIcon(icon);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusable(false);
        this.width = icon.getIconWidth();
        this.height = icon.getIconHeight();
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(x, y, this.width, this.height);
    }
}
