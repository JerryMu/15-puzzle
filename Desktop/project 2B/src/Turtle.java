import java.util.Timer;
import java.util.TimerTask;



//turtle class have same tags as logs class
public class Turtle extends ConstantMovingObjects{
	// this is a defined constant to avoid typos
	private static final String ASSET_PATH = "assets/turtles.png";
	private static final float SPEED = 0.085f;
	private static final int VISIBLE_SECONDS = 7;
	private static final int INVISIBLE_SECONDS = 9;
	private int timer_secod = 0;
	private float pushspeed = 0;
	private static Timer mytimer = new Timer();
	//create a timer to record seconds passed
	private TimerTask task = new TimerTask() {
		public void run() {
			timer_secod++;
		}
	};
	//Constructor method to initialize timer and set x,y position and tags of turtles\
	/**
	 * @param x: x position
	 * @param y: y position
	 * @param moveRight: boolean initial move direction
	 */
	public Turtle(float x, float y, boolean moveRight){
		super(x,y,moveRight,ASSET_PATH,new String[] {Sprite.RIDE,Sprite.PUSH});
		mytimer.schedule(task,1000,1000);
		super.setSpeed(SPEED);
		pushspeed = SPEED * (moveRight ? 1 : -1);
	} 
	// override push speed
	public float pushSpeed() {
		return (pushspeed);
	}

	//override render methd to make turtle visible and invisible
	public void render() {
		if(timer_secod <= VISIBLE_SECONDS ) {
			super.render();
		}else if(timer_secod>VISIBLE_SECONDS && timer_secod<INVISIBLE_SECONDS){
			//make turtle can not protect player
			this.setTags(new String[] {});
		}else if(timer_secod>INVISIBLE_SECONDS) {
			timer_secod = 0;
			this.setTags(new String[] {Sprite.RIDE,Sprite.PUSH});
		}
		

	}

}
