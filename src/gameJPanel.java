import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameJPanel extends JPanel
{
    private boolean gameStatePaused = false;
    private boolean soundMuted = false, gameKill = false; // will need to be changed after options panel is added
    JButton bSpeed, bMode, bFlavor;
    int speed = 2, flavors = 3, mode = 1;
    public gameJPanel ()
    {   
        super();
        bSpeed = new JButton("Speed: " + speed);
        bFlavor = new JButton("Flavors: " + flavors);
        bMode = new JButton("Mode: " + mode);
        add(bSpeed);
        add(bFlavor);
        add(bMode);
        System.out.println("game constructed");
    }
    // Pauses or resumes game loop
    public void setGamePaused(boolean ispaused) {
        gameStatePaused = ispaused;
    }
    // Return whether or not game loop is paused
    public boolean getGameState() {
        return gameStatePaused;
    }
    // Mutes and unmutes sound effects
    public void setMuted(boolean muted) {
        soundMuted = muted;
    }
    // Returns whether or not game is muted
    public boolean isMuted() {
        return soundMuted;
    }
    // Set game options
    public void setOptions(int inSpeed, int inFlavors, int inMode) {
        speed = inSpeed;
        flavors = inFlavors;
        mode = inMode;
        bSpeed.setText("Speed: " + speed);
        bFlavor.setText("Flavors: " + flavors);
        bMode.setText("Mode: " + mode);
    }
    // Quit current game
    public void gQuit() {
        gameKill = true;
    }
}
