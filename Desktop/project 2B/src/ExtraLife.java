import java.util.ArrayList;

//ExtraLife class 
public class ExtraLife extends ConstantMovingObjects{
	private static final String ASSET_PATH = "assets/extralife.png";
	private static final float SPEED = 0.024f;
	private float logSpeed;
	private float Speed;
	// constructor method (apply use super class)
	public ExtraLife(float x,float y,boolean moveRight) {
		super(x,y,moveRight,ASSET_PATH,new String[] {Sprite.EXTRA_LIFE});
	}

	//set moving speed of object
	public void setSpeed(float speed) {
		
		this.logSpeed = speed;
		this.Speed = SPEED*(this.isMoveRight() ? 1 : -1);
		super.setSpeed(logSpeed+Speed);
	}
	//override onCollision to test it's collision with player
	public void onCollision(ArrayList<Sprite> otherSprites) {
		boolean riding = false;
		//one object may collide more tahn one objects at same time so i use arrayList to store every collided objects
		for (int i = 0; i < otherSprites.size(); i++) {
			if (otherSprites.get(i).hasTag(Sprite.RIDE)) {
				riding = true;
			}
		}
		if(!riding) {
			Speed = -Speed;
			super.setSpeed(logSpeed+Speed);
		}
	}

}
	

