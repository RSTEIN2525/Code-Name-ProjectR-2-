package Terrain.platform;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import Entities.Hitboxes.Hitbox;

public class Platform 
{
	int x;
	int y;
	int width;
	int height;
	Hitbox h; 
	public Platform(int x, int y, int width, int height) 
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		h =  new Hitbox(x,y,width,height,"Platform", null);
	}
	
	public void render(Graphics g) 
	{
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
		if(h!= null)
			h.render(g);
	}
	
	public void update(GameContainer gc) 
	{
		h.setPosition(x, y);
	}
	
	public Hitbox getHitbox()
	{
		return h;
	}
	
	public int getX() 
	{
		return x;
	}
	
	public int getY() 
	{
		return y;
	}
	
	
	
}
