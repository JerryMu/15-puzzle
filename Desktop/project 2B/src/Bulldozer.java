// subclass of ConstantMovingObjects have PUSH tag
public class Bulldozer extends ConstantMovingObjects {
	private static final String ASSET_PATH = "assets/bulldozer.png";
	private static final float SPEED = 0.05f;
	private float pushSpeed = 0;
	
	
	/**
	 * @param x: x position
	 * @param y: y position
	 * @param moveRight: boolean initial move direction
	 */
	public Bulldozer(float x, float y, boolean moveRight){
		super(x,y,moveRight,ASSET_PATH,new String[] {Sprite.PUSH,Sprite.SOLID});
		super.setSpeed(SPEED);
		pushSpeed = SPEED * (moveRight ? 1 : -1);
	}
	////override: Sprite class set pushSpeed
	public float pushSpeed() {
		return (pushSpeed);
	}

	
}
