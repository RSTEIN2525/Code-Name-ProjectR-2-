package Entities.Hitboxes;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Entities.Player;

public class Hitbox {
    
	private float x;
    private float y;
    private float width;
    private float height;
    private float radius;
    private String type = "";
    float xVel; float yVel; float xDistance; float yDistance; float startingX; float startingY;
    float xExpansion; float yExpansion; float maxRadius; float yExpansionMax;
    Player p;
    float startingWidth; float startingExpansionY;
    boolean isCircleHitbox;
    boolean radiusIsGrowing;
    public int duration;
    
    public Hitbox(float x, float y, float width, float height, String type, Player owner, int duration) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        p = owner;
        isCircleHitbox = false;
        this.duration = duration;
    }
    
    public Hitbox(float x, float y, float radius, String type, Player owner) 
	{
    	this.x = x;
    	this.y = y;
    	this.radius = radius;
    	 this.type = type;
         p = owner;
         isCircleHitbox = true;
	}
    
    public int getDuration() 
    {
    	return duration;
    }
    
    public boolean durationExpired() 
    {
    	return duration == 0;
    }
    
    public void editDimensions(int width, int height, int radius) 
    {
    	if(!isCircleHitbox) {
    	this.width = width;
    	this.height = height;
    	}else
    	this.radius = radius;
    }

    public boolean intersects(Hitbox other) {
        // Check for intersection between this hitbox and another hitbox
        return x < other.x + other.width && x + width > other.x &&
                y < other.y + other.height && y + height > other.y;
    }
    
    public void setPlayer(Player p) 
    {
    	
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
    
    public void setExpansion(float xExpansion, float yExpansion, float maxRadius, 
    		int curWidth) 
    {
    	this.xExpansion = xExpansion;
    	this.yExpansion = yExpansion;
    	this.maxRadius = maxRadius;
    	radius = curWidth;
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
    	
    	if(duration > 0) 
    	{
    		duration --;
    	}
    	
    	
    	if(p.getPlayerDirection().equalsIgnoreCase("Right"))
    	{
    	if(x < startingX + xDistance) // going to need to pass player last direction to deal w/ directional inputs
    		x += xVel;
    	}else 
    	{
    		if(x > startingX - xDistance);
    		x-= xVel;
    	}
    	
    	if(y > startingY - yDistance)
    		y+= yVel;
    	
    	if(isCircleHitbox && radius < maxRadius) // don't need yExpansion max for greatSword Attack 
    	{
    		radius += xExpansion;
    		x -= xExpansion/2;
    		y -= xExpansion/2;
    		radiusIsGrowing = true;
    	}else 
    	{
    		radiusIsGrowing = false;
    	}
    		
    	
    	
    	
    	
    }
    
    public void setVelocity(float x, float y) 
    {
    	this.xVel = x;
    	this.yVel = y;
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
    
    public Side getCollisionSideRectOnCircle(boolean isColliding) {
       
    	if(p.getPlayerDirection().equalsIgnoreCase("Right") && isColliding)
    		return Side.LEFT;
    	else return Side.RIGHT;
    }

    	public boolean circleRectangleCollision(Hitbox h) {
    	   
    		
    		// off by half of the player
    		
    		
    		float centerCircleX = x + radius/2;
    		float centerCircleY = y+ radius/2;
    		
    		float circleDistanceX = Math.abs(centerCircleX - h.getX() - h.getWidth() / 2);
    		float circleDistanceY = Math.abs(centerCircleY - h.getY() - h.getHeight() / 2);
    		
    		if(circleDistanceX > (h.getWidth()/2 + radius/2))
    			return false;
    		if(circleDistanceY > (h.getHeight()/2 + radius/2))
    			return false;
    		if(circleDistanceX <= h.getWidth())
    			return true;
    		if(circleDistanceY <= h.getHeight())
    			return true;
    		
    		float cornerDistance_sq = (circleDistanceX - h.getWidth()/2) * (circleDistanceX - h.getWidth()/2) 
    				+ (circleDistanceY - h.getHeight()/2) * (circleDistanceY - h.getHeight()/2);
    		
    		return cornerDistance_sq <= ((radius) * (radius));
    		
    		
//    			float offsetX = 0;
//        	    float offsetY = 0;
//    		
//        	    if(radiusIsGrowing) 
//        		{    	
//        	    	if(!p.getPlayerDirection().equalsIgnoreCase("Left"))
//        	    	{
//        	    		offsetX = 64;
//            			offsetY = 64;
//        	    	}else 
//        	    	{
//        	    		offsetX = radius;
//            			offsetY = radius;
//        	    	}
//        		}
//    		
//
//    	    // Find the closest point on the rectangle to the center of the circle
//    	    float closestX = Math.max(h.getX(), Math.min(x + offsetX, h.getX() + h.getWidth()));
//    	    float closestY = Math.max(h.getY(), Math.min(y + offsetY, h.getY() + h.getHeight()));
//
//    	    // Adjust the closest point to be on the edge of the rectangle if the circle is larger
//    	    closestX = Math.min(closestX, h.getX() + h.getWidth());
//    	    closestY = Math.min(closestY, h.getY() + h.getHeight());
//
//    	    // Calculate the distance between the closest point and the circle's center
//    	    float distanceX = (x + offsetX) - closestX;
//    	    float distanceY = (y + offsetY) - closestY;
//    	    float distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);
//
//    	    // Check if the distance squared is less than the square of the circle's radius
//    	    return distanceSquared <= (radius * radius);
    	}

    





    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void render(Graphics g) 
    {
    	g.setColor(Color.green);
    	
    	
    	
    	
    	if(!isCircleHitbox)
    	g.drawRect(x , y, width, height);
    	else g.drawOval(x, y, radius, radius);
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
	
	public int getXVel() 
	{
		return (int) xVel;
	}
	
	public int getYVel() 
	{
		return (int) yVel;
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
