
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class gameJPanel extends JPanel implements MouseMotionListener, ActionListener, ComponentListener {

    private boolean gameStatePaused = false;
    private boolean gameKill = false;

    sprite topSprite;
    options gameOpt;
    coneSprite cone;
    flavorSprite flavor;
    scoreKeeper scores;

    int flavorCount = 0, flavorDelay = 200, flavorWait = 0, lives, score = 0, time,
            addSpeed = 0, flavorsCaught = 0;
    double scoreMult = 1d;
    Timer flavorT, gameT, flavorMoveT;
    JTextArea stats;

    public gameJPanel(options inOpt, scoreKeeper inScores) {
        super(true);
        gameOpt = inOpt;
        scores = inScores;
        setLayout(null);
        addMouseMotionListener(this);
        addComponentListener(this);
        flavorMoveT = new Timer(10, this);
        flavorMoveT.setInitialDelay(0);
        flavorT = new Timer(10, this);
        flavorMoveT.setInitialDelay(0);
        gameT = new Timer(1000, this); //Used for actual game timing (displayed in top left corner
        gameT.setInitialDelay(0);
    }

    // starts game loops
    public void gameStart() {
        switch (gameOpt.mode) {
            case 1: // Easy mode
                flavorDelay = 250;
                scoreMult = 2d;
                break;
            case 2: // Normal mode
                flavorDelay = 200;
                scoreMult = 5d;
                break;
            case 3: // Hard mode
                flavorDelay = 150;
                scoreMult = 8d;
                break;
        }

        gameKill = false;

        stats = new JTextArea();
        stats.setBackground(Color.WHITE);
        stats.setBounds(0, 0, 300, 20);

        scoreMult += (gameOpt.speed * 1.5 + gameOpt.flavors * 1.5);
        cone = new coneSprite(gameOpt.muted);
        add(cone);
        //I have no idea why this needs to be duplicated, but the cone won't reappear if you give up without it - Eric
        cone.init(getWidth() / 2, getHeight() - cone.height);
        cone.init(getWidth() / 2, getHeight() - cone.height);
        topSprite = cone;
        add(stats);

        flavorMoveT.start();
        flavorT.start();
        gameT.start();
        repaint();
    }

    public void setGamePaused() {
        gameStatePaused = !gameStatePaused;
    }

    // Return whether or not game loop is paused
    public boolean getGameState() {
        return gameStatePaused;
    }

    // Quit current game
    public void gQuit() {
        this.removeAll();
        flavorMoveT.stop();
        flavorT.stop();
        gameT.stop();
        gameKill = true;
        flavorCount = 0;
        flavorWait = 0;
        lives = 5;
        score = 0;
        time = 0;
        flavorsCaught = 0;

    }

    public void addFlavor() {
        
        if (flavorDelay > flavorWait) {
            flavorWait++;
        } else{
            int flavorColor = (int) Math.round(Math.random() * (gameOpt.flavors - 1));
            flavor = new flavorSprite(gameOpt.muted);
            flavor.setFlavor(flavorColor, this.getWidth(), this.getHeight());
            flavor.speed += (gameOpt.speed * 2) + addSpeed;
            add(flavor);
            setComponentZOrder(cone, getComponentCount()-1);
            setComponentZOrder(flavor, 1);
            flavorWait = 0;
            flavorDelay = ((int) Math.round(Math.random() * 100)) + 25;
        }
    }

    public void moveFlavors() {
        for (Component c : this.getComponents()) {
            if (c instanceof flavorSprite) {
                flavorSprite temp = (flavorSprite) c;
                if (!temp.stop && !temp.caught) {
                    temp.fall();
                    //Uses built in collision detection
                    Rectangle2D rect1 = temp.getBounds();
                    Rectangle2D rect2 = topSprite.getBounds();
                    Rectangle2D intersect = rect1.createIntersection(rect2);
                    System.out.println(intersect.getBounds().toString());
                    if (intersect.getWidth() > 0 && intersect.getHeight() > 0 && intersect.getY() == rect2.getY()) {
                        int yDistance = (int)Math.round(rect1.getY() + rect1.getHeight() - rect2.getY());
                        if (topSprite instanceof flavorSprite){
                            if (yDistance > rect1.getHeight() * 0.6 && yDistance < rect1.getHeight() * 0.8) {
                                if (intersect.getWidth() > rect2.getWidth() * 0.75) {
                                    catchFlavor(temp);
                                }
                            }
                        }
                        else if (yDistance > rect1.getHeight() * 0.1 && yDistance < rect1.getHeight() * 0.3){
                            if (intersect.getWidth() > rect2.getWidth() * 0.75){
                                catchFlavor(temp);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void catchFlavor(flavorSprite flavor){
        flavor.caught = true;
        flavor.offset = flavor.x - cone.x;
        topSprite = flavor;
        score += (5 * scoreMult);
        flavorsCaught++;
    }
    public void moveCaught() {
        for (Component c : this.getComponents()) {
            if (c instanceof flavorSprite) {
                if (((flavorSprite) c).caught) {
                    ((flavorSprite) c).moveX(cone.getX());
                }
            }
        }
    }

    public boolean collision(int xa1, int xb1, int xa2, int xb2) {
        //Find the amount overlap between the two sprites as a percentage
        int i = Math.abs(Math.min((xa2 + xa1), (xb2 + xb1)) - Math.max(xa1, xb1));
        int test = (int) (((xa2 + xb2 - i) / i) * 100.0f);
        System.out.println("xa=" + (xa1 + xa2) + " xb=" + (xb1 + xb2));
        System.out.println("xa1=" + xa1 + " xa2=" + xa2 + "xb1=" + xb1 + " xb2=" + xb2);
        System.out.println(i);
        System.out.println(test);
        return (test > 95);
    }

    public String getTime() {
        String curTime;
        if (time >= 60) {
            curTime = Integer.toString(time / 60);
        } else {
            curTime = "00";
        }
        if (time % 60 < 10) {
            curTime += (":0" + time % 60);
        } else if (time % 60 >= 10) {
            curTime += (":" + time % 60);
        }
        return curTime;
    }

    public void updateTime() {
        /*switch (gameOpt.mode) {
                    case 1: // Normal mode
                        time++;
                        stats.setText("Score: " + score + "     Elapsed Time: " + getTime());
                        break;
                    case 2: // Marathon mode
                        time--;
                        stats.setText("Score: " + score + "     Remaining Time: " + getTime());
                        break;
                    case 3: // Survival mode
                        time++;
                        stats.setText("Score: " + score + "     Elapsed Time: --:--");
                        //Increases speed every 5 seconds for marathon mode
                        if (time % 5 == 0) {
                            addSpeed++;
                        }   
                        break;
                }*/
        stats.setText("Score: " + score + "     Caught: " + flavorsCaught + "     Elapsed Time: " + getTime());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (!gameKill && !gameStatePaused) {
            if (obj == flavorT) {
                addFlavor();
            }
            if (obj == gameT) {
                time++;
                updateTime();
            }
            if (obj == flavorMoveT) {
                moveFlavors();
                moveCaught();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent evt) {
        Point pt = evt.getPoint();
        int x = pt.x;
        cone.moveX(x);
    }

    @Override
    public void mouseDragged(MouseEvent evt) {
        Point pt = evt.getPoint();
        int x = pt.x;
        cone.moveX(x);
    }

    @Override
    public void componentResized(ComponentEvent ce) {
        cone.init(this.getWidth() / 2, getHeight() - cone.height);

    }

    @Override
    public void componentMoved(ComponentEvent ce) {
    }

    @Override
    public void componentShown(ComponentEvent ce) {
    }

    @Override
    public void componentHidden(ComponentEvent ce) {
    }
}
