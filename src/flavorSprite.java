/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eric
 */
public class flavorSprite extends sprite {

    int xPos;
    boolean xSet = false;

    public flavorSprite(String imagePath) {
        super(imagePath);
    }

    @Override
    public void update() {
        if (!xSet) {
            xPos = (int) (Math.round(Math.random() * (getParent().getSize().width - icon.getIconWidth() / 2)));
            setBounds(xPos, 0, icon.getIconWidth(), icon.getIconHeight());
            xSet = true;
        }
        setPosition(xPos, getBounds().y + 7);
    }
}
