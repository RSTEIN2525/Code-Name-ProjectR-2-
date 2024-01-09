package Entities.Hitboxes;

import Entities.Player;

public class CircleHitbox extends Hitbox{

	
	private float x;
	private float y;
	private float radius;
	private String type;
	private float owner;
	
	public CircleHitbox(float x, float y, float radius, String type, Player owner) {
		super(x, y, radius, type, owner);
		
        this.x = x;
        this.y = y;
       this.radius = radius;
        this.type = type;
        p = owner;
	
	}
	
	


	
	    

}
