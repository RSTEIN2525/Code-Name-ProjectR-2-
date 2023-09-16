package Entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import Entities.Hitbox.Side;

public class Dummy extends Entity
{
	int x;
	int y;
	int w;
	int h;
	int health = 100;
	Hitbox hitbox;
	
	
	public Dummy(int x, int y, int w, int h) 
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		hitbox = new Hitbox(x,y,w,h,"Player");
	}
	
	public void render(Graphics g) 
	{
		g.setColor(Color.red);
		g.fillRect(x, y, w, h);
		g.setColor(Color.white);
		g.drawString("" + health, x, y - 20);	
		hitbox.render(g);
		
	}
	
	public void setDamage(int i) 
	{
		health -= i;
	}
	
	public void update(GameContainer gc) 
	{
		hitbox.setPosition(x, y);
	}
	
	public Hitbox getHitbox()
	{
		return hitbox;
	}
	
	public float getX() 
	{
		return x;
	}
	
	public float getY() 
	{
		return y;
	}
	
	public float getWidth() 
	{
		return w;
	}
	
	public float getHeight() 
	{
		return h;
	}

	@Override
	public void setKnockback(float i, Side s) {
		
		if(s == Side.LEFT) 
		{
			x += i;
			System.out.println("Trigger");
		}
			
		if(s == Side.RIGHT) 
		{
			x -= i;
		}
		
	}
}
