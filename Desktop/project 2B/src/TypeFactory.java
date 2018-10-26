
//Usr TypeFactory to initialize every sprite
public class TypeFactory {
	//this part for moving objects
	public static Sprite getObjecttype
		(String type, String xString, String yString, String moveRightString) {
		ConstantMovingObjects objects = null;
		float x = Float.parseFloat(xString);
		float y = Float.parseFloat(yString);
		boolean moveRight = Boolean.parseBoolean(moveRightString);
		if(type.equals( "bus")) {
			objects = new Bus(x,y,moveRight);
		}else if(type.equals("racecar")){
			objects = new Racecar(x,y,moveRight);
		}else if(type.equals("bike")){
			objects = new Bike(x,y,moveRight);
		}else if(type.equals("bulldozer")){
			objects = new Bulldozer(x,y,moveRight);
		}else if(type.equals("longLog")){
			objects = new LongLog(x,y,moveRight);
		}else if(type.equals("log")){
			objects = new Log(x,y,moveRight);
		}else if(type.equals("turtle")){
			objects = new Turtle(x,y,moveRight);
		}else if(type.equals("ExtraLife")){
			objects = new ExtraLife(x,y,moveRight);
		}else {objects = new Bus(x,y,moveRight);}
		return objects;
	}
	//this method for tile objects
	public static Sprite getObjecttype(String type, String xString, String yString) {
		Tile tile = null;
		float x = Float.parseFloat(xString);
		float y = Float.parseFloat(yString);
		if(type.equals("grass")) {
			tile = Tile.createGrassTile(x,y);
		}else if(type.equals("water")){
			tile = Tile.createWaterTile(x,y);
		}else if(type.equals("tree")){
			tile = Tile.createTreeTile(x,y);
		}else {
			tile = Tile.createGrassTile(x,y);
		}
			return tile;
	}

}
