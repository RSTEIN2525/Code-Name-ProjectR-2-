package Entities.Objects;

import org.newdawn.slick.Graphics;

import Entities.Player;

public abstract class Weapon 
{
	protected String name;
    protected int damage;
    protected int range;
    protected int cooldownTimer;
    Player p;

    public Weapon(String name, int damage, int range, int cooldownTimer) {
        this.name = name;
        this.damage = damage;
        this.range = range;
        this.cooldownTimer = cooldownTimer;
    }

    public abstract void attack();
    public abstract void update();
    public abstract void draw(Graphics g, float x, float y);

    public void setPlayer(Player p) 
    {
    	this.p = p;
    }
    
    public void setCooldown(int cooldownTimer) 
    {
    	this.cooldownTimer = cooldownTimer;
    }
    
    public void setDamage(int damage) 
    {
    	this.damage = damage;
    }
    
    public String getName() {
        return name;
    }
    
    public int getCooldownTimer() 
    {
    	return cooldownTimer;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

	public void lightAttack() {
		// TODO Auto-generated method stub
		
	}

	public void rangedAttack() {
		// TODO Auto-generated method stub
		
	}

	public void heavyAttack() {
		// TODO Auto-generated method stub
		
	}
	
	public void upAttack() {}

	public void projectileAttack() {
		// TODO Auto-generated method stub
		
	}

	public void hitboxPathing() {
		// TODO Auto-generated method stub
		
	}

	public void areaOfEffectAttack() {
		// TODO Auto-generated method stub
		
	}
}
