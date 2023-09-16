package Entities.Objects;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Entities.Hitbox;
import Entities.Player;
import core.Main;

public class GreatSword extends Weapon{

	 private int sweepArea;
	 public static int GREATSWORD_RANGE = 80;
	 public static int GREATSWORD_HEAVY_COOLDOWN = 120;
	 public static int GREATSWORD_LIGHT_COOLDOWN = 40;
	 public static int GREATSWORD_UP_COOLDOWN = 100; // land before reset
	 public static int GREATSWORD_DOWN_COOLDOWN = 60; //land before reset
	 public static int GREATSWORD_HEAVY_DAMAGE =  5;
	 public static int GREATSWORD_LIGHT_DAMAGE = 5;
	 public static int GREATSWORD_UP_DAMAGE = 5 ; // land before reset
	 public static int GREATSWORD_DOWN_DAMAGE =  5 ; //land before reset
	 public static int GREATSWORD_KNOCKBACK = 10;
	 float x;
	 float y;
	 float slashX;
	 float slashY;
	 int w = 30;
	 int h = 100;
	 Hitbox hitbox = new Hitbox(x,y,w,h,"Weapon");
	 Hitbox projectileHitbox = new Hitbox(x,y,w,h,"Weapon");
	 Player p;
	 boolean attacking = false;
	 
	 ArrayList<Hitbox> projectileHitboxes = new ArrayList <Hitbox>();
	 
	 
	 
	   
	 
	 
	 String name = "GreatSword";
	    public GreatSword() {
	        super("GreatSword", 0, GREATSWORD_RANGE, GREATSWORD_HEAVY_COOLDOWN);
	        this.sweepArea = sweepArea;
	    }
	    
	    public void setPlayer(Player p) 
	    {
	    	this.p = p;
	    }

	    @Override
	    
	    
	    
	    
	    
	    public void upAttack() {
	       
	        setCooldown(30);
	        setDamage(GREATSWORD_UP_DAMAGE);
	        
	        
	        projectileHitboxes.add(new Hitbox(p.getX(), p.getY() + (p.getHeight() / 3),32, 32, "Weapon"));
	        
	        for(int i = 0; i < projectileHitboxes.size(); i ++) 
	        {
	        	projectileHitboxes.get(i).setVelocityPath(10, 10,  distanceToEndStage() , 280, p.getX(), p.getY());
	        }
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        

	        // Calculate the hitbox dimensions
	      
//	        projectileHitbox.setPosition(p.getX(), p.getY() + (p.getHeight() / 3));
//	        projectileHitbox.editDimensions(32, 32);
//	     
//	        projectileHitbox.setVelocityPath(10, 10,  distanceToEndStage() , 280, p.getX(), p.getY());
//	        
//	        for(int i = 0; i < p.getPlatforms().size(); i ++) 
//	        {
//	        	
//	        	if(p != null && p.getPlatforms() != null) 
//	        	{
//	        		if(projectileHitbox.intersects(p.getPlatforms().get(i).getHitbox()) && p.getPlatforms().get(i).getHitbox().getType().equalsIgnoreCase("Platform")) 
//	        		{
//	        			projectileHitbox.wipeVelocityPath();
//	    	    		projectileHitbox.setPosition(p.getX(), p.getY());
//	        		}
//	        	}
//	        }
//	        
	        
	        
	       
	        
	        
	    }
	    
	    
	    public float distanceToEndStage() 
	    {
	    	return Math.abs((Main.getScreenWidth() - 32) - (p.getX() + p.getWidth()));
	    }





	    
	    
	    public void lightAttack() 
	    {
	    	hitbox.editDimensions(2 * GREATSWORD_RANGE, GREATSWORD_RANGE / 2);
	    	setCooldown(GREATSWORD_LIGHT_COOLDOWN);
	    	setDamage(GREATSWORD_LIGHT_DAMAGE);
	    	
	    	setSlashX();
	    	setSlashY();
	    	
	    	hitbox.setPosition(slashX, slashY);
	    	
	    	if(p != null && hitbox.intersects(p.getOpponent().getHitbox())) 
	    	{
	    		
	    		
	    		
	    		p.getOpponent().setDamage(getDamage());
	    		p.getOpponent().setKnockback(10, hitbox.getCollisionSide(p.getOpponent().getHitbox()));
	    	}
	    }
	    
	 
	    
