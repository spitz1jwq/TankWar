package TankWarV15;

import java.awt.*;
import java.util.List;

public class Shot {
    int x, y, width=10, height=10;
    int speed=10;
    Direction dir;
    TankWar tw ;
    Tank t;
    boolean isLive =true;
    boolean isGood=false;


    public Shot(int x, int y, Direction dir) {
        super();
        this.x = x-(width/2);
        this.y = y-(height/2);
        this.dir = dir;
    }
    public Shot(int x, int y, Direction dir, TankWar tw) {
        this(x, y, dir);
        this.tw=tw;
    }
    public Shot(int x, int y, Direction dir, TankWar tw, Tank t) {
        this(x, y, dir, tw);
        this.t=t;
        this.isGood = t.isGood;
    }

    public void draw(Graphics g) {



        switch(dir){
            case L:
                g.drawImage(t.image.get("L1"), x,  y, null);
                break;
            case LU:
                g.drawImage(t.image.get("LU1"), x,  y, null);
                break;
            case U:
                g.drawImage(t.image.get("U1"), x,  y, null);
                break;
            case RU:
//			g.drawLine(x+(width/2), y+(height/2), x+width, y);
                g.drawImage(t.image.get("RU1"), x,  y, null);
                break;
            case R:
                g.drawImage(t.image.get("R1"), x,  y, null);
                break;
            case RD:
                g.drawImage(t.image.get("RD1"), x,  y, null);
                break;
            case D:
                g.drawImage(t.image.get("D1"), x,  y, null);
                break;
            case LD:
                g.drawImage(t.image.get("LD1"), x,  y, null);
                break;
        }






        move();
    }

    public void move() {
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

        if(x<=0 || y<=27 || x>=(TankWar.WIDTH-width) || y>=(TankWar.HEIGHT-height)){
            this.isLive=false;
        }
    }

    public void hitTank(List<Tank> tanks){

        Rectangle shotRect = new Rectangle(x, y, width, height);
        for(int i=0;i<tanks.size();i++) {
            Tank tank = tanks.get(i);
            Rectangle tankRect = tank.getRect();
            boolean intersects = shotRect.intersects(tankRect);
            if(intersects  && this.isGood != tank.isGood) {
                this.isLive = false;
                if(tank.isGood){
                    tank.setLife(tank.getLife()-20);
                    if(tank.getLife()<=0){
                        tank.isLive = false;
                    }
                }
                else{
                    tank.isLive = false;
                }

            }
        }

    }





    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        return rectangle;
    }

}
//然后在碰撞内进行详细的划分。在tank里面通过isGood 来划分。

