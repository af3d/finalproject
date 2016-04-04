
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class gameJPanel extends JPanel implements MouseMotionListener, ActionListener {

    private boolean gameStatePaused = false;
    private boolean gameKill = false;
    
    sprite topSprite;
    options gameOpt;
    coneSprite cone;
    flavorSprite flavor;
    
    int flavorCount = 0, flavorDelay = 200, flavorWait = 0, lives, score = 0, time, 
            misses = 0, addSpeed = 0;
    double scoreMult = 1d;
    Timer flavorT, gameT, flavorMoveT;
    JTextArea stats;
    
    public gameJPanel(options inOpt) {
        super();
        gameOpt = inOpt;
        setLayout(null);
        addMouseMotionListener(this);
        flavorMoveT = new Timer(10,this);
        flavorT = new Timer(10, this);
        gameT = new Timer(1000, this);
    }

    // starts game loops
    public void gameStart() {
        gameKill = false;
        time = 0;
        
        stats = new JTextArea();
        stats.setBackground(Color.WHITE);
        stats.setBounds(0, 0, 300, 20);
        
        scoreMult = (gameOpt.speed * 1.5 + gameOpt.flavors * 1.5);
        
        cone = new coneSprite();
        add(cone);
        cone.init(this.getWidth()/2, getParent().getHeight()-cone.height-300);
        topSprite = cone;
        add(stats);
        switch (gameOpt.mode) {
            case 1: {
                gameNormal();
                break;
            }
            case 2: {
                gameMarathon();
                time = 300;
                break;
            }
            case 3: {
                gameSurvival();
                break;
            }
        }
        
        flavorMoveT.start();
        flavorT.start();
        gameT.start();
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
    }

    //Normal Game Loop
    public void gameNormal() { 
    // timer count up until level complete, match random order of scoops to finish level, base speed increases each level
    }

    //Marathon Game Loop
    public void gameMarathon() {
        // Timer countdown, unlimited lives, score based on scoops caught
    }

    //Survival Game Loop
    public void gameSurvival() {
        // No visible timer, score based on time alive, scoops caught, if a scoop is missed you lose
        // once scoops hit top, clear cones and continue, gets progressively faster
    }
    
    public void addFlavor() {

        if (flavorDelay > flavorWait) {
            flavorWait ++;
        }
        else {
           int flavorColor = (int) Math.round(Math.random() * (gameOpt.flavors - 1));
            flavor = new flavorSprite(gameOpt.muted);
            flavor.setFlavor(flavorColor, this.getWidth(), this.getHeight());
            flavor.speed += (gameOpt.speed * 2) + addSpeed;
            //System.out.println(flavor.speed + "");
            add(flavor); 
            flavorWait = 0;
            flavorDelay = ((int) Math.round(Math.random() * 20)) * 10;
        }           
    }
    
    public void moveFlavors() {
        int xa1, xa2, xb1, xb2;
        
        for (Component c : this.getComponents()) {
            if (c instanceof flavorSprite) { 
                if (!((flavorSprite) c).stop) {
                    ((flavorSprite) c).fall();
                }  

                if ( ((flavorSprite) c).y + ((flavorSprite) c).height - ((flavorSprite) c).speed < topSprite.y
                        && ((flavorSprite) c).y + ((flavorSprite) c).height > topSprite.y
                        ) {
                    xa1 = topSprite.getBounds().x;
                    xa2 = topSprite.getBounds().width;
                    xb1 = ((flavorSprite) c).getBounds().x;
                    xb2 = ((flavorSprite) c).getBounds().width;
                    
                    if (collision(xa1, xa2, xb1, xb2)) {
                        ((flavorSprite) c).caught = true;
                        topSprite = ((flavorSprite) c);
                    }
                }
            }
        }
    }
    
    public boolean collision(int xa1, int xb1,int xa2, int xb2){
        int i = Math.abs( Math.min( (xa2+xa1), (xb2+xb1) ) - Math.max(xa1, xb1) );
        int test = (int) ( ( (  xa2+xb2-i ) / i) * 100.0f ) ;
        System.out.println("xa=" + (xa1+xa2) + " xb=" + (xb1 + xb2));
        System.out.println("xa1=" + xa1 + " xa2=" + xa2 + "xb1=" + xb1 + " xb2=" + xb2);
        System.out.println(i);
        System.out.println(test);
        return (test > 95);
    }
    
    public String getTime() {
        String curTime;
        if (time >= 60) {
           curTime = Integer.toString(time / 60);
        }
        else {
            curTime = "00";
        }
        if (time % 60 < 10) {
            curTime += (":0" + time % 60);
        }
        else if (time % 60 >= 10) {
            curTime += (":" + time % 60);
        }
       return curTime;
    }
    

    
    public void updateTime() {
        switch (gameOpt.mode) {
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
                }
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (!gameKill && !gameStatePaused){
            if (obj == flavorT) {
                    addFlavor();
            }
            if (obj == gameT) {
                updateTime();
            }
            if (obj == flavorMoveT) {
                moveFlavors();
            }
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent evt)
    {
        Point pt = evt.getPoint();
        int x = pt.x;
        cone.moveX(x);
    }
    
    @Override
    public void mouseDragged(MouseEvent evt)
    {
        Point pt = evt.getPoint();
        int x = pt.x;
        cone.moveX(x);
    }
}
