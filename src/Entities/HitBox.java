package Entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Hitbox {
    
	private float x;
    private float y;
    private float width;
    private float height;
    private String type = "";
    float xVel; float yVel; float xDistance; float yDistance; float startingX; float startingY;

    public Hitbox(float x, float y, float width, float height, String type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }
    
    public void editDimensions(int width, int height) 
    {
    	this.width = width;
    	this.height = height;
    }

    public boolean intersects(Hitbox other) {
        // Check for intersection between this hitbox and another hitbox
        return x < other.x + other.width && x + width > other.x &&
                y < other.y + other.height && y + height > other.y;
    }
    
    public void setVelocityPath(float xVel, float yVel, float xDistance, float yDistance, float startingX, float startingY) 
    {
    	this.xVel = xVel;
    	this.yVel = yVel;
    	this.xDistance = xDistance;
    	this.yDistance = yDistance;
    	this.startingX = startingX;
    	this.startingY = startingY;
    }
    
    public void wipeVelocityPath() 
    {
    	this.xVel = 0;
    	this.yVel = 0;
    	this.xDistance = 0;
    	this.yDistance = 0;
    	this.startingX = 0;
    	this.startingY = 0;
    }
    
    public void update() 
    {
    	
    	if(x < startingX + xDistance) // going to need to pass player last direction to deal w/ directional inputs
    		x += xVel;
    	if(y > startingY + xDistance)
    		y-= yVel;
    }
    
    public void setVelocity(float x) 
    {
    	this.x += x;
    }
    
    public Side getCollisionSide(Hitbox other) {
        float dx = (x + width / 2f) - (other.x + other.width / 2f);
        float dy = (y + height / 2f) - (other.y + other.height / 2f);
        float widthSum = (width + other.width) / 2f;
        float heightSum = (height + other.height) / 2f;

        float crossWidth = widthSum * dy;
        float crossHeight = heightSum * dx;

        if (Math.abs(dx) <= widthSum && Math.abs(dy) <= heightSum) {
            if (crossWidth > crossHeight) {
                return (crossWidth > -crossHeight) ? Side.BOTTOM : Side.LEFT;
            } else {
                return (crossWidth > -crossHeight) ? Side.RIGHT : Side.TOP;
            }
        }

        return Side.NONE;
    }

    public enum Side {
        TOP, BOTTOM, LEFT, RIGHT, NONE
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void render(Graphics g) 
    {
    	g.setColor(Color.green);
    	g.drawRect(x , y, width, height);
    }
    
    public String getType() 
	{
		return type;
	}
    
    public int getX() 
	{
		return (int) x;
	}
	
	public int getY() 
	{
		return (int) y;
	}
	
	public int getHeight() 
	{
		return (int) height;
	}
	
	public int getWidth() 
	{
		return (int) width;
	}
	
	
    // Getters and Setters for other properties (x, y, width, height) as needed
}
