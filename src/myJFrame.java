
import java.awt.*;
import javax.swing.*;

public class myJFrame extends JFrame{

    public myJFrame() {
        super("Berkey Creamery");
        myJPanel mPanel = new myJPanel();

        getContentPane().add(mPanel, "Center");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 800));
        setSize(1000, 800);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setVisible(true);
    }


}
