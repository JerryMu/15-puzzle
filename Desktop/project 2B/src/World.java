import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	//square tile size
	public static final int TILE_SIZE = 48;
	//position of water tile start
	public static final int WATER_START = 336;
	
	public static final int WATER_END = 48;
	public static final int TIME_LOWER = 25;
	public static final int TIME_HIGHER = 35;
	public static final int TIME_LIMIT =14;

	
	private static final String LEVEL0= "assets/levels/0.lvl";
    private static final String LEVEL1= "assets/levels/1.lvl";
    //sprities arrayList conatain all sprite in world
	private ArrayList<Sprite> sprites = new ArrayList<>();
	private Player player;
	private ArrayList<Integer> logsIndex;
	Scanner inputStream;
	private File file= null;
	private static Timer mytimer = new Timer();
	private int timer = 0;
	private int random_time;
	private int addPosition = 0;
	private boolean haveExtraLive = false;
	
	// World class contain all sprites and their relation
	public World(int lvlNum) {
		logsIndex  = new ArrayList<>();
		//decide level
		if(lvlNum == 0) {
			file= new File(LEVEL0);
		}else if(lvlNum == 1){
			file= new File(LEVEL1);
		}
		//read file create new world
        try{
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] values = line.split(",");
                Object Objects = null;
                /*Different kind of objects moving objects or tile with different method
                 use Typefactory to convert .csv file to different objects */
                 
            	if(values.length == 3) {
            		Objects = TypeFactory.getObjecttype(values[0],values[1],values[2]);
            	}else if(values.length == 4) {
            		Objects = TypeFactory.getObjecttype(values[0],values[1],values[2],values[3]);
            		if(values[0].equals("log")||values[0].equals("longLog")) {
            			logsIndex.add(sprites.size());
            		}
            	}
            	// add all objects to sprites array
    			sprites.add((Sprite) Objects);
    			
            }
            
            // close input file
            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //add flags to world
    	for (float x = TILE_SIZE*2.5f; x < App.SCREEN_WIDTH; x+= TILE_SIZE*4f) {
    		sprites.add(Tile.createFlagTile(x, 48));
    		
    	}
		player = new Player(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT - TILE_SIZE);
		sprites.add(player);
		//set a timer 
		mytimer.schedule(task,1000,1000);
		random_time = generateRandomTime();
		
	}
	
	private TimerTask task = new TimerTask() {
		public void run() {
			timer++;
			System.out.println(timer);
		}
	};
		
	// method return boolean of finish this level
	public boolean nextLvl() {
		return player.lvlFinish();
	}
	//choose random log to generate extralife object
	public int chooseRandomLog(ArrayList<Integer> LogsIndex) {
		Random r = new Random();
		return LogsIndex.get(r.nextInt(LogsIndex.size()));
	}
	// generate time between 25 and 35 to generate extra live
	public int generateRandomTime() {
		Random r = new Random();
		return r.nextInt(TIME_HIGHER - TIME_LOWER + 1) + TIME_LOWER;
	}
	//method to generate extra live
	public void addExtraLife() {
		// get information about log that extra live generate
		ConstantMovingObjects choosen_log = (ConstantMovingObjects) sprites.get(chooseRandomLog(logsIndex));
		String x =Float.toString(choosen_log.getX());
		String y = Float.toString(choosen_log.getY());
		String moveRight = Boolean.toString(choosen_log.isMoveRight());
		
		ExtraLife extraLife = (ExtraLife)TypeFactory.getObjecttype("ExtraLife",x,y,moveRight);
		extraLife.setSpeed(choosen_log.getSpeed());
		//add extra live to sprite
		addPosition = sprites.size();
		sprites.add(extraLife);
		haveExtraLive = true;
	}
	//if time exceed or extra live get delete it
	public void deleteExtraLife() {
		sprites.remove(addPosition);
		haveExtraLive = false;
		
	}
	
	// override update to run every update of sprite
	public void update(Input input, int delta) {
		for (Sprite sprite : sprites) {
			sprite.update(input, delta);
		}
		//test colide for each sprite
		for(Sprite sprite1: sprites) {
			ArrayList<Sprite> colideSprites = new ArrayList<>();
			for (Sprite sprite2: sprites) {
				if (sprite1 != sprite2 && sprite1.collides(sprite2)) {
					colideSprites.add(sprite2);
				}
			}
			sprite1.onCollision(colideSprites);
		}
		// time to generate extralive
        if(timer >= random_time) {
        	addExtraLife();
        	timer = 0;
        }
        //time of extra live delete
        if(timer == TIME_LIMIT && haveExtraLive || player.getExtraLife()) {
        	deleteExtraLife();
        	player.setGetExtraLive(false);
        }
	}
	//override render to render all sprities
	public void render(Graphics g) {
		for (Sprite sprite : sprites) {
			sprite.render();
		}
		
	}
}
