
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

    public myJPanel() {
        super();
        setLayout(new BorderLayout());
        //Initialize Options
        gameOptions = new options();
        gameOptions.getOptions();

        //Initialize Panels        
        instP = new instJPanel();
        scoresP = new scoresJPanel();
        creditsP = new creditsJPanel();
        optionsP = new optionsJPanel(gameOptions);
        gameP = new gameJPanel(gameOptions);
        menuP = new menuJPanel();
        mBarP = new menuBarJPanel(gameOptions);

        //Add Listeners
        menuP.bGame.addActionListener(this);
        menuP.bOptions.addActionListener(this);
        menuP.bInst.addActionListener(this);
        menuP.bScores.addActionListener(this);
        menuP.bCredits.addActionListener(this);

        mBarP.bGiveUp.addActionListener(this);
        mBarP.bReturn.addActionListener(this);
        mBarP.bPlay.addActionListener(this);
        mBarP.bPause.addActionListener(this);
        mBarP.bSound.addActionListener(this);

        optionsP.jrbMarathon.addActionListener(this);
        optionsP.jrbNormal.addActionListener(this);
        optionsP.jrbSurvival.addActionListener(this);
        optionsP.bSound.addActionListener(this);
        optionsP.jsFlavors.addChangeListener(this);
        optionsP.jsSpeed.addChangeListener(this);

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
            add(scoresP, "Center");
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
            mBarP.setVisButtons(3);

        }

        if (obj == mBarP.bGiveUp) {
            gameP.gQuit();
            switchPanel("menu");
        }

        if (obj == menuP.bOptions) {
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
        if (obj == optionsP.jrbNormal) {
            gameOptions.setMode(1);
        }
        if (obj == optionsP.jrbSurvival) {
            gameOptions.setMode(2);
        }
        if (obj == optionsP.jrbMarathon) {
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
