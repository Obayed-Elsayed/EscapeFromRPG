package levelMaker;

public class Platform implements Collidable {

	private int xPosition;
	private int yPosition;
	private int width;
	private int height;
	
	// <Idea> for different platform Styles can add some sort mapper / handler that loads corresponding style?
	
	public Platform(int x, int y, int width, int height) {
		this.xPosition = x;
		this.yPosition = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public boolean isCollidable() {
		return true;
	}

	@Override
	public boolean isColliding(int xPosition, int yPosition, int width, int height) {
		return false;
	}

	public int getxPosition() { return xPosition;}

	public void setxPosition(int xPosition) { this.xPosition = xPosition;}

	public int getyPosition() {	return yPosition;}

	public void setyPosition(int yPosition) {this.yPosition = yPosition;}

	public int getWidth() { return width;}

	public void setWidth(int width) { this.width = width;}

	public int getHeight() { return height;}

	public void setHeight(int height) { this.height = height;}

}
