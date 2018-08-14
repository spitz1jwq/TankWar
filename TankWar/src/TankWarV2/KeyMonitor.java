package TankWarV2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class  KeyMonitor  extends KeyAdapter {

    TankWar tankWar;
    public KeyMonitor(TankWar tankWar){
        super();
        this.tankWar = tankWar;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                tankWar.x -= 5;
                break;
            case KeyEvent.VK_UP:
                tankWar.y -= 5;
                break;
            case KeyEvent.VK_RIGHT:
                tankWar.x += 5;
                break;
            case KeyEvent.VK_DOWN:
                this.tankWar.y += 5;
                break;
        }
    }



}
