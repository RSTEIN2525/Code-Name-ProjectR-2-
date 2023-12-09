package Entities.Objects;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Entities.Player;
import Entities.Hitboxes.Hitbox;
import Status.Fear;
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
	 //Hitbox hitbox = new Hitbox(x,y,w,h,"Weapon", this);
	 //Hitbox projectileHitbox = new Hitbox(x,y,w,h,"Weapon", this);
	// Hitbox upAttackHitbox = new Hitbox(x,y,w,h,"Weapon",this );
	
	 Hitbox hitbox;
	 Hitbox projectileHitbox;
	 Hitbox upAttackHitbox;
	 Hitbox areaOfEffectHitbox;
	 Player p;
	 Hitbox heavyAttackHitbox;
	 Hitbox lightAttackHitbox;
	 
	 
	 boolean attacking = false;
	 
	 ArrayList<Hitbox> projectileHitboxes = new ArrayList <Hitbox>();
	 ArrayList<Hitbox> newProjectileList = new ArrayList <Hitbox>();
	 
	 
	 
	   
	 
	 
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
	    
	    
	    public void areaOfEffectAttack() 
	    {
	    	areaOfEffectHitbox = null;
	    	int width = 64;
	    	int maxWidth = 64 * 10;
	    	int expansionRate = 5;
	    	int x = (int) p.getX();
	    	int y = (int) (p.getY() + p.getHeight() / 2);
//	    	areaOfEffectHitbox = new Hitbox(x,y,width,width, "Weapon", p);
	    	
	    	areaOfEffectHitbox = new Hitbox(x,y,width, "Weapon", p);
	    	
	    	areaOfEffectHitbox.setPlayer(p);
	    	areaOfEffectHitbox.editDimensions(0,0,width);
	    	areaOfEffectHitbox.setExpansion(expansionRate,expansionRate,maxWidth,w);
	    	setCooldown(GREATSWORD_LIGHT_COOLDOWN);
	    	setDamage(GREATSWORD_HEAVY_DAMAGE * 2);
	    	
	    	
	    	
	    }
	    
	    public void upAttack() 
	    {
	    	//setCooldown(60);
	    	upAttackHitbox = null;
	    	upAttackHitbox = new Hitbox(p.getX(), p.getY() - p.getHeight() + (p.getHeight()),32, 32, "Weapon",p);
	    	upAttackHitbox.setPlayer(p);
	    	
	    	upAttackHitbox.editDimensions(2 * GREATSWORD_RANGE, GREATSWORD_RANGE / 2,0);
	    	setCooldown(GREATSWORD_LIGHT_COOLDOWN);
	    	setDamage(GREATSWORD_LIGHT_DAMAGE);
	    	
	    	//setSlashX();
	    	//setSlashY();
	    	
	    	p.scriptedUpwardAttackMovement();
	    	
	    	int xDistanceMoved = 130;
	    	int yDistanceMoved = (int) p.getHeight() * 2 ;
	    	
	    	
	    	int xVel;
	    	if(p.getPlayerDirection().equalsIgnoreCase("Right"))
        		{
	    		xVel = 10;
	    		//xDistanceMoved *= -1;
        		}
        	else {
        		xVel = -10;
        		//Math.abs(xDistanceMoved);
        	}
	    	
	    	
	    	upAttackHitbox.setVelocityPath(xVel, -10,  xDistanceMoved , yDistanceMoved, p.getX(), p.getY());
	    	
	    }
	    
	    
	    public void projectileAttack() {
	       
	        setCooldown(30);
	        setDamage(GREATSWORD_UP_DAMAGE);
	        
	        
	      //  projectileHitboxes.add(new Hitbox(p.getX(), p.getY() + (p.getHeight() / 3),32, 32, "Weapon",p));
	        projectileHitboxes.add(new Hitbox(p.getX(), p.getY() + (p.getHeight() / 3), 32, "Weapon",p));
	        
	        for(int i = 0; i < projectileHitboxes.size(); i ++) 
	        {
	        	
	        	projectileHitboxes.get(i).setPlayer(p);
	        	
	        	float xVel;
	        	//if(p.getPlayerDirection().equalsIgnoreCase("Right"))
	        		xVel = 10;
	        	//else xVel = -10;
	        		projectileHitboxes.get(i).editDimensions(0, 0, 32);
	        	projectileHitboxes.get(i).setVelocityPath(xVel, 0,  distanceToEndStage() , 280, p.getX(), p.getY());
	        }
	        
	    }
	    
	    
	   
	    
	    
	    
	    public float distanceToEndStage() 
	    {
	    	return Math.abs((Main.getScreenWidth() - 32) - (p.getX() + p.getWidth()));
	    }





	    
	    
	    public void lightAttack() 
	    {
	    	
	    	lightAttackHitbox = new Hitbox(p.getX(), p.getY() - p.getHeight() + (p.getHeight()),32, 32, "Weapon",p);
	    	lightAttackHitbox.editDimensions(2 * GREATSWORD_RANGE, GREATSWORD_RANGE / 2,0);
	    	setCooldown(GREATSWORD_LIGHT_COOLDOWN);
	    	setDamage(GREATSWORD_LIGHT_DAMAGE);
	    	
	    	setSlashX();
	    	setSlashY();
	    	
	    	lightAttackHitbox.setPosition(slashX, slashY);
	    	
	    	if(p != null && lightAttackHitbox.intersects(p.getOpponent().getHitbox())) 
	    	{
	    		
	    		
	    		
	    		p.getOpponent().setDamage(getDamage());
	    		p.getOpponent().setKnockback(10,lightAttackHitbox.getCollisionSide(p.getOpponent().getHitbox()));
	    	}
	    }
	    
	 
	    
	    public void heavyAttack() {
	       
	    	heavyAttackHitbox = new Hitbox(p.getX(), p.getY() - p.getHeight() + (p.getHeight()),32, 32, "Weapon",p);
	    	heavyAttackHitbox.editDimensions( (int) 2 * GREATSWORD_RANGE, GREATSWORD_RANGE / 8,0);
    		setCooldown(GREATSWORD_HEAVY_COOLDOWN);
    		setDamage(GREATSWORD_HEAVY_DAMAGE);
    		setSlashX();
    		setSlashY();
    		
    		//hitbox.setPosition(slashX, slashY);
    		 
    		
    		if(p != null && heavyAttackHitbox.intersects(p.getOpponent().getHitbox())) 
	    	{
	    		
	    		
	    		
	    		p.getOpponent().setDamage(getDamage());
	    		p.getOpponent().setKnockback(10, heavyAttackHitbox.getCollisionSide(p.getOpponent().getHitbox()));
	    	}
	    	
	    }
	    
	    public void setSlashX() 
	    {		
        	if(p.getPlayerDirection().equalsIgnoreCase("Right"))
        		slashX = p.getX() + p.getWidth();
        	if(p.getPlayerDirection().equalsIgnoreCase("Left"))
        		slashX = p.getX() - (p.getWidth() * 3);
	    }
	    
	    public void setSlashY() 
	    {
	    	slashY = p.getY() + (1/2) * GREATSWORD_RANGE;
	    }
	    
	    public void update() 
	    {
	    		
	    		hitbox.setPlayer(p);
	    		slashX = p.getX();
		    	slashY = p.getY();
	    		hitbox.setPosition(slashX, slashY);
	    		
	    		
	    		hitbox.editDimensions(w, h,0);
	    		hitbox.update();
	    		
	    		if(heavyAttackHitbox != null)
	    			heavyAttackHitbox.update();
	    		
	    		if(p!= null && heavyAttackHitbox != null &&heavyAttackHitbox.intersects(p.getOpponent().getHitbox()))
	    		{
	    			p.getOpponent().setDamage(getDamage());
		    		p.getOpponent().setKnockback(10, heavyAttackHitbox.getCollisionSide(p.getOpponent().getHitbox()));
		    		heavyAttackHitbox = null;
	    			
	    		}
	    		
	    		if(lightAttackHitbox != null)
	    			lightAttackHitbox.update();
	    		
	    		if(p!= null && lightAttackHitbox != null && lightAttackHitbox.intersects(p.getOpponent().getHitbox()))
	    		{
	    			p.getOpponent().setDamage(getDamage());
		    		p.getOpponent().setKnockback(10, lightAttackHitbox.getCollisionSide(p.getOpponent().getHitbox()));
		    		lightAttackHitbox = null;
	    			
	    		}
	    		
	    		
	    		if(areaOfEffectHitbox != null)
	    			areaOfEffectHitbox.update();
	    		
	    		//if(p!= null && areaOfEffectHitbox != null && areaOfEffectHitbox.intersects(p.getOpponent().getHitbox()))
	    		
	    		if(p!= null && areaOfEffectHitbox != null && areaOfEffectHitbox.circleRectangleCollision(p.getOpponent().getHitbox()))
	    		{

		    		p.getOpponent().setDamage(getDamage());
		    		System.out.println("Collision Side: " + areaOfEffectHitbox.getCollisionSideRectOnCircle(p.getOpponent().getHitbox()));
		    		p.getOpponent().setKnockback(30, areaOfEffectHitbox.getCollisionSideRectOnCircle(p.getOpponent().getHitbox()));
		    		((Player) p.getOpponent()).setStatus(new Fear((Player) p.getOpponent()));
		    		areaOfEffectHitbox = null;
	    		}
	    		
	    		
	    		if(upAttackHitbox != null)
	    			upAttackHitbox.update();
	    		
	    		
	    		
	    		if(p != null && upAttackHitbox != null && upAttackHitbox.intersects(p.getOpponent().getHitbox())) 
		    	{
		    		p.getOpponent().setDamage(getDamage());
		    		p.getOpponent().setKnockback(10, upAttackHitbox.getCollisionSide(p.getOpponent().getHitbox()));
		    		upAttackHitbox = null;
		    	}
		    	
	    		
	    		
	  
	    			
	    		
	    		ArrayList<Integer> indexOfRemovedProjectiles =new ArrayList<Integer>();
	    	
	    		
	    		for(int i = 0; i < projectileHitboxes.size(); i++) 
	    		{
	    			
	    			boolean triggerDeletion = false;
	    		
	    			
	    			//if(projectileHitboxes.get(i).intersects(p.getOpponent().getHitbox())) 
	    			if(projectileHitboxes.get(i).circleRectangleCollision(p.getOpponent().getHitbox())) 
	    			{
	    				p.getOpponent().setDamage(getDamage());
			    		p.getOpponent().setKnockback(10, hitbox.getCollisionSide(p.getOpponent().getHitbox()));
			    		projectileHitboxes.get(i).wipeVelocityPath();
			    		
			    		
			    		
			    		// Add numbers to list to be removed;
			    		triggerDeletion = true;
//			    		projectileHitboxes.remove(i);
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
 			    	    		triggerDeletion = true;
 			        		}
	    				 }
	    			}
	    			
	    			if(triggerDeletion == true) 
	    			{
	    				indexOfRemovedProjectiles.add(i);
	    			}
	    			
	    		}
	    		
	    		for(int i = 0; i < indexOfRemovedProjectiles.size(); i++) 
	    		{
	    			
	    			projectileHitboxes.remove(projectileHitboxes.get(indexOfRemovedProjectiles.get(i)));
	    		}
	    		
	    		for(int i = 0; i < projectileHitboxes.size(); i++)
	    		projectileHitboxes.get(i).update();

	    	
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
	    	
	    	
//	    	g.drawString("onLSide: " + p.lastDirectionLeft(), 0, 300);
//	    	g.drawString("onRSide: " + p.lastDirectionRight(), 0, 320);
	    	
	    	  g.setColor(Color.pink);
	          g.fillRect(this.x, this.y, w,h);
	          
	         hitbox = new Hitbox(x,y,w,h,"Weapon", p);
	          
	          hitbox.render(g);
	          for(int i = 0; i < projectileHitboxes.size(); i++) 
	    		{
	    			projectileHitboxes.get(i).render(g);
	    		}
	          
	          if(upAttackHitbox != null)
	        	  upAttackHitbox.render(g);
	    			
	          
	          if(areaOfEffectHitbox != null)
	        	  areaOfEffectHitbox.render(g);
	          
	          if(lightAttackHitbox != null) 
	          {
	        	  lightAttackHitbox.render(g);
	          }
	          
	          if(heavyAttackHitbox != null) 
	          {
	        	  heavyAttackHitbox.render(g);
	          }
	          // upAttackDebug
	         //g.drawString("X: " + upAttackHitbox.getX() + " Y: " + upAttackHitbox.getY(), 1000, 0);
	         //g.drawString("xVel: " + upAttackHitbox.getXVel() + " yVel: " + upAttackHitbox.getYVel(), 1000, 20);
	          
	         
	         
	          
	         // drawRectanglesWithTimer(g);

	    		
	    		 
	    		
	         

	         
	          
	      

	    }

		@Override
		public void attack() {
			// TODO Auto-generated method stub
			
		}

		

}
