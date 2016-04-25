
import javax.swing.*;
import java.awt.*;

public class scoresJPanel extends JPanel {

    scoreKeeper scores;
    JTextArea JScores;
    int fontSize = 16;

    public scoresJPanel(scoreKeeper inScores) {
        super();
        scores = inScores;
        JScores = new JTextArea();
        JScores.setBackground(Color.LIGHT_GRAY);
        JScores.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontSize));
        JScores.setText(scores.getHighScores());
        add(JScores);
    }
}
