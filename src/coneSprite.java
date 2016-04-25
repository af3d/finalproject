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
        this.setBounds(x, y,
                this.width, this.height);  
        this.x = x;
        this.y = y;
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
        this.x = this.getBounds().x;
        
    }
}
