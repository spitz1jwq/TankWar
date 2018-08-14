package TankWarV13;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

// Done : 2018/8/5  完善了碰撞检测， 主要检测的是子弹和tank之间是否发生了碰撞，将自己的mytank也放进enemyTank 中。
//增加了血条显示。修复了打击敌方不能击毙的问题。

public class TankWar extends Frame {
    private static final long serialVersionUID = -4752546809421493001L;
    /** 战场宽度 ***/
    public static final int WIDTH = 500;
    /** 战场高度 **/
    public static final int HEIGHT = 400;

    /** 左光标键 */
    boolean LK = false;
    /** 上光标键 */
    boolean UK = false;
    /** 右光标键 */
    boolean RK = false;
    /** 下光标键 */
    boolean DK = false;

    /** 解放军坦克 **/
    Tank myTank = new Tank(225, 350, 50, 50, Color.RED, this,true);
    /** 敌机的集合 */
    private List<Tank> enemyTank = new ArrayList<Tank>();
    /** 子弹集合 **/
    private List<Shot> shotList = new ArrayList<Shot>();

    /**
     * 程序主方法
     *
     * @param args
     */
    public static void main(String[] args) {
        TankWar tankWar = new TankWar();
        tankWar.init();

        RePaintStar rePaintStar = new RePaintStar(tankWar);
        rePaintStar.start();
    }

    /**
     * 战场初始化
     *
     */
    private void init() {
        setBounds(100, 100, WIDTH, HEIGHT);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        setResizable(false);
        setTitle("TankWar-V1.0");
        TankKeyListener tkl = new TankKeyListener(myTank, this);
        addKeyListener(tkl);

        for (int i = 0; i < 5; i++) {
            Tank tank = new Tank(50 + (i * 50), 45, 50, 50, Color.GREEN, this,
                    false);
            enemyTank.add(tank);
        }
      enemyTank.add(myTank);
        setVisible(true);
    }

    /***
     * 添加一枚炮弹
     *
     * @param s
     *            打出的炮弹
     */
    public void addShot(Shot s) {
        if (null == s) {
            throw new NullPointerException();
        }
        if (null == this.shotList) {
            shotList = new ArrayList<Shot>();
        }
        shotList.add(s);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
      //  myTank.draw(g);
        // 画敌人坦克
        for (int i = 0; i < enemyTank.size(); i++) {
            Tank tank = enemyTank.get(i);
            if (tank.isLive) {
                tank.draw(g);
            } else {
                enemyTank.remove(i);
            }
        }

        // 画炮弹
        for (int i = 0; i < shotList.size(); i++) {
            Shot shot = shotList.get(i);
            if (shot.isLive) {
                shot.draw(g);
                shot.hitTank(enemyTank);
            } else {
                shotList.remove(i);
            }

        }
        Color color = g.getColor();
        g.setColor(Color.BLACK);
        g.drawString("EnemyTank" + this.enemyTank.size(), 10, 40);
        g.drawString("COUNT" + this.shotList.size(), 10, 60);
        g.drawString("life"+myTank.getLife(),10,70);
        g.setColor(color);
    }
}

class RePaintStar extends Thread {
    Frame frame;

    public RePaintStar(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (frame) {
                frame.repaint();
            }
        }
    }
}
