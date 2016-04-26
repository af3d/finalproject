
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class scoresJPanel extends JPanel implements ActionListener, MouseListener{

    scoreKeeper scores;
    JTextField name;
    JTextArea JScores;
    gameJPanel gameP;
    JButton bSave; // needs converted to imageButton
    int fontSize = 16;

    public scoresJPanel(scoreKeeper inScores, gameJPanel inGameP) {
        super();
        gameP = inGameP;
        scores = inScores;
        bSave = new JButton("Save");
        JScores = new myTextBox();
        bSave.addActionListener(this);
        name = new JTextField();
        name.setSize(20,20);
        name.addMouseListener(this);
        name.setText("Enter Name");
        JScores.setText("High Scores:\n\n" + scores.getHighScores());
        add(JScores);
    }
    
    public void newHighScore(boolean yes) {
        if (yes) {
           remove(JScores);
           add(name);  
           add(bSave);
        }
        else {
            
        }
    }
    public void setScore() {
        if (name.getText().equals("Enter Name") || name.getText().equals("")) {
                    scores.setNewScore("Unknown", gameP.score);
                }
                else {
                    scores.setNewScore(name.getText(), gameP.score);
                }
                JScores.setText("High Scores:\n\n" + scores.getHighScores());
                remove(name);
                remove(bSave);
                add(JScores);
               // JScores is not visible
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == bSave) {
            setScore();
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
     
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent event) {
           Object obj = event.getSource();
        if (obj == name) {
            name.setText("");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
