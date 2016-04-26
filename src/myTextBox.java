/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joe
 */
import javax.swing.*;
import java.awt.*;

public class myTextBox extends JTextArea{
    int fontSize = 16;
    
    public myTextBox(){
        super();
        this.setBackground(Color.LIGHT_GRAY);
        this.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontSize+8));  
    }
    public myTextBox(int newFontSize){
        super();
        fontSize = newFontSize;
        this.setBackground(Color.LIGHT_GRAY);
        this.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontSize+8));  
    }   
}
