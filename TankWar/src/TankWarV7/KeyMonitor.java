package TankWarV7;
/**这里只进行更改方向的操作，通过按键来判断当前的方向。**/

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class  KeyMonitor  extends KeyAdapter {

    TankWar tankWar;
    Tank myTank;
    public KeyMonitor(TankWar tankWar, Tank myTank){
        super();
        this.tankWar = tankWar;
        this.myTank = myTank;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_UP:
                myTank.UK = true;
                break;
            case KeyEvent.VK_DOWN:
                myTank.DK = true;
                break;
            case KeyEvent.VK_LEFT:
                myTank.LK = true;
                break;
            case KeyEvent.VK_RIGHT:
                myTank.RK = true;
                break;

            case KeyEvent.VK_CONTROL:
                myTank.fire();
                break;
            default:
                break;
        }
        changeDir();
    }

    private void changeDir() {
        if (myTank.UK && !myTank.RK && !myTank.DK && !myTank.LK) {
            myTank.tankDir = Direction.U;
        } else if (myTank.UK && myTank.RK && !myTank.DK && !myTank.LK) {
            myTank.tankDir = Direction.RU;
        } else if (!myTank.UK && myTank.RK && !myTank.DK && !myTank.LK) {
            myTank.tankDir = Direction.R;
        } else if (!myTank.UK && myTank.RK && myTank.DK && !myTank.LK) {
            myTank.tankDir = Direction.RD;
        } else if (!myTank.UK && !myTank.RK && myTank.DK && !myTank.LK) {
            myTank.tankDir = Direction.D;
        } else if (!myTank.UK && !myTank.RK && myTank.DK && myTank.LK) {
            myTank.tankDir = Direction.LD;
        } else if (!myTank.UK && !myTank.RK && !myTank.DK && myTank.LK) {
            myTank.tankDir = Direction.L;
        } else if (myTank.UK && !myTank.RK && !myTank.DK && myTank.LK) {
            myTank.tankDir = Direction.LU;
        } else if (!myTank.UK && !myTank.RK && !myTank.DK && !myTank.LK) {
            myTank.tankDir = Direction.STOP;
        }if (Direction.STOP != myTank.tankDir) {
            myTank.ptDir = myTank.tankDir;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                myTank.UK = false;
                break;
            case KeyEvent.VK_DOWN:
                myTank.DK = false;
                break;
            case KeyEvent.VK_LEFT:
                myTank.LK = false;
                break;
            case KeyEvent.VK_RIGHT:
                myTank.RK = false;
                break;
            default:
                break;
        }
        this.changeDir();
    }



}
