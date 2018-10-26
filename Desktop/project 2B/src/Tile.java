// class contain all tiles (can't move)
public class Tile extends Sprite {
	private static final String GRASS_PATH = "assets/grass.png";
	private static final String WATER_PATH = "assets/water.png";
	private static final String TREE_PATH = "assets/tree.png";
	private static final String FLAG_PATH = "assets/flag.png";
	/**
	 * @param x x position of create tile
	 * @param y y position of create tile
	 * @return Tile objects with location
	 */
	public static Tile createGrassTile(float x, float y) {
		return new Tile(GRASS_PATH, x, y);
	}
	//method of create water tile with HAZARD tag
	/**
	 * @param x x position of create tile
	 * @param y y position of create tile
	 * @return Tile objects with location
	 */
	public static Tile createWaterTile(float x, float y) {
		return new Tile(WATER_PATH, x, y, new String[] { Sprite.WATER });
	}
	/**
	 * @param x x position of create tile
	 * @param y y position of create tile
	 * @return Tile objects with location
	 */
	public static Tile createTreeTile(float x, float y) {
		return new Tile(TREE_PATH, x, y, new String[] { Sprite.SOLID });
	}
	/**
	 * @param x x position of create tile
	 * @param y y position of create tile
	 * @return Tile objects with location
	 */
	public static Tile createFlagTile(float x, float y) {
		return new Tile(FLAG_PATH, x, y, new String[] {Sprite.TERMINAL});
	}
	private Tile(String imageSrc, float x, float y) {		
		super(imageSrc, x, y);
	}
	private Tile(String imageSrc, float x, float y, String[] tags) {		
		super(imageSrc, x, y, tags);
	}
}