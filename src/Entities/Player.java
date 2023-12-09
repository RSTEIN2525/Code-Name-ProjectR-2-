package Entities;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Entities.Hitboxes.Hitbox;
import Entities.Hitboxes.Hitbox.Side;
import Entities.Objects.Weapon;
import Status.Status;
import Terrain.platform.Platform;

public class Player extends Entity
{
	
	private float x;
	private float y;
	private int width = 50;
	private int height = 100;
	private float yVelocity;
	private float xVelocity;
	private float acceleration = 0.35f;
    private float deceleration = 0.45f;
	private float gravity = 0.25f;
	private float maxSpeed = 10;
	private float movementReductionConstant = 3f;
	private float jumpTimer;
	private float jumpCooldown;
	private boolean canJump = true;
	Input input;
	boolean lastInputRight = false;
	boolean lastInputLeft = false;
	Hitbox h = new Hitbox(3,3,width,height,"Player",this);
	
	ArrayList<Platform> platforms ;
	public boolean colliding;
	Side collisionSide;
	Platform platformColliding;
	Entity Opponent;
	Weapon w;
	int health = 100;
	int cooldown;
	private int ID;
	PlayerHUD hud = new PlayerHUD(this);
	//private String status = "";
	boolean logPositions = false;
	private Status status;
	
	boolean paralyzed;
	
	public Player(int startX, int startY,  Weapon w, int PlayerID) 
	{
		x = startX;
		y = startY;
		this.w = w;
		jumpCooldown = (int) (1.5 * 60);
		w.setPlayer(this);
		cooldown = w.getCooldownTimer();
		ID = PlayerID;
		//Hitbox h = new Hitbox(x,y,width,height,"Player");
	}
	
	public String getName() 
	{
		if(ID == 1)
			return "Player 1";
		else
			return "Player 2";
		
	}
	
	public void setOpponent(Entity e) 
	{
		Opponent = e;
	}
	
	public void init(GameContainer gc) 
	{
		input = gc.getInput();
		h.setPlayer(this);
	}
	
	public void setPlatform(ArrayList<Platform> platforms)
	{
		this.platforms = platforms;
	}
	
	public void setParalysis(boolean paralyzed) 
	{
		this.paralyzed = paralyzed;
	}
	
