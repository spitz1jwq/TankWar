package TankWarV11;

import java.awt.*;
import java.util.List;

public class Bullet {
    int x,y, width=10,height=10;
    int speed = 10;
    Direction dir; //default dir as the same with the myTank
    TankWar tankWar;
    Tank tank;
    boolean isLive = true ;
    boolean isGood=false;
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
        g.fillOval(x, y, width,height);
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
      if(x < 0 || y < 0 || x>TankWar.GAME_WIDTH-width || y> TankWar.GAME_HEIGHT-height){
            this.isLive = false;
      }
    }


    public void hitTank(List<Tank> tanks){
        Rectangle bulletRect = new Rectangle(x, y, width, height);
        for(int i=0;i<tanks.size();i++) {
            Tank tank = tanks.get(i);
            Rectangle tankRect = tank.getRect();
            boolean intersects = bulletRect.intersects(tankRect);
            if(intersects) {
                if(this.tank.isGood!=tank.isGood) {
                    tank.isLive = false;
                }
            }
        }
    }

}
