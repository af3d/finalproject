public class coneSprite extends sprite{

    coneSprite(String imagePath) {
        super(imagePath);
        setBounds(icon.getIconWidth() / 2, 0,
                icon.getIconWidth(), icon.getIconHeight());
        
    }

    @Override
    public void update() {
        mouseLoc = getParent().getMousePosition();
        if (mouseLoc == null)
            return;
        int yPos = getParent().getSize().height - icon.getIconHeight();
        int delta = 0;
        delta = mouseLoc.x - (getBounds().x + (getBounds().width / 2));
        if (delta > 30) {
            delta = 30;
        }
        if (delta < -30) {
            delta = -30;
        }
        setPosition(getBounds().x + delta, yPos);
    }
}
