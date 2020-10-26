package levelMaker;

public interface Collidable {

	public boolean isCollidable();

	public boolean isColliding(int xPosition, int yPosition, int width, int height);
}
