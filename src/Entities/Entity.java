package Entities;

import Entities.Hitboxes.Hitbox;
import Entities.Hitboxes.Hitbox.Side;

public abstract class Entity 
{
	float x;
	float y;
	float w;
	float h;
	
	public Entity() 
	{
		
	}
	
	public abstract Hitbox getHitbox(); 
	public abstract void setDamage(int i);
	public abstract void setKnockback(float i, Side s);
	
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
}
