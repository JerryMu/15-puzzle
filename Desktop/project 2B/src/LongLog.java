
public class LongLog extends Logs{
	
	private static final String ASSET_PATH = "assets/longlog.png";
	private static final float SPEED = 0.07f;
	
	/**
	 * @param x: x position
	 * @param y: y position
	 * @param moveRight: boolean initial move direction
	 */
	public LongLog(float x, float y, boolean moveRight){
		super(x,y,moveRight,ASSET_PATH,SPEED);
	}
}
