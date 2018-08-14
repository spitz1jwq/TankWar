package TankWarV14;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.Toolkit;

public class Tank {
    private long dirTime;
    private long shotTime;


    int x, y, width, height;
    int speed =5;
    Color color;
    public Direction tankDir= Direction.STOP;
    private Direction[] dirs = new Direction[]{Direction.L, Direction.LU, Direction.U, Direction.RU, Direction.R, Direction.RD, Direction.D, Direction.LD};
    public Direction ptDir= Direction.U;
    public TankWar tw;
    public boolean isGood =true;
    public boolean isLive = true;

    private int life = 100;

    public static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static Image[] images = null;
    public static Map<String, Image> image = new HashMap<String, Image>();

    static {

        images = new Image[] {
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/tankL.gif")),
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/tankLU.gif")),
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/tankU.gif")),
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/tankRU.gif")),
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/tankR.gif")),
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/tankRD.gif")),
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/tankD.gif")),
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/tankLD.gif")),
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/missileL.gif")),
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/missileLU.gif")),
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/missileU.gif")),
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/missileRU.gif")),
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/missileR.gif")),
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/missileRD.gif")),
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/missileD.gif")),
                toolkit.getImage(Tank.class.getClassLoader().getResource("images/missileLD.gif")),
        };
        image.put("L", images[0]);
        image.put("LU", images[1]);
        image.put("U", images[2]);
        image.put("RU", images[3]);
        image.put("R", images[4]);
        image.put("RD", images[5]);
        image.put("D", images[6]);
        image.put("LD", images[7]);
        image.put("L1", images[8]);
        image.put("LU1", images[9]);
        image.put("U1", images[10]);
        image.put("RU1", images[11]);
        image.put("R1", images[12]);
        image.put("RD1", images[13]);
        image.put("D1", images[14]);
        image.put("LD1", images[15]);

    }
    


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

    public Tank(int x, int y, int width, int height, Color color, TankWar tw) {
        this(x, y, width, height, color);
        this.tw=tw;
    }

    public Tank(int x, int y, int width, int height, Color color, TankWar tw, boolean isGood) {
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
//        Color color2 = g.getColor();
//        g.setColor(color);
//        g.fillOval(x, y, width, height);
//        g.setColor(color2);


        switch(ptDir){
            case L:
//				g.drawLine(x+(width/2), y+(height/2), x, y+(height/2));
                g.drawImage(image.get("L"), x,  y, null);
                break;
            case LU:
//				g.drawLine(x+(width/2), y+(height/2), x, y);
                g.drawImage(image.get("LU"), x,  y, null);
                break;
            case U:
//				g.drawLine(x+(width/2), y+(height/2), x+(width/2), y);
                g.drawImage(image.get("U"), x,  y, null);
                break;
            case RU:
//				g.drawLine(x+(width/2), y+(height/2), x+width, y);
                g.drawImage(image.get("RU"), x,  y, null);
                break;
            case R:
//				g.drawLine(x+(width/2), y+(height/2), x+width, y+(height/2));
                g.drawImage(image.get("R"), x,  y, null);
                break;
            case RD:
//				g.drawLine(x+(width/2), y+(height/2), x+width, y+height);
                g.drawImage(image.get("RD"), x,  y, null);
                break;
            case D:
//				g.drawLine(x+(width/2), y+(height/2), x+(width/2), y+height);
                g.drawImage(image.get("D"), x,  y, null);
                break;
            case LD:
//				g.drawLine(x+(width/2), y+(height/2), x, y+height);
                g.drawImage(image.get("LD"), x,  y, null);
                break;
        }


        if(isGood){
            Color color = g.getColor();
            g.setColor(color.RED);
            g.drawRect(x-(width/2), y-20, life, 10);
            g.fillRect(x-(width/2), y-20, this.getLife(), 10);
            g.setColor(color);
        }else{
//            Color color = g.getColor();
//            g.setColor(this.color);
//            g.drawRect(x+(width/2)-100/4, y-20, 100/2, 10);
//            g.fillRect(x+(width/2)-100/4, y-20, this.getLife(), 10);
//            g.drawString("HP��"+this.getLife(), x+(width/2)-100/4,  y-20);
//            g.setColor(color);
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
