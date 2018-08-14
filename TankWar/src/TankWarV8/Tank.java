package TankWarV8;
// TODO: 2018/8/2  实现按键发射子弹功能， 主要是对象之间的各种引用。把bullet的值创给

import java.awt.*;

public class Tank {
    public int x,y,width,height;
    public Color color;
    int speed = 5;
    public Direction tankDir = Direction.STOP; // 默认的方向！！
    private Direction[] dirs = new Direction[]{Direction.L,Direction.LU,Direction.U,Direction.RU,Direction.R,Direction.RD,Direction.D,Direction.LD};
    public Direction ptDir = Direction.U;



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

    public Tank(int x, int y, int width, int height, TankWar tankWar) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tankWar = tankWar;
    }

    public Tank(int x, int y, int width, int height, Color color, TankWar tankWar) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.tankWar = tankWar;
    }

    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y,width,height);
        g.setColor(c);

        switch(ptDir){
            case L:
                g.drawLine(x+(width/2), y+(height/2), x, y+(height/2));
                break;
            case LU:
                g.drawLine(x+(width/2), y+(height/2), x, y);
                break;
            case U:
                g.drawLine(x+(width/2), y+(height/2), x+(width/2), y);
                break;
            case RU:
                g.drawLine(x+(width/2), y+(height/2), x+width, y);
                break;
            case R:
                g.drawLine(x+(width/2), y+(height/2), x+width, y+(height/2));
                break;
            case RD:
                g.drawLine(x+(width/2), y+(height/2), x+width, y+height);
                break;
            case D:
                g.drawLine(x+(width/2), y+(height/2), x+(width/2), y+height);
                break;
            case LD:
                g.drawLine(x+(width/2), y+(height/2), x, y+height);
                break;
        }

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
        Bullet bullet = new Bullet(x+(width/2), y+(height/2),ptDir,tankWar,this);
        this.tankWar.addBullet(bullet);
    }

}
