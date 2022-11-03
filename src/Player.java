import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Rectangle {

    int playerID;
    int speed = 7;
    KeyHandler keyH;

    public Player(int x, int y, int width, int height, int playerID, KeyHandler keyH){
        super(x,y,width,height);
        this.playerID = playerID;
        this.keyH = keyH;
    }

    public void input(){
        switch (playerID){
            case 1:
                if(keyH.upP1 == true) {
                    y -= speed;
                }
                else if(keyH.downP1 == true){
                    y += speed;
                }
                break;
            case 2:
                if(keyH.upP2 == true) {
                    y -= speed;
                }
                else if(keyH.downP2 == true){
                    y += speed;
                }
                break;
        }
    }

    public void draw(Graphics g){
        if(playerID == 1){
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.red);
        }
        g.fillRect(x, y, width, height);
    }
}
