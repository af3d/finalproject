
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class myJPanel extends JPanel implements ActionListener, ChangeListener {

    instJPanel instP;
    scoresJPanel scoresP;
    creditsJPanel creditsP;
    optionsJPanel optionsP;
    gameJPanel gameP;
    menuJPanel menuP;
    menuBarJPanel mBarP;
    options gameOptions;
    scoreKeeper scores;

    public myJPanel() {
        super();
        setLayout(new BorderLayout());
        //Initialize Options
        gameOptions = new options();
        gameOptions.getOptions();
        scores = new scoreKeeper();

        //Initialize Panels        
        instP = new instJPanel();
        scoresP = new scoresJPanel(scores);
        creditsP = new creditsJPanel();
        optionsP = new optionsJPanel(gameOptions);
        gameP = new gameJPanel(gameOptions, scores);
        menuP = new menuJPanel();
        mBarP = new menuBarJPanel(gameOptions);

        //Add Listeners
        for (JPanel j : new JPanel[] {menuP, mBarP, optionsP}) {
            for (Component c : j.getComponents()) {
                if (c instanceof imageButton) { 
                    ((imageButton) c).addActionListener(this);
                }
            }            
        }
        //Add panels
        switchPanel("menu");
    }

    public void switchPanel(String state) {

        if (state.equals("menu")) {
            add(menuP, "Center");
        } else {
            remove(menuP);
        }
        if (!state.equals("menu")) {
            add(mBarP, "North");
        } else {
            remove(mBarP);
        }
        if (state.equals("game")) {
            add(gameP, "Center");
            gameP.gameStart();
        } else {
            remove(gameP);
        }
        if (state.equals("instructions")) {
            add(instP, "Center");
        } else {
            remove(instP);
        }
        if (state.equals("scores")) {
            scoresP.updateScores();
            add(scoresP, "Center");
            mBarP.setVisButtons(3);
        } else {
            remove(scoresP);
        }
        if (state.equals("credits")) {
            add(creditsP, "Center");
        } else {
            remove(creditsP);
        }
        if (state.equals("options")) {
            add(optionsP, "Center");
        } else {
            remove(optionsP);
        }
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();

        if (obj == mBarP.bReturn) {
            gameOptions.storeOptions();
            switchPanel("menu");
        }

        if (obj == menuP.bInst) {
            switchPanel("instructions");
            mBarP.setVisButtons(3);
        }

        if (obj == menuP.bCredits) {
            switchPanel("credits");
            mBarP.setVisButtons(3);
        }

        if (obj == menuP.bScores) {
            switchPanel("scores");
        }

        if (obj == mBarP.bGiveUp) {
            gameP.gQuit();
            switchPanel("menu");
        }

        if (obj == menuP.bOptions) {
            gameOptions.getOptions();
            mBarP.setVisButtons(2);
            switchPanel("options");
        }

        if (obj == mBarP.bPlay || obj == menuP.bGame) {
            switchPanel("game");
            mBarP.setVisButtons(1);
            gameOptions.storeOptions();

        }

        // Toggles pause status
        if (obj == mBarP.bPause) {
            mBarP.bPause.setAltImage();
            gameP.setGamePaused();
        }
        // Toggles mute
        if (obj == mBarP.bSound || obj == optionsP.bSound) {
            gameOptions.setMuted();
            mBarP.bSound.setAltImage();
            optionsP.bSound.setAltImage();
        }
        if (obj == optionsP.jrbEasy) {
            gameOptions.setMode(1);
        }
        if (obj == optionsP.jrbNormal) {
            gameOptions.setMode(2);
        }
        if (obj == optionsP.jrbHard) {
            gameOptions.setMode(3);
        }
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        Object source = ce.getSource();
        if (source == optionsP.jsFlavors) {
            gameOptions.setFlavors(optionsP.jsFlavors.getValue());
        }
        if (source == optionsP.jsSpeed) {
            gameOptions.setSpeed(optionsP.jsSpeed.getValue());
        }
    }
}
