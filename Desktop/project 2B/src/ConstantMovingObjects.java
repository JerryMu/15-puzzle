import org.newdawn.slick.Input;
//Subclass of Spites contain all sprite with constant moving speed
public class ConstantMovingObjects extends Sprite {
	
	private float speed;
	
	private boolean moveRight;
		

	/**
	 * @return define the direction of star
	 */
	public boolean isMoveRight() {
		return moveRight;
	}

	/**
	 * @param moveRight set the initial direction of move
	 */
	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}

	/**
	 * @param speed setter constant move speed
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	/**
	 * @return getter constant move speed
	 */
	public float getSpeed() {
		return this.speed;
	}
	
	/**
	 * @return the center point of objects
	 */
	private final float getInitialX() {
		return moveRight ? -World.TILE_SIZE / 2
						 : App.SCREEN_WIDTH + World.TILE_SIZE / 2;
	}
	
	// build method include initial position and a boolean about move right and tags it contain
	public ConstantMovingObjects(float x, float y, boolean moveRight,String assets_path, String[] tags) {
		super(assets_path, x, y, tags);
		this.moveRight = moveRight;
		reverseImage(!moveRight);
	}
	//reverse image by x-axis 
	public void reverseImage(boolean reverse) {
		setImage(getImage().getFlippedCopy(reverse, false));
	}
	
	
	//Override: update with constant moving move(dx,dy)
	public void update(Input input, int delta) {
		move(speed * delta * (moveRight ? 1 : -1), 0);
		
		// check if the vehicle has moved off the screen
		if (getX() > App.SCREEN_WIDTH + World.TILE_SIZE / 2 || getX() < -World.TILE_SIZE / 2
		 || getY() > App.SCREEN_HEIGHT + World.TILE_SIZE / 2 || getY() < -World.TILE_SIZE / 2) {
			setX(getInitialX());
		}
	}
}
