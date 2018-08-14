package TankWarV8;
//统计字数，
// TODO: 2018/8/3 1. 是否越界2.炮弹不消失3.画出敌军tank，4.将敌军击毙 5. 添加多坦克 6. 让敌军随机动   
// add the   List<Bullet> to store the bullets!
// 2. finish the addBullet method to add the bullet to List<bullet> !
//
// use the cycle to draw the bullet !

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TankWar extends JFrame {


    Image offScreenImage = null;

    public static final int GAME_WIDTH = 500;
    public static final int GAME_HEIGHT = 400;

    Tank myTank = new Tank(225, 350, 50, 50, Color.RED, this);

    private List<Bullet> shotList = new ArrayList<Bullet>();


    public void addBullet(Bullet b){
        if (null == b) {
            throw new NullPointerException();
        }
        if (null == this.shotList) {
            shotList = new ArrayList<Bullet>();
        }
        shotList.add(b);
    }


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

        for (int i = 0; i < shotList.size(); i++) {
            Bullet bullet = shotList.get(i);
            bullet.draw(g);
        }
        g.drawString("当前炮弹数：" + this.shotList.size(), 10, 60);
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


