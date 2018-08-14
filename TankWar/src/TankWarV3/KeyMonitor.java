package TankWarV3;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class  KeyMonitor  extends KeyAdapter {

    TankWar tankWar;
    Tank myTank;
    public KeyMonitor(TankWar tankWar,Tank myTank){
        super();
        this.tankWar = tankWar;
        this.myTank = myTank;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
               myTank.x -= 5;
                break;
            case KeyEvent.VK_UP:
                myTank.y -= 5;
                break;
            case KeyEvent.VK_RIGHT:
                myTank.x += 5;
                break;
            case KeyEvent.VK_DOWN:
                myTank.y += 5;
                break;
        }
    }



}
