//abstract class
public abstract class Logs extends ConstantMovingObjects{
	
	private float pushSpeed = 0;
	
	// contain all logs with tags PUSH and RIDE
	/**
	 * @param x: x position
	 * @param y: y position
	 * @param moveRight: boolean initial move direction
	 */
	public Logs(float x, float y, boolean moveRight,String asset_path, float speed){
		super(x,y,moveRight,asset_path,new String[] {Sprite.RIDE,Sprite.PUSH});
		super.setSpeed(speed);
		pushSpeed = speed * (moveRight ? 1 : -1);
	}
	public float pushSpeed() {
		return (pushSpeed);
	}

}
