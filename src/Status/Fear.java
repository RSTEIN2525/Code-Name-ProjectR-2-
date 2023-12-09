package Status;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Entities.Player;

public class Fear extends Status{

	Player p;
	
	public Fear(Player p) {
		super(p);
		this.p = p;
		duration = 240;
		statusType = "Fear";
	}
	
	public void implementConditions() 
	{
		// invokes paralysis
		if(statusType != null || !statusType.equalsIgnoreCase("None")) 
		{
			if(p!= null)
			p.setParalysis(true);	
		}
	}
	
	public void removeConditions() 
	{
		// removes paralysis
		
		if(p!= null)
		p.setParalysis(false);
	}
	
	public void draw(Graphics g) 
	{
//		g.setColor(Color.white);
//		g.drawString("Status: " + statusType,400,400 - 30);
	}
	

}


// 12/6 last thing worked on was doing implement conditions and remove conditions