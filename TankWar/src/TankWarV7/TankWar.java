package TankWarV7;
// TODO: 2018/8/2  完善一下命名规范。并且让炮台出现。



import javax.swing.*;
import java.awt.*;

public class TankWar extends JFrame {
    public static final int GAME_WIDTH = 500;
    public static final int GAME_HEIGHT = 400;

    Tank myTank = new Tank(225, 350, 50, 50, Color.RED, this);

    Bullet bullet =null;

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
       // this.addKeyListener(new KeyMonitor(this));
        KeyMonitor keyMonitor = new KeyMonitor(this,myTank);
        //直接写上tankWar是不行的， 这里面是传入当前的对象
        addKeyListener(keyMonitor);
        /**让另一个也动起来。*/
//        addKeyListener(new KeyMonitor(this,yourTank));
        new Thread(new PaintThread()).start();
    }
    @Override
    public void paint(Graphics g)   //绘制tank的方法，拿出来。
    {   super.paint(g);//
        myTank.draw(g);
        //yourTank.draw(g);
        if(null != bullet){
            bullet.draw(g);
        }
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


