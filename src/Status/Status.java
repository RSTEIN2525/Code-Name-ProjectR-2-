package Status;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Entities.Player;

public abstract class Status {
	
	Player p;
	int duration;
	int remainingDuration = 0;
	String statusType;
	
	public Status(Player p) 
	{
		statusType = "None";
	}
	
	public String getStatusType() 
	{
		return statusType;
	}
	
	public int getRemainingDuration() 
	{
		return remainingDuration;
	}
	
	public void draw(Graphics g) 
	{
		g.setColor(Color.white);
		g.drawString("Status: " + statusType,p.getX(), p.getY() - 30);
	}
	
	public void resolveStatus() 
	{
		if(statusType != null) 
		{
			if(remainingDuration < duration) 
			{
				remainingDuration ++;
			}else 
			{
				remainingDuration = 0;
				statusType = "None";
				removeConditions();
			}
		}
	}
	
	public void implementConditions() 
	{
		if(statusType != null || !statusType.equalsIgnoreCase("None")) 
		{
			// condition
		}
	}
	
	public void removeConditions() 
	{
		System.out.print("Condition Removed");
	}
	
	
}
