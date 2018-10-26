
public class Log extends Logs{
	
	private static final String ASSET_PATH = "assets/log.png";
	private static final float SPEED = 0.1f;
	
	/**
	 * @param x: x position
	 * @param y: y position
	 * @param moveRight: boolean initial move direction
	 */
	public Log(float x, float y, boolean moveRight){
		super(x,y,moveRight,ASSET_PATH,SPEED);
	}
}
