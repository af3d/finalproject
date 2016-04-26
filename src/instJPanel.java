
import javax.swing.*;
import java.awt.*;

public class instJPanel extends JPanel {

    myTextBox JInst;

    public instJPanel() {
        super();
        JInst = new myTextBox();
        JInst.setSize(10,10);
        JInst.setText("Instructions:\n\nMove your mouse left and right to move your cone. Your goal is to catch ice cream scoops as possible in 2 minutes."
                + "\n\n To adjust the game speed, number of flavors, and game mode, click the options button.");
        add(JInst);
    }
}
