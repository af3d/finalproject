
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class flavorSprite extends sprite {

    int speed = 5, pWidth, pHeight, flavor;
    soundPlayer flavorDrop, flavorMiss;
    boolean muted, caught = false, stop = false;

    public flavorSprite(boolean inMuted) {
        super();
        muted = inMuted;
        flavorDrop = new soundPlayer();
        flavorMiss = new soundPlayer();
        try {
            flavorDrop.setIStream("sounds/drop.wav");
            flavorMiss.setIStream("sounds/miss.wav");
        } catch (IOException ex) {
            Logger.getLogger(flavorSprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setFlavor(int i, int pWidth, int pHeight) {
        this.flavor = i;
        this.pWidth = pWidth;
        this.pHeight = pHeight;
        this.setImage("images/gameP/flavor" + (i + 1) + ".png");
        this.x = (int) (Math.round(Math.random() * (this.pWidth - this.width / 2)));
        this.y = 25;
        this.setPosition(this.x, this.y);

    }
    
    public void fall() {
        
        if (!this.stop && !this.caught) {
            this.y += speed;
            setPosition(this.x, this.y);

                if (this.y >= this.pHeight) {
                    stop = true;
                }

            if (!muted) {
                flavorDrop.play();
                if (this.y >= this.pHeight) {
                    flavorMiss.play();
                }
            }
        }
    }
    
    public void moveX(int x) {
            this.x = x;
            setPosition(this.x, this.y);
    }
    
}
