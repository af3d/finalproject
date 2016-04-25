public class coneSprite extends sprite{
    int speed = 100;
    flavorSprite stuck[];
    int stuckCount = 0;
    boolean muted; 
    
    coneSprite(boolean inMuted) {
        super();
        muted = inMuted;
    }

    public void init(int x, int y) {
        this.setImage("images/gameP/coneCut.png");
        this.x = x;
        this.y = y;
        this.setBounds(x, y,
                this.width, this.height);  
    }
    
    public void moveX(int inX) {
        int delta = inX - (this.x + (this.width / 2));
        if (delta > speed) {
            delta = speed;
        }
        if (delta < -speed) {
            delta = -speed;
        }
        /*if (stuckCount > 0) {
            for (int x = 0; x <= stuckCount; x++) {
                stuck[x].moveStuck(this.x + (this.width / 2), 100);
            }
        }*/
        
        setPosition(this.x + delta, this.y);
        
    }
}
