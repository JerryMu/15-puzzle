import utilities.BoundingBox;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
// an abstract all other class rewrite it's methods
public abstract class Sprite {
	// this is a defined constant to avoid typos
	public final static String HAZARD = "hazard";
	public final static String PUSH = "push";
	public final static String WATER = "water";
	public final static String RIDE = "ride";
	public final static String SAFEPLACE = "safeland";
	public final static String PLAYER = "player";
	public final static String TERMINAL = "terminal";
	public static final String SOLID = "solid";
	public static final String EXTRA_LIFE = "extralife";

	//useing bounds to test colide
	private BoundingBox bounds;
	private Image image;
	private float x;
	private float y;


	// contain tags objects have
	private String[] tags;
	
	//two kind of Sprite contractor for those objects have tags or not have
	public Sprite(String imageSrc, float x, float y) {
		setupSprite(imageSrc, x, y);
	}
	public Sprite(String imageSrc, float x, float y, String[] tags) {
		setupSprite(imageSrc, x, y);
		this.tags = tags;
	}
	//get image from image source and initial object
	private void setupSprite(String imageSrc, float x, float y) {
		try {
			image = new Image(imageSrc);
			reverseImage();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;
		
		bounds = new BoundingBox(getImage(), (int)x, (int)y);
		
		tags = new String[0];		
	}

	
	/**
	 * Sets the x position of the sprite.
	 * @param x	 the target x position
	 */
	public final void setX(float x) { this.x = x; bounds.setX((int)x); }
	/**
	 * Sets the y position of the sprite.
	 * @param y	 the target y position
	 */
	public final void setY(float y) { this.y = y; bounds.setY((int)y); }
	/**
	 * Accesses the x position of the sprite.
	 * @return	the x position of the sprite
	 */
	public final float getX() { return x; }
	/**
	 * Accesses the y position of the sprite.
	 * @return	the y position of the sprite
	 */
	public final float getY() { return y; }
	
	public final void move(float dx, float dy) {
		setX(x + dx);
		setY(y + dy);
	}
	// return Image of the sprite
	public Image getImage() {
		return image;
	}
	//set image of the sprite
	public void setImage(Image image){
		this.image = image;
	}
	//set tags of the sprite
	public void setTags(String[] tags){
		this.tags = tags;
	}
	
	public void reverseImage() { }
	
	// test whether sprite move off screen true
	public final boolean onScreen(float x, float y) {
		return !(x + World.TILE_SIZE / 2 > App.SCREEN_WIDTH || x - World.TILE_SIZE / 2 < 0
			 || y + World.TILE_SIZE / 2 > App.SCREEN_HEIGHT || y - World.TILE_SIZE / 2 < 0);
	}
	// override test sprite on screen
	public final boolean onScreen() {
		return onScreen(getX(), getY());
	}
	// return true if two objects colide
	public final boolean collides(Sprite other) {
		return bounds.intersects(other.bounds);
	}
	//the speed of PUSH objects
	public float pushSpeed() { return 0; }

	public void update(Input input, int delta) { }
	
	public void onCollision(ArrayList<Sprite> otherSprites) { }
	
	public void render() {
		getImage().drawCentered(x, y);
	}
	
	//test whether objects contain certain tag
	public boolean hasTag(String tag) {
		for (String test : tags) {
			if (tag.equals(test)) {
				return true;
			}
		}
		return false;
	}


}