	public void update(GameContainer gc) 
	{
		
		if(status != null) {
			status.implementConditions();
			status.resolveStatus();
		}
		
		// JumpTimer
		if(jumpTimer == jumpCooldown) 
		{
			canJump = true;
		}
		
		if(jumpTimer < jumpCooldown) 
		{
			jumpTimer ++;
		}
		
		w.update();
		
		if(cooldown > 0)
			cooldown --;
		
		if(ID == 1 && !paralyzed) 
		{
			if(input.isKeyDown(input.KEY_Q) && cooldown == 0) 
			{
				w.lightAttack();
				
				cooldown = w.getCooldownTimer();
			}
			
			if(input.isKeyDown(input.KEY_E) && cooldown == 0) 
			{
				w.heavyAttack();
				
				cooldown = w.getCooldownTimer();
			}
			

			if(input.isKeyDown(input.KEY_R) && cooldown == 0) 
			{
				w.projectileAttack();
				
				cooldown = w.getCooldownTimer();
			}
			
			if(input.isKeyDown(input.KEY_T) && cooldown == 0) 
			{
				w.upAttack();
				
				cooldown = w.getCooldownTimer();
			}
			
			if(input.isKeyDown(input.KEY_F) && cooldown == 0) 
			{
				w.areaOfEffectAttack();
				cooldown = w.getCooldownTimer();
			}
			
			
		}else if(ID == 2 && !paralyzed) 
		{
			
			
			
			
			if(input.isKeyDown(input.KEY_U) && cooldown == 0) 
			{
				w.lightAttack();
				
				cooldown = w.getCooldownTimer();
			}
			
			if(input.isKeyDown(input.KEY_O) && cooldown == 0) 
			{
				w.heavyAttack();
				
				cooldown = w.getCooldownTimer();
			}
			

			if(input.isKeyDown(input.KEY_P) && cooldown == 0) 
			{
				w.projectileAttack();
				
				cooldown = w.getCooldownTimer();
			}
			if(input.isKeyDown(input.KEY_0) && cooldown == 0) 
			{
				w.upAttack();
				
				cooldown = w.getCooldownTimer();
			}
		}
		
		
		
		h.setPosition(x,y);
		
		
		 if (lastInputLeft && xVelocity > maxSpeed * -1) {
			 if(!(getPlayerDirection().equalsIgnoreCase("Left")) && xVelocity <= 0)
	        	{
	        		xVelocity -= (acceleration / movementReductionConstant);
	        	}else 
	        	{
	        		xVelocity -= acceleration;
	        	}
			 
		
	        } else if (lastInputRight && xVelocity < maxSpeed) {
	        	if(!(getPlayerDirection().equalsIgnoreCase("Right")) && xVelocity >= 0)
	        	{
	        		xVelocity += (acceleration / movementReductionConstant);
	        	}else 
	        	{
	        		xVelocity += acceleration;
	        	}
	        	
	        } else {
	            // Apply deceleration when not moving
	            if (xVelocity > 0) {
	            	xVelocity -= deceleration;
	            	xVelocity = Math.max(xVelocity, 0);
	            } else if (xVelocity < 0) {
	            	xVelocity += deceleration;
	            	xVelocity = Math.min(xVelocity, 0);
	            }
	        }

	       
		
		
		
		
	if(ID == 1 && !paralyzed) 
	{
		if(input.isKeyDown(input.KEY_D)) 
		{
			lastInputRight = true;
			lastInputLeft = false;
		}
			
		
		if(input.isKeyDown(input.KEY_A)) 
		{
			lastInputRight = false;
			lastInputLeft = true;
		}
		
		if(!(input.isKeyDown(input.KEY_A) || input.isKeyDown(input.KEY_D)))
		{
			lastInputRight = false;
			lastInputLeft = false;
		}		
	}else if(ID == 2 && !paralyzed) 
	{
		if(input.isKeyDown(input.KEY_L)) 
		{
			lastInputRight = true;
			lastInputLeft = false;
		}
			
		
		if(input.isKeyDown(input.KEY_J)) 
		{
			lastInputRight = false;
			lastInputLeft = true;
		}
		
		if(!(input.isKeyDown(input.KEY_L) || input.isKeyDown(input.KEY_J)))
		{
			lastInputRight = false;
			lastInputLeft = false;
		}	
	}		
		
		
			
				
		
		
		
		
		if(platforms != null) 
		{
			for(int i = 0; i < platforms.size(); i++) 
			 {
				 if(h!= null && platforms.get(i)!= null && h.intersects(platforms.get(i).getHitbox()) && platforms.get(i).getHitbox().getType().equalsIgnoreCase("Platform"))
				 {
					 platformColliding = platforms.get(i);
					 resolveCollision(platformColliding.getHitbox());
				 }
			 }
		} 
		
		 
		 
		

		
		
		 y += yVelocity;
	        x += xVelocity;
		
		
	
		
		
		
		if(y >= 1080-100) 
		{
			y = 1080-100;
			yVelocity = 0;
		}
		
		
		if(ID == 1 && !paralyzed) 
		{
			
				if(canJump && input.isKeyPressed(input.KEY_W))
				{
					y -= 1;
					yVelocity = - 10;	
					jumpTimer = 0;
					canJump = false;
				}
				
			
		} else if(ID == 2 && !paralyzed) 
		{
			if(canJump && input.isKeyPressed(input.KEY_I))
			{
				y -= 1;
				yVelocity = - 10;	
				jumpTimer = 0;
				canJump = false;
			}
		}
		
		
		if(!colliding) 
		{
			yVelocity += gravity;
	       
		}
		
		
		
			
	}
	
