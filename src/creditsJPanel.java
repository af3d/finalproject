
import javax.swing.*;
import java.awt.*;

public class creditsJPanel extends JPanel {

    myTextBox jCredits;
    

    public creditsJPanel() {
        super();
        jCredits = new myTextBox(24);
        jCredits.setSize(20,30);
        jCredits.setText("\n\n\n\tNittany Creamery: \n\n\tDeveloped By \n\tGroup 01 \n\tIST240 \n\tSpring 2016 \n\n\tAdam Fedor \n\tDaniel Maes \n\tEric Ruppert \n\tJessica Spiegelblatt");
        add(jCredits);
    }
}
