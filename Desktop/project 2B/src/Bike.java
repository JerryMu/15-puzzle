import org.newdawn.slick.Input;
// subclass of ConstantMovingObjects move return and back

public class Bike extends ConstantMovingObjects{
	private static final String ASSET_PATH = "assets/bike.png";
	private static final float SPEED = 0.2f;
	private boolean moveRight;
	
	//override with HAZARD tag and constant moving speed
	/**
	 * @param x: x position
	 * @param y: y position
	 * @param moveRight: boolean initial move direction
	 */
	public Bike(float x, float y, boolean moveRight){
		super(x,y,moveRight,ASSET_PATH,new String[] {Sprite.HAZARD});
		super.setSpeed(SPEED);
		moveRight = isMoveRight();
		super.reverseImage(true);

	}
	
	/* 
	 * @see override: 24 and 1000 of y-axis and return back
	 */
	public void update(Input input, int delta) {
		move(SPEED * delta * (moveRight ? 1 : -1), 0);
		
		if (getX() <=24 || getX() >= 1000){
			moveRight = !moveRight;
			move(SPEED * delta * ((moveRight) ? 1 : -1), 0);
			super.reverseImage(true);
		}
	
	}
}
