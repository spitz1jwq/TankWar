package TankWarV13;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
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
    public Shot(int x, int y, Direction dir,TankWar tw) {
        this(x, y, dir);
        this.tw=tw;
    }
    public Shot(int x, int y, Direction dir,TankWar tw,Tank t) {
        this(x, y, dir, tw);
        this.t=t;
        this.isGood = t.isGood;
    }

    public void draw(Graphics g) {
        Color color2 = g.getColor();
        g.setColor(Color.BLACK);
        g.fillOval(x, y, width, height);
        g.setColor(color2);
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
}
//然后在碰撞内进行详细的划分。在tank里面通过isGood 来划分。

