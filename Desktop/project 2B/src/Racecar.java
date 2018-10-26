
public class Racecar extends ConstantMovingObjects {
	
	private static final String ASSET_PATH = "assets/racecar.png";
	private static final float SPEED = 0.5f;
	
	//Rescuer move much faster than other vehicles
	/**
	 * @param x: x position
	 * @param y: y position
	 * @param moveRight: boolean initial move direction
	 */
	public Racecar(float x, float y, boolean moveRight){
		super(x,y,moveRight,ASSET_PATH,new String[] {Sprite.HAZARD});
		super.setSpeed(SPEED);
	}

}
