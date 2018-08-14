package TankWarV12;
//统计字数，
// TODO:
// 增加生命条。


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankWar extends Frame {

    private static final long serialVersionUID = -4752546809421493001L;

    Image vBackground = null;

    public static final int GAME_WIDTH = 500;
    public static final int GAME_HEIGHT = 400;

    Tank myTank = new Tank(225, 350, 50, 50, Color.RED, this);

    private List<Bullet> bulletArrayList = new ArrayList<Bullet>();

    private List<Tank> EnemyTankArrayList = new ArrayList<>();

    public void addBullet(Bullet b){
        if (null == b) {
            throw new NullPointerException();
        }
        if (null == this.bulletArrayList) {
            bulletArrayList = new ArrayList<Bullet>();
        }
        bulletArrayList.add(b);

        }

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
                super.windowClosing(e);
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setBackground(Color.white);
        this.setVisible(true);
       // this.addKeyListener(new KeyMonitor(this));
        KeyMonitor keyMonitor = new KeyMonitor(this,myTank);
        //直接写上tankWar是不行的， 这里面是传入当前的对象
        addKeyListener(keyMonitor);
        /**让另一个也动起来。*/
//        addKeyListener(new KeyMonitor(this,yourTank));
        for (int i = 0; i < 5; i++) {
            Tank tank = new Tank(50 + (i * 50),55, 50, 50, Color.GREEN,this,false);
            EnemyTankArrayList.add(tank);
        }

        new Thread(new PaintThread()).start();
    }
    @Override
    public void paint(Graphics g)   //绘制tank的方法，拿出来。
    {   super.paint(g);//
        myTank.draw(g);

        //画出坦克(给出限制条件，只有在存活的时候才paint Tank)
        //在画出tank的时候，子弹并没有调用hit方法。

        for (int i = 0; i < EnemyTankArrayList.size(); i++) {
            Tank tank = EnemyTankArrayList.get(i);
            if(tank.isLive) {
                tank.draw(g);
            }else{
                EnemyTankArrayList.remove(i);
            }
        }

        //画出子弹，（给出限制条件 ）
        for (int i = 0; i < bulletArrayList.size(); i++) {
            Bullet bullet = bulletArrayList.get(i);
            if(bullet.isLive) {
                bullet.draw(g);
                bullet.hitTank(EnemyTankArrayList);
            }else{
                bulletArrayList.remove(i);
            }
        }
        Color color = g.getColor();
        g.setColor(Color.BLACK);
        g.drawString("count"  + this.bulletArrayList.size(), 10, 60);
        g.drawString("E_Tank" + this.EnemyTankArrayList.size(), 10, 50);
        g.getColor();

    }


    public void update(){
        if(vBackground == null){
            vBackground = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics vg = vBackground.getGraphics();
        Color vc = vg.getColor();
        vg.setColor(Color.white);
        vg.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        paint(vg);
        vg.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);

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


