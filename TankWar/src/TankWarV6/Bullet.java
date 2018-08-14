package TankWarV6;

import java.awt.*;

public class Bullet {
    int x,y, width=10,height=10;
    int speed = 10;
    Direction dir; //default dir as the same with the myTank
    TankWar tankWar;
    Tank tank;


    public Bullet(int x, int y, Direction dir) {
        super();
        this.x = x-(width/2);
        this.y = y-(height/2);
        this.dir = dir;
    }
    public Bullet(int x, int y, Direction dir, TankWar tankWar) {
        this(x,y,dir);
        this.tankWar = tankWar;
    }

    public Bullet(int x, int y, Direction dir, TankWar tankWar, Tank tank) {
        this(x,y,dir,tankWar);
        this.tank = tank;
    }

    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 10, 10);
        g.setColor(c);
        move();
    }

    public void move(){
        switch (dir) {
            case L:
                x -= speed;
                break;
            case LU:
                x -= speed;
                y -= speed;
                break;
            case U:
                y -= speed;
                break;
            case RU:
                y -= speed;
                x += speed;
                break;
            case R:
                x += speed;
                break;
            case RD:
                x += speed;
                y += speed;
                break;
            case D:
                y += speed;
                break;
            case LD:
                x -= speed;
                y += speed;
                break;
            default:
                break;
        }

    }
}
