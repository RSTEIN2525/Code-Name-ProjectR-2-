package Entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Main;

public class PlayerHUD{
	
	Player p;
	
	public PlayerHUD(Player p) 
	{
		this.p = p;
	}
	
	public void draw(Graphics g) 
	{
		healthBar(g);
		statusBar(g);
	}
	
	public void update() 
	{
		
	}
	
	public void statusBar(Graphics g) 
	{
		int screenOffset = 25;
		int x;
		int y = screenOffset * 2;
		
		if(p.getID() == 1) 
			x = 2 * screenOffset;
		else x = Main.getScreenWidth() - screenOffset - 185;
		
		g.setColor(Color.white);
		
		if(p.getStatus() != null)
		g.drawString("Status: " + p.getStatus().getStatusType(), x, y);
	}
	
	public void healthBar(Graphics g) 
	{
		int maxHealth = 100;
		int currentHealth = p.getHealth();
		int vesselWidth = maxHealth * 2;
		int healthWidth = currentHealth * 2;
		int screenOffset = 20;
		int x;
		int y = screenOffset * 2;
		
		if(p.getID() == 1) 
			x = 2 * screenOffset;
		else x = Main.getScreenWidth() - screenOffset - vesselWidth;
		
		//Outline
		g.setColor(Color.black);
		g.fillRect(x - screenOffset / 4, y - screenOffset / 4, vesselWidth + screenOffset / 2, (float) (screenOffset * 2.5));
		
		// Vessel
		g.setColor(Color.gray);
		g.fillRect(x, y, vesselWidth, screenOffset * 2);
		
		// Health Fill
		if(p.getID() == 1)
			g.setColor(Color.red);
		else g.setColor(Color.blue);
		
		g.fillRect(x,y,healthWidth, screenOffset * 2);
		
		
		
		
		
	}
}
