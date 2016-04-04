public class coneSprite extends sprite{
    int speed = 100;
    
    coneSprite() {
        super();
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
        
        setPosition(this.x + delta, this.y);
        this.x = this.getBounds().x;
        
    }
}
