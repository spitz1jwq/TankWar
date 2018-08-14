package TankWarV15;


import java.awt.*;
import java.util.List;


// TODO: 2018/8/6 增加墙体，使得子弹，等和tank不能从上面传过去。


public  class wall {

    public int x,y,width,height;
    Tank tank;


    public wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g){

        Color c = g.getColor();
        g.setColor(Color.PINK);
        g.fillRect(x,y,width,height);//100,100,10,100
        g.setColor(c);


    }

    //碰撞类，tank和wall之间是否发生类碰撞。

    public void hitTheWall(List<Tank> tanks,List<Shot> shotList){
        Rectangle wallRect = new Rectangle(x,y,width,height);
        for (int i = 0; i < tanks.size(); i++) {
            Tank tank = tanks.get(i);
            Rectangle tankRect = tank.getRect();
            boolean intersects = wallRect.intersects(tankRect);
            if(intersects){
               tank.x = tank.ox;
               tank.y = tank.oy;
            }
        }

        for (int i = 0; i < shotList.size(); i++) {
            Shot shot = shotList.get(i);
            Rectangle shotRect = shot.getRect();
            boolean intersects = wallRect.intersects(shotRect);
            if(intersects){
                shot.isLive = false;
            }

        }
    }




}