	    public void heavyAttack() {
	       
    		hitbox.editDimensions( (3/2) * GREATSWORD_RANGE,  (5/2) * GREATSWORD_RANGE);
    		setCooldown(GREATSWORD_HEAVY_COOLDOWN);
    		setDamage(GREATSWORD_HEAVY_DAMAGE);
    		setSlashX();
    		setSlashY();
    		
    		//hitbox.setPosition(slashX, slashY);
    		 
    		
    		if(p != null && hitbox.intersects(p.getOpponent().getHitbox())) 
	    	{
	    		
	    		
	    		
	    		p.getOpponent().setDamage(getDamage());
	    		p.getOpponent().setKnockback(10, hitbox.getCollisionSide(p.getOpponent().getHitbox()));
	    	}
	    	
	    }
	    
	    public void setSlashX() 
	    {
	    	if(p.lastDirectionLeft()){
	    		slashX = p.getX() + p.getWidth();
	    		}
	    		
	    		if(p.lastDirectionRight()) 
	    		{
	    			slashX = p.getX() + p.getWidth();
	    		}else if(p.lastDirectionLeft()) 
	    		{
	    			slashX = p.getX() - p.getWidth();
	    		}else 
	    		{
	    			slashX = p.getX() + p.getWidth();
	    		}
	    }
	    
	    public void setSlashY() 
	    {
	    	slashY = p.getY() + (1/2) * GREATSWORD_RANGE;
	    }
	    
	    public void update() 
	    {
	    		
	    	
	    		slashX = p.getX();
		    	slashY = p.getY();
	    		hitbox.setPosition(slashX, slashY);
	    		
	    		
	    		hitbox.editDimensions(w, h);
	    		hitbox.update();
	    		
	    		
	    		
	    		
	    		for(int i = 0; i < projectileHitboxes.size(); i++) 
	    		{
	    			projectileHitboxes.get(i).update();
	    			
	    			
	    			if(projectileHitboxes.get(i).intersects(p.getOpponent().getHitbox())) 
	    			{
	    				p.getOpponent().setDamage(getDamage());
			    		p.getOpponent().setKnockback(10, hitbox.getCollisionSide(p.getOpponent().getHitbox()));
			    		projectileHitboxes.get(i).wipeVelocityPath();
			    		projectileHitboxes.remove(i);
	    			}
	    			
	    			// breaks when only one
	    			
	    			
	    			else if(projectileHitboxes != null && p != null && p.getPlatforms() != null) 
	    			{
	    				
	    				// Hadouken break on platform
	    				
	    				for(int j = 0; j < p.getPlatforms().size(); j ++) 
	    				 {
	    					 if(projectileHitboxes.get(i).intersects(p.getPlatforms().get(j).getHitbox()) && p.getPlatforms().get(j).getHitbox().getType().equalsIgnoreCase("Platform")) 
 			        		{
 			        			projectileHitboxes.get(i).wipeVelocityPath();
 			    	    		projectileHitboxes.remove(i);
 			        		}
	    				 }
	    			}	
	    		}
	    		
	    		

	    	
	    }
	    	

	    public int getSweepArea() {
	        return sweepArea;
	    }
	    
	    public void drawRectanglesWithTimer(Graphics g) {
	        float initialX = p.getX() + p.getWidth();
	        float initialY = p.getY() + p.getHeight();
	        float heightIncrement = 280 / 8;
	        float lengthIncrement = 110 / 5;
	        
	        

	        int currentRectIndex = (int) ((System.currentTimeMillis() / 60) % 9);

	        
	        	float x;
	        	
	        	if( currentRectIndex < 5)
	        		x = initialX + currentRectIndex * lengthIncrement;
	            else x = initialX + 5 * lengthIncrement;
	            float y = initialY - currentRectIndex * heightIncrement;
	            g.drawRect(x, y, 2 * GREATSWORD_RANGE, GREATSWORD_RANGE / 2);
	        
	    }
	    
	    public void draw(Graphics g, float x, float y) 
	    {
	    	
	    	this.x = x;
	    	this.y = y;
	    	this.x += 30;
	    	this.y -= 30;
	    	
	    	
	    	g.drawString("onLSide: " + p.lastDirectionLeft(), 0, 300);
	    	g.drawString("onRSide: " + p.lastDirectionRight(), 0, 320);
	    	
	    	  g.setColor(Color.pink);
	          g.fillRect(this.x, this.y, w,h);
	          
	          hitbox.render(g);
	          for(int i = 0; i < projectileHitboxes.size(); i++) 
	    		{
	    			projectileHitboxes.get(i).render(g);
	    		}
	    			
	          
	          
	          
	         
	          
	         // drawRectanglesWithTimer(g);

	    		
	    		 
	    		
	         

	         
	          
	      

	    }

		@Override
		public void attack() {
			// TODO Auto-generated method stub
			
		}

		

}
