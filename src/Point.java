//This class allows points to be created with x and y values
public class Point {
	private int x;
	private int y;
	
	//this constructor constructs the points
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//This method checks if two points are the same by comparing the x and y values
	public boolean equals(Point p) {
		return (this.x == p.getX()) && (this.y == p.getY());
	}
	
	//These getters return the x and y values of the point
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
