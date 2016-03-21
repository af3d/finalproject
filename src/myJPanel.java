import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myJPanel extends JPanel implements ActionListener
{
    instJPanel instP;
    scoresJPanel scoresP;
    creditsJPanel creditsP;
    optionsJPanel optionsP;
    gameJPanel gameP;
    menuJPanel menuP;
    menuBarJPanel mBarP;
    public myJPanel ()
    {
        super();
        setLayout(new BorderLayout());
        
        //Initialize Panels
        
        instP = new instJPanel();
        scoresP = new scoresJPanel();
        creditsP = new creditsJPanel();
        optionsP = new optionsJPanel();
        gameP = new gameJPanel();
        menuP = new menuJPanel();
        mBarP = new menuBarJPanel(gameP, optionsP);
        
        //Add Listeners
        menuP.bGame.addActionListener(this);
        menuP.bOptions.addActionListener(this);
        menuP.bInst.addActionListener(this);
        menuP.bScores.addActionListener(this);
        menuP.bCredits.addActionListener(this);
        mBarP.bGiveUp.addActionListener(this);
        mBarP.bReturn.addActionListener(this);
        mBarP.bDiscard.addActionListener(this);
        mBarP.bPlay.addActionListener(this);
        
        //Add panels
        add(mBarP, "North");
        mBarP.setVisible(false);
        /*add(gameP, "Center");
        gameP.setVisible(false);
        add(instP, "Center");
        instP.setVisible(false);
        add(creditsP, "Center");
        creditsP.setVisible(false);
        add(optionsP, "Center");
        optionsP.setVisible(false);
        add(scoresP, "Center");
        scoresP.setVisible(false);*/
        add(menuP, "Center");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == mBarP.bReturn) {
            mBarP.setVisible(false);
            menuP.setVisible(true);
            gameP.setVisible(false);
            instP.setVisible(false);
            scoresP.setVisible(false);
            creditsP.setVisible(false);
            optionsP.setVisible(false);
        }
          if (obj == menuP.bGame){
           menuP.setVisible(false);
           mBarP.setVisible(true);
           mBarP.setVisButtons(0);
           mBarP.setVisButtons(1);
           gameP.setVisible(true);
           add(gameP);
        }
        if (obj == menuP.bInst){
           menuP.setVisible(false);
           mBarP.setVisible(true);
           mBarP.setVisButtons(0);
           mBarP.setVisButtons(3);
           instP.setVisible(true);
           add(instP);
        }
        if (obj == menuP.bCredits){
           menuP.setVisible(false);
           mBarP.setVisible(true);
           mBarP.setVisButtons(0);
           mBarP.setVisButtons(3);
           creditsP.setVisible(true);
           add(creditsP);
        }
         if (obj == menuP.bOptions){
           menuP.setVisible(false);
           mBarP.setVisible(true);
           mBarP.setVisButtons(0);
           mBarP.setVisButtons(2);
           optionsP.setVisible(true);
           optionsP.setMenuItems(gameP.speed, gameP.flavors, gameP.mode);
           add(optionsP);
        }
         if (obj == menuP.bScores){
           menuP.setVisible(false);
           mBarP.setVisible(true);
           mBarP.setVisButtons(0);
           mBarP.setVisButtons(3);
           scoresP.setVisible(true);
           add(scoresP);
        }
        if (obj == mBarP.bGiveUp) {
            mBarP.setVisible(false);
            menuP.setVisible(true);
            gameP.setVisible(false);
            instP.setVisible(false);
            scoresP.setVisible(false);
            creditsP.setVisible(false);
            optionsP.setVisible(false);
            gameP.gQuit();
            remove(gameP);
        }
        if (obj == mBarP.bDiscard) {
            mBarP.setVisible(false);
            menuP.setVisible(true);
            gameP.setVisible(false);
            instP.setVisible(false);
            scoresP.setVisible(false);
            creditsP.setVisible(false);
            optionsP.setVisible(false);
            optionsP.setDefaults();
            gameP.setOptions(optionsP.speed, optionsP.flavors, optionsP.mode);
        }
        if (obj == mBarP.bPlay){
           menuP.setVisible(false);
           optionsP.setVisible(false);
           mBarP.setVisible(true);
           mBarP.setVisButtons(0);
           mBarP.setVisButtons(1);
           gameP.setVisible(true);
           optionsP.setAll();
           gameP.setOptions(optionsP.speed, optionsP.flavors, optionsP.mode);
           add(gameP);
        }
    }
}
