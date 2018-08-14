package TankWarV3;
import java.awt.*;

public class Tank {
    public int x,y;

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
        x-=5;
    }


}
