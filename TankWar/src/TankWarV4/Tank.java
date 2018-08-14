package TankWarV4;
// TODO: 2018/8/2 进行封装属性。
import java.awt.*;

public class Tank {
    public int x,y;
    int speed = 5;
    public Direction tankDir = Direction.STOP;
    private Direction[] dirs = new Direction[]{Direction.L,Direction.LU,Direction.U,Direction.RU,Direction.R,Direction.RD,Direction.D,Direction.LD};
    boolean LK = false;
    boolean UK = false;
    boolean RK = false;
    boolean DK = false;


    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
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


}
