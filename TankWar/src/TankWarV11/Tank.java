package TankWarV11;

import java.awt.*;
import java.util.Random;

public class Tank {

    private long dirTime;
    private long shotTime;
    public int x,y,width,height;
    public Color color;
    int speed = 5;
    public Direction tankDir = Direction.STOP;
    private Direction[] dirs = new Direction[]{Direction.L,Direction.LU,Direction.U,Direction.RU,Direction.R,Direction.RD,Direction.D,Direction.LD};
    public Direction ptDir = Direction.U;

    public boolean isLive = true;
    public boolean isGood = true;

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
    public Tank(int x, int y, int width, int height, Color color, TankWar tankWar, boolean isGood) {
        this(x, y, width, height, color, tankWar);
        this.isGood=isGood;
    }


    public void draw(Graphics g){

        Color c = g.getColor();
        g.setColor(color); //获取tank的形参。
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

        if(!isGood){
            long currentTimeMillis = System.currentTimeMillis();
            if(currentTimeMillis-dirTime>3000){
                Random random = new Random();
                int nextInt = random.nextInt(8);
                tankDir =dirs[nextInt];
                ptDir = tankDir;
                dirTime=currentTimeMillis;
            }
            if(currentTimeMillis-shotTime>2000){
                this.fire();
                shotTime=currentTimeMillis;
            }
        }

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

        // 防止tank出界。
        if(x<0){
            x = 0 ;
        }else if (x>TankWar.GAME_WIDTH-width)
        {
            x = TankWar.GAME_WIDTH-width;
        }else if (y<height-13){
            y = height-13;
        }else if (y>TankWar.GAME_HEIGHT-height){
            y = TankWar.GAME_HEIGHT - height;
        }


    }

    public void fire(){
        // 传出bullet给 tankWar ！
        Bullet bullet = new Bullet(x+(width/2), y+(height/2),ptDir,tankWar,this);
        this.tankWar.addBullet(bullet);
    }

    //碰撞类检测技术,
    public Rectangle getRect(){
        Rectangle rectangle = new Rectangle(x, y, width, height);
        return rectangle;
    }

}
