package TankWarV13;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankKeyListener implements KeyListener {

    Tank myTank;
    TankWar tw;

    public TankKeyListener(Tank myTank, TankWar tw) {
        super();
        this.myTank = myTank;
        this.tw = tw;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                tw.UK = true;
                break;
            case KeyEvent.VK_DOWN:
                tw.DK = true;
                break;
            case KeyEvent.VK_LEFT:
                tw.LK = true;
                break;
            case KeyEvent.VK_RIGHT:
                tw.RK = true;
                break;
            case KeyEvent.VK_CONTROL:
                if (myTank.isLive){ // fix 了自己dead后依然能够fire的bug。
                    myTank.fire();
                }
                break;
            default:
                break;
        }
        changeDir();
    }

    private void changeDir() {
        if (tw.UK && !tw.RK && !tw.DK && !tw.LK) {
            myTank.tankDir = Direction.U;
        } else if (tw.UK && tw.RK && !tw.DK && !tw.LK) {
            myTank.tankDir = Direction.RU;
        } else if (!tw.UK && tw.RK && !tw.DK && !tw.LK) {
            myTank.tankDir = Direction.R;
        } else if (!tw.UK && tw.RK && tw.DK && !tw.LK) {
            myTank.tankDir = Direction.RD;
        } else if (!tw.UK && !tw.RK && tw.DK && !tw.LK) {
            myTank.tankDir = Direction.D;
        } else if (!tw.UK && !tw.RK && tw.DK && tw.LK) {
            myTank.tankDir = Direction.LD;
        } else if (!tw.UK && !tw.RK && !tw.DK && tw.LK) {
            myTank.tankDir = Direction.L;
        } else if (tw.UK && !tw.RK && !tw.DK && tw.LK) {
            myTank.tankDir = Direction.LU;
        } else if (!tw.UK && !tw.RK && !tw.DK && !tw.LK) {
            myTank.tankDir = Direction.STOP;
        }
        if (Direction.STOP != myTank.tankDir) {
            myTank.ptDir = myTank.tankDir;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                tw.UK = false;
                break;
            case KeyEvent.VK_DOWN:
                tw.DK = false;
                break;
            case KeyEvent.VK_LEFT:
                tw.LK = false;
                break;
            case KeyEvent.VK_RIGHT:
                tw.RK = false;
                break;
            default:
                break;
        }
        this.changeDir();
    }

}
