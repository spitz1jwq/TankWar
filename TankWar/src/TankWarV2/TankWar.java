package TankWarV2;
// TODO: 2018/8/2 完成了让tank移动功能具体实现
/**
 * 在里面添加了监听器，线程重绘repaint 等方法！
 * */

import javax.swing.*;
import java.awt.*;

public class TankWar extends JFrame {

    public static final int GAME_WIDTH = 500;
    public static final int GAME_HEIGHT = 500;
    int x = 235;
    int y = 470;

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
        KeyMonitor keyMonitor = new KeyMonitor(this);
        //直接写上tankWar是不行的， 这里面是传入当前的对象
        addKeyListener(keyMonitor);
        new Thread(new PaintThread()).start();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);//
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
        g.setColor(c);
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


