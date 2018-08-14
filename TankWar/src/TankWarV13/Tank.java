package TankWarV13;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tank {
    private long dirTime;
    private long shotTime;


    int x, y, width, height;
    int speed =5;
    Color color;
    public Direction tankDir=Direction.STOP;
    private Direction[] dirs = new Direction[]{Direction.L,Direction.LU,Direction.U,Direction.RU,Direction.R,Direction.RD,Direction.D,Direction.LD};
    public Direction ptDir=Direction.U;
    public TankWar tw;
    public boolean isGood =true;
    public boolean isLive = true;

    private int life = 100;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Tank(int x, int y, int width, int height, Color color) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color=color;
    }

    public Tank(int x, int y, int width, int height,Color color,TankWar tw) {
        this(x, y, width, height, color);
        this.tw=tw;
    }

    public Tank(int x, int y, int width, int height,Color color,TankWar tw,boolean isGood) {
        this(x, y, width, height, color, tw);
        this.isGood=isGood;
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
        switch(tankDir){
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

        if(x<=0){
            x=0;
        }
        if(y<=27){
            y=27;
        }
        if(x>=(TankWar.WIDTH-width)){
            x=(TankWar.WIDTH-width);
        }
        if(y>=(TankWar.HEIGHT-height)){
            y=(TankWar.HEIGHT-height);
        }
    }

    public void draw(Graphics g){
        Color color2 = g.getColor();
        g.setColor(color);
        g.fillOval(x, y, width, height);
        g.setColor(color2);

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
        if(isGood){
            Color color = g.getColor();
            g.setColor(color);
            g.drawRect(x-(width/2), y-20, life, 10);
            g.fillRect(x-(width/2), y-20, this.getLife(), 10);
            g.setColor(color);
        }else{
            Color color = g.getColor();
            g.setColor(this.color);
            g.drawRect(x+(width/2)-100/4, y-20, 100/2, 10);
            g.fillRect(x+(width/2)-100/4, y-20, this.getLife(), 10);
            g.drawString("HP��"+this.getLife(), x+(width/2)-100/4,  y-20);
            g.setColor(color);
        }

        move();
    }

    public void fire(){
        Shot shot = new Shot(x+(width/2), y+(height/2), ptDir,tw,this);
        this.tw.addShot(shot);
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        return rectangle;
    }
}
