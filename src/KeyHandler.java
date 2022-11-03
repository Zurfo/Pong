import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upP1, downP1, upP2, downP2, rest;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upP1 = true;
        }
        if(code == KeyEvent.VK_S){
            downP1 = true;
        }

        if(code == KeyEvent.VK_I){
            upP2 = true;
        }
        if(code == KeyEvent.VK_K) {
            downP2 = true;
        }

        if (code == KeyEvent.VK_R) {
            rest = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upP1 = false;
        }
        if(code == KeyEvent.VK_S){
           downP1 = false;
        }

        if(code == KeyEvent.VK_I){
            upP2 = false;
        }
        if(code == KeyEvent.VK_K) {
            downP2 = false;
        }

        if (code == KeyEvent.VK_R) {
            rest = false;
        }
    }
}
