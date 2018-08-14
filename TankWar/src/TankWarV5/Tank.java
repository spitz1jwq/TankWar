package TankWarV5;
// TODO: 2018/8/2  实现按键发射子弹功能， 主要是对象之间的各种引用。把bullet的值创给

import java.awt.*;
public class Tank {
    public int x,y,width,height;
    int speed = 5;
    public Direction tankDir = Direction.STOP; // 默认的方向！！
    private Direction[] dirs = new Direction[]{Direction.L,Direction.LU,Direction.U,Direction.RU,Direction.R,Direction.RD,Direction.D,Direction.LD};
    boolean LK = false;
    boolean UK = false;
    boolean RK = false;
    boolean DK = false;

    TankWar tankWar;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Tank(int x, int y, TankWar tankWar) {
        this(x,y);
        this.tankWar = tankWar;
    }
    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
        g.setColor(c);
        move();
    }

    public void move(){
        switch (tankDir){
            case L:
                x-=speed;
                break;
            case LU:
                x-=speed;
                y-=speed;
                break;
            case U:
                y-=speed;
                break;
            case RU:
                y-=speed;
                x+=speed;
                break;
            case R:
                x+=speed;
                break;
            case RD:
                x+=speed;
                y+=speed;
                break;
            case D:
                y+=speed;
                break;
            case LD:
                x-=speed;
                y+=speed;
                break;
            default :
                break;
        }
    }

    public void fire(){
        // 传出bullet给 tankWar ！
        Bullet bullet = new Bullet(x+(width/2), y+(height/2),tankDir,tankWar);
        tankWar.bullet = bullet;
    }

}
