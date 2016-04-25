
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adam
 */
public class scoreKeeper {
    String[] nameList = new String[10];
    int[] scoreList = new int[10];
    String blank = "Name: ------- Date: ------";
    int blankScore = 0;
    int MAX_SCORES_SAVED = 10;
    String saveFile = "HighScores.xml";
    
    
    scoreKeeper() {
        File f = new File(saveFile);
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            setBlank();
        }
    }
    public void setBlank() {
        XML_240 xr = new XML_240();
        xr.openWriterXML(saveFile);
        for (int x = 0; x < MAX_SCORES_SAVED; x++) {
            xr.writeObject(blank);
            xr.writeObject(blankScore);
        }
        xr.closeWriterXML();
    }
    
    public void checkScore() {
      
    }
    
    public String getHighScores() {
        XML_240 xr = new XML_240();
        xr.openReaderXML(saveFile);
        String returnList = ""; 
        for (int x = 0; x < MAX_SCORES_SAVED; x++) {
            nameList[x] = new String();
            nameList[x] = (String) xr.ReadObject();
            scoreList[x] = (int) xr.ReadObject();
            returnList+=(x + ": " + nameList[x] + " Score: " + scoreList[x] + "\n");
        }
        //System.out.print(returnList);
        xr.closeReaderXML();
        return returnList;
    }
        
    
    public void setNewScore(String inName, int tempScore) {
        XML_240 xr = new XML_240();
        getHighScores();
        String tempName = ("Name: " + inName.toUpperCase() + " Date: " + LocalDateTime.now().toLocalDate());
        int writeCount = 0, x = 0;
        xr.openWriterXML(saveFile);
        do {
            if (tempScore >= scoreList[x]) {
                xr.writeObject(tempName);
                xr.writeObject(tempScore);
                writeCount++;
                tempScore = scoreList[x];
                tempName = nameList[x];
            }
            else {
                xr.writeObject(nameList[x]);
                xr.writeObject(scoreList[x]);
                writeCount++;
            }
            x++;
        } while (writeCount < MAX_SCORES_SAVED);
        xr.closeWriterXML();
    }
    
}
