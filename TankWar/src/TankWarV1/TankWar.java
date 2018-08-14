package TankWarV1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankWar extends Frame {

    public static final int GAME_WIDTH = 500;
    public static final int GAME_HEIGHT = 500;
    int x = 235;
    int y = 470;

    Image vBackground = null;

    public static void main(String[] args) {
        TankWar tankWar = new TankWar();
        tankWar.init();

    }

    public void init(){
        this.setLocation(500,500);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setTitle("TankWar v1.0");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setBackground(Color.white);
        this.setVisible(true);
        new Thread(new PaintThread()).start();
    }

    @Override
    public void paint(Graphics g)
    {
       // super.paint(g);//
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
        g.setColor(c);
        y -= 5;
    }

    public void update(Graphics g){
        if(vBackground == null){
            vBackground = this.createImage(500,500);
        }
        Graphics vg = vBackground.getGraphics();
        Color vc = vg.getColor();
        vg.setColor(Color.white);
        vg.fillRect(0,0,500,500);
        paint(vg);
        g.drawImage(vBackground,0,0,null);

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


