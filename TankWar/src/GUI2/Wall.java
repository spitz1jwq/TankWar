package GUI2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall {
	
	
	int Wall_x,Wall_y,Wall_width,Wall_Height;
	TankWar tankWar;
	
	public Wall(int wall_x, int wall_y, int wall_width, int wall_Height, TankWar tankWar) {
		super();
		Wall_x = wall_x;
		Wall_y = wall_y;
		Wall_width = wall_width;
		Wall_Height = wall_Height;
		this.tankWar = tankWar;
	}
	
	
	public  void draw(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.WHITE);
		g.fillRect(Wall_x, Wall_y, Wall_width, Wall_Height);
		g.setColor(color);
	}
	
	public Rectangle getRec() {
		return  new Rectangle(Wall_x, Wall_y, Wall_width, Wall_Height);
	}
	
}
