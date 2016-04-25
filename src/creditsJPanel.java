
import javax.swing.*;
import java.awt.*;

public class creditsJPanel extends JPanel {

    JTextArea jCredits;
    int fontSize = 24;
    

    public creditsJPanel() {
        super();
        jCredits = new JTextArea(20,30);
        jCredits.setBackground(Color.LIGHT_GRAY);
      
        jCredits.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontSize));
        jCredits.setText("\n\n\n\tNittany Creamery: \n\n\tDeveloped By \n\tGroup 01 \n\tIST240 \n\tSpring 2016 \n\n\tAdam Fedor \n\tDaniel Maes \n\tEric Ruppert \n\tJessica Spiegelblatt");
        add(jCredits);
    }
}
