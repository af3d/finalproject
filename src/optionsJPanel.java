import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;

public class optionsJPanel extends JPanel implements ActionListener
{   
    imageButton bMode, bSpeed, bFlavors, bSound;
    JRadioButton jrbNormal, jrbSurvival, jrbMarathon;
    ButtonGroup bgMode;
    optionsSlider jsSpeed, jsFlavors;
    int DEFAULT_FLAVORS = 3, DEFAULT_SPEED = 2, DEFAULT_MODE = 1;
    boolean DEFAULT_SOUND = true;
    int flavors = DEFAULT_FLAVORS, speed = DEFAULT_SPEED, mode = DEFAULT_MODE;
    boolean sound = DEFAULT_SOUND;
    public optionsJPanel ()
    {
        super();        
        //Initialize imageButtons
        bMode = new imageButton("images/optionsP/iMode.png");
        bSpeed = new imageButton("images/optionsP/iSpeed.png");
        bFlavors = new imageButton("images/optionsP/iFlavor.png");
        bSound = new imageButton("images/optionsP/iSoundEn.png", "images/optionsP/iSoundDis.png");
        bSound.addActionListener(this);
        
        System.out.println("Initializing optionsP");
        
        
        //Initialize and group RadioButtons
        jrbNormal = new JRadioButton("Normal");
        jrbSurvival = new JRadioButton("Survival");
        jrbMarathon = new JRadioButton("Marathon");
        bgMode = new ButtonGroup();
        bgMode.add(jrbNormal);
        bgMode.add(jrbSurvival);
        bgMode.add(jrbMarathon);switch (mode) {
            case 1: {
                jrbNormal.setSelected(true);
                break;
            }
            case 2: {
                jrbSurvival.setSelected(true);
                break;
            }
            case 3: {
                jrbMarathon.setSelected(true);
                break;
            }
        }

        
        //Initialize Sliders
        jsSpeed = new optionsSlider(JSlider.HORIZONTAL, 1, 5, speed, 1);
        jsFlavors = new optionsSlider(JSlider.HORIZONTAL, 1, 5, flavors, 1);
        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 10;
        c.insets = new Insets(10,0,0,0);
        add(bMode, c);
        c.gridy = 2;
        c.gridx = 0;
        add(jrbNormal, c);
        c.gridx = 1;
        add(jrbSurvival, c);
        c.gridx = 2; 
        add(jrbMarathon, c);
        c.gridy = 3;
        c.gridx = 1;
        add(bSpeed, c);
        c.gridy = 4;
        add(jsSpeed, c);
        c.gridy = 5;
        add(bFlavors, c);
        c.gridy = 6;
        add(jsFlavors, c);
        c.gridy = 7;
        add(bSound, c);
        
    }
    //Save changes locally
    public void setAll() {
        speed = jsSpeed.getValue();
        flavors = jsSpeed.getValue();
        if (jrbNormal.isSelected()) {
            mode = 1;
        }
        else if (jrbSurvival.isSelected()) {
            mode = 2;
        }
        else {
            mode = 3;
        }   
    }
    // Set slider values
    public void setMenuItems(int inSpeed, int inFlavors, int inMode) {
        jsSpeed.setValue(inSpeed);
        jsFlavors.setValue(inFlavors);
        switch (inMode) {
            case 1: {
                jrbNormal.setSelected(true);
                break;
            }
            case 2: {
                jrbSurvival.setSelected(true);
                break;
            }
            case 3: {
                jrbMarathon.setSelected(true);
                break;
            }
        }
        if (sound) {
            bSound.setAltImage(true);
        }
        else if (!sound) {
            bSound.setAltImage(false);
        }
    }
    public void setMuted(boolean isMuted) {
        if (isMuted) {
            bSound.setAltImage(true);
            sound = false;
        }
        else if (!isMuted) {
            bSound.setAltImage(false);
            sound = true;
        }
    }
    //Reset Defaults
    public void setDefaults() {
        speed = DEFAULT_SPEED;
        flavors = DEFAULT_FLAVORS;
        mode = DEFAULT_MODE;
        sound = DEFAULT_SOUND;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == bSound) {
           if (sound) {
               setMuted(true);
           }
           else {
               setMuted(false);
           }
        }
    }
}
