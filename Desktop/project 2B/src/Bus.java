
public class Bus extends ConstantMovingObjects {
	
	
	private static final String ASSET_PATH = "assets/bus.png";
	private static final float SPEED = 0.15f;
	
	/**
	 * @param x: x position
	 * @param y: y position
	 * @param moveRight: boolean initial move direction
	 */
	public Bus(float x, float y, boolean moveRight){
		super(x,y,moveRight,ASSET_PATH, new String[] {Sprite.HAZARD});
		super.setSpeed(SPEED);
	}

}
 