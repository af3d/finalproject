
import javax.swing.*;
import java.awt.*;

public class instJPanel extends JPanel {

    JTextArea JInst;
    int fontSize = 16;

    public instJPanel() {
        super();
        JInst = new JTextArea(10, 10);
        JInst.setEditable(false);
        JInst.setBackground(Color.LIGHT_GRAY);
        JInst.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontSize+8));
        JInst.setText("Instructions:\n\nMove your mouse left and right to move your cone. Your goal is to catch ice cream scoops and not miss any."
                + "\n\nTo adjust the game speed, number of flavors, and game mode, click the options button.");
        add(JInst);
    }
}
