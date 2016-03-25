
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class gameJPanel extends JPanel implements ActionListener {

    Point mouseLoc;
    private boolean gameStatePaused = false;
    private boolean gameKill = false;
    String[] flavorIcons = {"images/gameP/flavor1.png",
        "images/gameP/flavor2.png",
        "images/gameP/flavor3.png",
        "images/gameP/flavor4.png",
        "images/gameP/flavor5.png"};
    JButton bSpeed, bMode, bFlavor;
    options gameOpt;
    coneSprite cone;
    flavorSprite[] flavors;
    int flavorCount = 0, flavorDelay = 200;
    Timer mouseCycle;

    public gameJPanel(options inOpt) {
        super();
        gameOpt = inOpt;
        setLayout(null);
        mouseLoc = MouseInfo.getPointerInfo().getLocation();
        flavors = new flavorSprite[15];
    }

    // starts game loops
    public void gameStart() {
        switch (gameOpt.mode) {
            case 1: {
                gameNormal();
                break;
            }
            case 2: {
                gameMarathon();
                break;
            }
            case 3: {
                gameSurvival();
                break;
            }
        }
        mouseCycle = new Timer(10, this);
        mouseCycle.start();
    }

    public void setGamePaused() {
        gameStatePaused = !gameStatePaused;
    }

    public void setGamePaused(boolean bool) {
        gameStatePaused = bool;
    }

    // Return whether or not game loop is paused
    public boolean getGameState() {
        return gameStatePaused;
    }

    // Quit current game
    public void gQuit() {
        gameKill = true;
    }

    //Normal Game Loop
    public void gameNormal() {
        mouseLoc = MouseInfo.getPointerInfo().getLocation();
        cone = new coneSprite("images/gameP/coneCut.png");
        add(cone);

    }

    public void update() {
        cone.update();
        for (int i = 0; i < flavorCount; i++) {
            flavors[i].update();
        }
        flavorDelay--;
        if (flavorDelay == 0 && flavorCount < 14) {
            int flavor = (int) Math.round(Math.random() * 4);
            flavors[flavorCount] = new flavorSprite(flavorIcons[flavor]);
            add(flavors[flavorCount]);
            flavorCount++;
            flavorDelay = 200;
        }
    }

    //Marathon Game Loop
    public void gameMarathon() {

    }

    //Survival Game Loop
    public void gameSurvival() {

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == mouseCycle) {
            update();
        }
    }
}
