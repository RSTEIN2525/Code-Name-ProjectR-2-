package Entities.Objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Entities.Player;
import Entities.Hitboxes.Hitbox;

public class PsychoStaff extends Weapon {

	public static int NORMAL_JAB_RANGE;
	public static int EMPOWERED_JAB_RANGE;
	public static int NORMAL_SPIKE_RANGE;
	public static int EMPOWERED_SPIKE_RANGE;
	public static int NORMAL_PROJECTILE_RANGE;
	public static int EMPOWERED_PROJECTILE_RANGE;
	public static int NORMAL_FLING_RANGE;
	public static int EMPOWERED_FLING_RANGE;
	public static int NORMAL_JAB_DAMAGE;
	public static int EMPOWERED_JAB_DAMAGE;
	public static int NORMAL_SPIKE_DAMAGE;
	public static int EMPOWERED_SPIKE_DAMAGE;
	public static int NORMAL_PROJECTILE_DAMAGE;
	public static int EMPOWERED_PROJECTILE_DAMAGE;
	public static int NORMAL_FLING_DAMAGE;
	public static int EMPOWERED_FLING_DAMAGE;
	public static int CHANNEL_INCREASE_RATE;
	public static int JAB_DURATION;
	public static int SPIKE_DURATION = 80;
	public static int PROJECTILE_DURATION;
	public static int FLING_DURATION;
	public static int JAB_COOLDOWN;
	public static int SPIKE_COOLDOWN = 60;
	public static int PROJECTILE_COOLDOWN;
	public static int FLING_COOLDOWN;
	public static int NORMAL_SPIKE_KNOCKBACK = 5;
	public static int EMPOWERED_SPIKE_KNOCKBACK = 5;
	boolean isKeyPressed = false;
	long keyPressedTime = 0;
	long keyHeldDuration;
	
	private boolean isKeyPressedIndicator = false;
	private long keyPressedTimeIndicator;
	private float indicatorX;
	private float indicatorY;
	private float indicatorWidth;
	private float indicatorHeight;
	Input input;
	
	
	Hitbox projectileHitbox;
	Hitbox spikeHitbox;
	Hitbox jabHitbox;
	Hitbox flingHitbox;
	
	Player p;
	Player opponent; 

	
	
	
	public PsychoStaff(String name, int damage, int range, int cooldownTimer) {
		super(name, damage, range, cooldownTimer);
		// TODO Auto-generated constructor stub
	}
	
	public void init(GameContainer gc) 
	{
		input = gc.getInput();
	}
	
	public void setPlayer(Player player)
	{
		this.p = player;
		this.opponent = (Player) this.p.getOpponent();
	}
	
	public void projectileHitbox() 
	{
		
	}
	
	public void jab() 
	{
		
	}
	
	public void channel() 
	{
		
	}
	
	public void fling() 
	{
		
	}
	
	public void spike() 
	{
		if(spikeHitbox == null) {
		// instead of inherent timer, we can make spike hitbox get deleted after its existed for its entire duration, only then can we conjure a new one
			
		
		setCooldown(SPIKE_COOLDOWN);
		// later make an if statement determing if empowered or not...
		setDamage(NORMAL_SPIKE_DAMAGE);
		
		float spikeWidth =(float) (p.getWidth() * 0.55);
		float spikeHeight = (float) (p.getHeight() * 0.90);
		
		float spikeX = (float) ((keyHeldDuration / 1000.0) * 500.0);
		
		float spikeY = p.getY() - spikeHeight;
				
		if(p.getPlayerDirection().equalsIgnoreCase("Right"))
			spikeX += p.getX();
		else 
		{
			spikeX *= -1;
			spikeX += p.getX();
		}
				
		
		spikeHitbox = new Hitbox(spikeX, spikeY + p.getHeight(), spikeWidth, spikeHeight, "Weapon", p, SPIKE_DURATION);
		spikeHitbox.setPlayer(p);
		}
        
		//System.out.println(spikeX);
		
	}
	
	
	
	@Override
	public void update() {
		
		// Indicator Variables
		indicatorWidth = p.getWidth() * 0.15f;
	    indicatorHeight = p.getHeight() * 0.30f;
	    indicatorY = p.getY() + p.getHeight() - indicatorHeight / 2;
		
	    // Indicator boolean logic
	    if(input.isKeyDown(Input.KEY_Q)) {
	        if (!isKeyPressedIndicator) {
	            isKeyPressedIndicator = true;
	            keyPressedTimeIndicator = System.currentTimeMillis();
	        }
	    }else if(isKeyPressedIndicator) {
	        isKeyPressedIndicator = false;
	    }
	    
	 // Calculate the current distance based on how long the key has been pressed
        float currentDistance = (System.currentTimeMillis() - keyPressedTimeIndicator) / 1000.0f * 500.0f;
        
        if(p.getPlayerDirection().equalsIgnoreCase("Right")) {
            indicatorX = p.getX() + currentDistance;
        } else {
            indicatorX = p.getX() - currentDistance;
        }
     
	    
		
		// Psycho Spike Timing Code
		if(input.isKeyDown(input.KEY_Q) && !isKeyPressed) 
		{
			isKeyPressed = true;
			keyPressedTime = System.currentTimeMillis();
		}else if(!input.isKeyDown(input.KEY_Q) && isKeyPressed) 
		{
			isKeyPressed = false;
		 keyHeldDuration = System.currentTimeMillis() - keyPressedTime;
		 spike();
			//System.out.println("Key was held for " + keyHeldDuration / 1000.0 + " seconds.");
		}
		
		if(spikeHitbox != null) 
			spikeHitbox.update();
		
		if(p!= null && spikeHitbox != null && spikeHitbox.intersects(p.getOpponent().getHitbox())) 
		{
			p.getOpponent().setDamage(NORMAL_SPIKE_DAMAGE);
			p.getOpponent().setKnockback(NORMAL_SPIKE_KNOCKBACK, spikeHitbox.getCollisionSide(p.getOpponent().getHitbox()));
			spikeHitbox = null;
		}else 
		{
			if(checkHitboxDuration(spikeHitbox))
				spikeHitbox = null;
		}
		
	}

	@Override
	public void draw(Graphics g, float x, float y) {
		// TODO Auto-generated method stub
		drawHitboxes(g);
		drawWeaponSprite(g);
		drawSpikeIndicator(g);
	
		
	}
	
	public void drawSpikeIndicator(Graphics g) 
	{

	    if (isKeyPressedIndicator) {
	        g.setColor(Color.yellow);
	        g.drawRect(indicatorX, indicatorY, indicatorWidth, indicatorHeight);
	    }
	
	}
	
	public void drawHitboxes(Graphics g) 
	{
		if(flingHitbox != null)
			flingHitbox.render(g);
		
		if(jabHitbox != null)
			jabHitbox.render(g);
		
		if(projectileHitbox != null)
			projectileHitbox.render(g);
		
		if(spikeHitbox != null)
			spikeHitbox.render(g);
	}
	
	public void drawWeaponSprite(Graphics g) 
	{
		// Mock Weapon Sprite
				 g.setColor(Color.blue);
		         g.fillRect(p.getX(), p.getY(), 30,70);
				
	}
	
	public boolean checkHitboxDuration(Hitbox h) 
    {
    	if(h!= null)
	    	System.out.println("Expired: " + h.durationExpired() + "Time: " + h.getDuration());
    	
    	if(h!= null) 
    	{
    		if(h.durationExpired())
    			return true;
    		
    	}
    		return false;
    	
    		
    }
	
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

}
