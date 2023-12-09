package core;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Entities.Dummy;
import Entities.Player;
import Entities.Objects.GreatSword;
import Entities.Objects.Weapon;
import Terrain.platform.Platform;

public class Game extends BasicGameState 
{	
	private int id;
	public static String winnerName;
	Weapon w1 = new GreatSword();
	Weapon w2 = new GreatSword();
	Player p1 = new Player(200,200, w1, 1);
	Player p2 = new Player(900,200,w2,2);
	ArrayList<Platform> platforms = new ArrayList<>();

	public Game(int id) 
	{
		this.id = id;
	}
	
	public int getID() 
	{
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		gc.setShowFPS(true);
		p1.init(gc);
		p2.init(gc);
		p1.setOpponent(p2);
		p2.setOpponent(p1);
		platforms.add(new Platform(1080,1080- 200,300,50)); //mid stage
		platforms.add(new Platform(0,1080-50,1920,50)); // bottom
		platforms.add(new Platform(0,0,1920,50)); //top
		platforms.add(new Platform(0,0,50,1080)); //left
		platforms.add(new Platform(1920 - 50,0,50,1080)); //right
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		p1.update(gc);
		p2.update(gc);
		p1.setPlatform(platforms);
		p2.setPlatform(platforms);
		// This is updates your game's logic every frame.  NO DRAWING.
		
		
		if(p1.getHealth() <= 0) {
			winnerName = p2.getName();
			sbg.enterState(1);
		}
		if(p2.getHealth() <= 0) {
			winnerName = p1.getName();
			sbg.enterState(1);
		}	
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		
		for(int i = 0; i < platforms.size(); i++) 
		{
			platforms.get(i).render(g);
		}
		
		p1.render(g);
		p2.render(g);
		// This code renders shapes and images every frame.
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a gameState.  
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		// This code happens when you leave a gameState. 
	}

	public void keyPressed(int key, char c)
	{
		// This code happens every time the user presses a key
	}
	
	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
	}
	
	


}