	public ArrayList<Platform> getPlatforms()
	{
		return platforms;
	}
	
	
	
	
	private void resolveCollision(Hitbox platformHitbox){
		 float playerSide;
	        float platformEdge;
	        float overlap;

        collisionSide = h.getCollisionSide(platformHitbox);
        if (collisionSide == Side.TOP) {
        
        	yVelocity = 0;
        	playerSide = h.getY() + h.getHeight();
        	platformEdge = platformHitbox.getY();
        	overlap = playerSide - platformEdge;
        	
        	        	  if (overlap > 0) {
                  x = h.getX(); 
                  y = h.getY() - overlap;      
             }
        	
        	
        } else if (collisionSide == Side.BOTTOM) {
        	
        	yVelocity = 0;
        	playerSide = h.getY();
        	platformEdge = platformHitbox.getY() + platformHitbox.getHeight();
        	overlap = Math.abs(playerSide - platformEdge);
        	
        	        	  if (overlap > 0) {
                  x = h.getX(); 
                  y = h.getY() + overlap;  
        	        	  }
        
        } else if (collisionSide == Side.LEFT) {
        	xVelocity = 0;
            playerSide = h.getX() + h.getWidth();
        	platformEdge = platformHitbox.getX();
        	overlap = Math.abs(playerSide + h.getWidth() - platformEdge);
        	
        	if(overlap > 0) 
        	{
        		x = playerSide - overlap;
        		//y = h.getY();
        		
        	}
            
            
        } else if (collisionSide == Side.RIGHT) {
        	
        	xVelocity = 0;
            playerSide = h.getX();
        	platformEdge = platformHitbox.getX() + platformHitbox.getWidth();
        	overlap = Math.abs(playerSide - platformEdge);
        	
        	if(overlap > 0) 
        	{
        		x = playerSide + overlap;
        		//y = h.getY();
        		
        	}
        	
        	
        } else if (collisionSide == Side.NONE) {
            // No collision or collision from an unrecognized side
        	
        }
      

       
    }
	
	public Entity getOpponent() 
	{
		return Opponent;
	}
	
	public void debug(Graphics g) 
	{
		g.drawString("Attack_Cooldown: " + cooldown, x, y - 10);
		g.drawString("In Air: " + inAir(), x, y - 20);
		g.drawString("Player Direction: " + getPlayerDirection(), x,y-30);
		status.draw(g);
	}
	
	public boolean inAir() 
	{
		return !(yVelocity == 0.25);
	}
	
	public Status getStatus() 
	{
		return status;
	}
	
	public void setStatus(Status status) 
	{
		this.status = status;
	}
	
	
	
//	public String getStatus() 
//	{
//		return status;
//	}
//	
//	public void setStatus(String status) 
//	{
//		this.status = status;
//	}
	
	
	public void scriptedUpwardAttackMovement() 
	{
		
		int preJumpMove = 11;
		int jumpHeight = 12;
		
		xVelocity = preJumpMove;
		y-= 1;
		yVelocity = jumpHeight * -1;
		
		
			logPositions = true;
	}
	
	public void render(Graphics g) 
	{
		
		//debug(g);
		
		float weaponX = x;
		float weaponY = y;
		
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		//g.drawString("XVel: " + xVelocity + "YVel: " + yVelocity,  0,0);
		//g.drawString("CollisionSide: " + collisionSide + "Colliding: " + colliding, 0,50);
		
		
		
		
		
		
		h.render(g);
		 
	
		hud.draw(g); 
		
		w.draw(g, weaponX, weaponY);
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
		return width;
	}
	
	public float getHeight() 
	{
		return height;
	}
	
	public String getPlayerDirection() 
	{
		if(x < getOpponentXPosition())
			return "Right";
		else if(x >= getOpponentXPosition()) 
			return "Left";
		else return "N/A";
	}
	
	public float getOpponentXPosition() 
	{
		return getOpponent().getX();
	}
	
	public boolean lastDirectionRight() 
	{
		return lastInputRight;
	}
	
	public boolean lastDirectionLeft() 
	{
		return lastInputLeft;
	}
	
	@Override
	public Hitbox getHitbox() {
		// TODO Auto-generated method stub
		return h;
	}

	@Override
	public void setDamage(int i) 
	{
		health -= i;
	}
	
	public int getHealth() 
	{
		return health;
	}
	
	public int getID() 
	{
		return ID;
	}

	@Override
	public void setKnockback(float knockbackConstant, Side s) {
		
		// Apply knockback
		if(s == Side.LEFT) 
		{
			xVelocity = knockbackConstant;
		
		}
			
		if(s == Side.RIGHT) 
		{
			xVelocity = -1 * knockbackConstant;
		}
		
	}
}
