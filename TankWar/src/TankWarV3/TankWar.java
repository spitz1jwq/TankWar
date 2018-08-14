package TankWarV3;
// TODO: 2018/8/2 分离出一个tank类。
/**
 * 面向对象中的类，相互调用的时候得创建一个类似引用的东西。
 *再画一个tank
 * */

import javax.swing.*;
import java.awt.*;

public class TankWar extends JFrame {

    public static final int GAME_WIDTH = 500;
    public static final int GAME_HEIGHT = 500;
  /**这边是tank的属性值，单独拿出来**/
//    int x = 235;
//    int y = 470;
    Tank myTank = new Tank(235,470);
    Tank yourTank = new Tank(235,40);

    public static void main(String[] args) {
        TankWar tankWar = new TankWar();
        tankWar.init();
    }

    public void init(){
        this.setLocation(500,500);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setTitle("TankWar v1.0");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.white);
        this.setVisible(true);
// TODO: 2018/8/2 匿名类中的参数传入方式。
       // this.addKeyListener(new KeyMonitor(this));
        KeyMonitor keyMonitor = new KeyMonitor(this,myTank);
        //直接写上tankWar是不行的， 这里面是传入当前的对象
        addKeyListener(keyMonitor);

        /**让另一个也动起来。*/
        addKeyListener(new KeyMonitor(this,yourTank));

        new Thread(new PaintThread()).start();
    }

    @Override
    public void paint(Graphics g)   //绘制tank的方法，拿出来。
    {
        super.paint(g);//
        myTank.draw(g);
        yourTank.draw(g);
//        y -= 5;
    }
    private class PaintThread implements Runnable //采用的内部类的方法，顾使用的时候不能在静态主函数中调用。
    {

        public void run() {
            while(true)
            {
                repaint();
                try
                {
                    Thread.sleep(20);
                }catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

        }

    }
}


