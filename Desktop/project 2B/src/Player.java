import java.util.ArrayList;
import org.newdawn.slick.Input;

// player class include life and game end and level control
public class Player extends Sprite {
	private static final String ASSET_PATH = "assets/frog.png";
	// X and Y position of live shown
	private static final int LIVE_X_START = 24;
	private static final int LIVE_Y_START = 744;
	// i set max live possible to 5
	private static final int MAX_LIVE = 5;
	// at strat everyone have 3 lives
	private static final int START_LIVE = 3;
	// number of holes for every level
	private static final int NUM_HOLES_INIT = 5;
	
	private float pushSpeed;
	private float delta;
	private int numLifes;
	private ArrayList<Live> Livelist = new ArrayList<Live>();
	private boolean levelFinish;
	private int getDx = 0, getDy = 0;
	private int numOfHolesLeft = NUM_HOLES_INIT;
	private boolean extraLive = false;

	//construct method creat player with 3 lives
	public Player(float x, float y) {
		super(ASSET_PATH, x, y,new String[] {Sprite.PLAYER});
		levelFinish = false;
		pushSpeed = 0;
		numLifes = START_LIVE;
		for(int i = 0; i < MAX_LIVE;i++) {
			Livelist.add(new Live(LIVE_X_START+World.TILE_SIZE*i,LIVE_Y_START));
		}
	}

	//Override render combine Live's render
	public void render() {
		super.render();
		for(int i = 0;i<numLifes ; i++) {
			Livelist.get(i).render();
		}
	}
	//Override add keyboard control and live's update
	public void update(Input input, int delta) {
		int dx = 0,
			dy = 0;
		//key board control
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			dx -= World.TILE_SIZE;
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			dx += World.TILE_SIZE;
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			dy += World.TILE_SIZE;
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			dy -= World.TILE_SIZE;
		}
		
		// make sure the frog stays on screen
		if (getX() + dx - World.TILE_SIZE / 2 < 0 || getX() + dx + World.TILE_SIZE / 2 	> App.SCREEN_WIDTH) {
			dx = 0;
		}
		if (getY() + dy - World.TILE_SIZE / 2 < 0 || getY() + dy + World.TILE_SIZE / 2 > App.SCREEN_HEIGHT) {
			dy = 0;
		}
		if (getX() - World.TILE_SIZE / 2 < 0 || getX() + World.TILE_SIZE / 2 > App.SCREEN_WIDTH) {
			loseLife();
		}
		move(dx, dy);
		getDx = dx;
		getDy = dy;
		
		this.delta = delta;
	}
	
	
	//Override onCollision to detect collision with different object or objects 
	public void onCollision(ArrayList<Sprite> otherSprites) {
		boolean onwater = false;
		boolean riding = false;
		boolean unreachable = false;
		
		for (int i = 0; i < otherSprites.size(); i++) {
			//test push player will move with other of push by other
			if (otherSprites.get(i).hasTag(Sprite.PUSH)) {
				this.pushSpeed = otherSprites.get(i).pushSpeed();
				this.move(pushSpeed*delta,0);
			}
			//Hazard: player will lose 1 life
			if (otherSprites.get(i).hasTag(Sprite.HAZARD)) {
				loseLife();
			}
			//Water : represent player on water area
			if(otherSprites.get(i).hasTag(Sprite.WATER)) {
				onwater = true;
			}
			//RIDE : represent player on log or Turtle
			if(otherSprites.get(i).hasTag(Sprite.RIDE)) {
				riding = true;
			}
			//SOLID: player can not go into solid area
			if(otherSprites.get(i).hasTag(Sprite.SOLID)) {
				unreachable = true;
			}
			//Terminal: flags on holes player will back to init position if not all hole filled
			if(otherSprites.get(i).hasTag(Sprite.TERMINAL)) {
				reachTerminal();
				otherSprites.get(i).setImage(this.getImage());
				otherSprites.get(i).setTags(new String[] { Sprite.HAZARD });
			}
			//EXTRA_LIFE: player get live +1
			if(otherSprites.get(i).hasTag(Sprite.EXTRA_LIFE)) {
				if(numLifes < MAX_LIVE) {
					numLifes+=1;
				}
				// used to destroy extralife objects
				extraLive = true;
			}
		}
		// if on water but not on log or turtle lose life
		if(onwater && !riding) {
			loseLife();
		}
		//move back (can't move in)
		if(unreachable) {
			move(-getDx,-getDy);
		}
	}
	// used to destroy extra live object (test whether player get extra life)
	public boolean getExtraLife() {
		return extraLive;
	}
	//change boolean of whether player get extra live
	public void setGetExtraLive(boolean GetExtraLive) {
		this.extraLive = GetExtraLive;
	}
	// take player back to initial position
	private void restart() {
		setX(App.SCREEN_WIDTH / 2);
		setY(App.SCREEN_HEIGHT - World.TILE_SIZE);
	}
	//live -1
	public void loseLife() {
		restart();
		numLifes -=1;
		if(numLifes == 0) {
			System.exit(0);
		}
	}
	//reach end numOfHolesLeft-1
	public void reachTerminal() {
		restart();
		numOfHolesLeft -=1;
		if(numOfHolesLeft == 0) {
			levelFinish = true;
		}
	}
	//test whether numOfHolesLeft = 0 finish this level 
	public boolean lvlFinish() {
		return levelFinish;
	}
	

}
