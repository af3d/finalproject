
import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class optionsJPanel extends JPanel implements ChangeListener {

    imageButton bMode, bSpeed, bFlavors, bSound;
    JRadioButton jrbEasy, jrbNormal, jrbHard;
    ButtonGroup bgMode;
    optionsSlider jsSpeed, jsFlavors;
    options gameOpt;

    JTextArea saved;

    public optionsJPanel(options inOpt) {
        super();
        gameOpt = inOpt;

        //Initialize imageButtons
        bMode = new imageButton("images/optionsP/iMode.png");
        bSpeed = new imageButton("images/optionsP/iSpeed.png");
        bFlavors = new imageButton("images/optionsP/iFlavor.png");
        bSound = new imageButton("images/optionsP/iSoundEn.png",
                "images/optionsP/iSoundEnP.png",
                "images/optionsP/iSoundDis.png",
                "images/optionsP/iSoundDisP.png");

        System.out.println("Initializing optionsP");

        //Initialize and group RadioButtons
        jrbEasy = new JRadioButton("Easy");
        jrbNormal = new JRadioButton("Normal");
        jrbHard = new JRadioButton("Hard");
        bgMode = new ButtonGroup();
        bgMode.add(jrbEasy);
        bgMode.add(jrbNormal);
        bgMode.add(jrbHard);

        //Initialize Sliders
        jsSpeed = new optionsSlider(JSlider.HORIZONTAL, 1, 5, gameOpt.getSpeed(), 1);
        jsFlavors = new optionsSlider(JSlider.HORIZONTAL, 1, 5, gameOpt.getFlavors(), 1);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 10;
        c.insets = new Insets(10, 0, 0, 0);
        add(bMode, c);
        c.gridy = 1;
        c.gridx = 0;
        add(jrbEasy, c);
        c.gridx = 1;
        add(jrbNormal, c);
        c.gridx = 2;
        add(jrbHard, c);
        c.gridy = 2;
        c.gridx = 1;
        add(bSpeed, c);
        c.gridy = 3;
        add(jsSpeed, c);
        c.gridy = 4;
        add(bFlavors, c);
        c.gridy = 5;
        add(jsFlavors, c);
        c.gridy = 6;
        add(bSound, c);

        setOptionScreen();
        jsSpeed.addChangeListener(this);
        jsFlavors.addChangeListener(this);
        jrbEasy.addChangeListener(this);
        jrbNormal.addChangeListener(this);
        jrbHard.addChangeListener(this);
    }

    // Set values to values from xml
    public void setOptionScreen() {
        jsSpeed.setValue(gameOpt.getSpeed());
        jsFlavors.setValue(gameOpt.getFlavors());

        switch (gameOpt.getMode()) {
            case 1: {
                jrbEasy.setSelected(true);
                break;
            }
            case 2: {
                jrbNormal.setSelected(true);
                break;
            }
            case 3: {
                jrbHard.setSelected(true);
                break;
            }
        }

        bSound.setAltImage(gameOpt.getMuted());

    }

    @Override
    public void stateChanged(ChangeEvent event) {
        Object obj = event.getSource();
        if (obj == jsSpeed) {
            gameOpt.setSpeed(jsSpeed.getValue());
        }
        else if (obj == jsFlavors) {
            gameOpt.setFlavors(jsFlavors.getValue());
        }
        else if (obj == jrbEasy) {
            gameOpt.setMode(1);
        }
        else if (obj == jrbNormal) {
            gameOpt.setMode(2);
        }
        else if (obj == jrbHard) {
            gameOpt.setMode(3);
        }
    }

}
