//This class creates the bikes as well as stores their paths
import java.awt.Color;
import java.util.ArrayList;

public class Bike{
	private int x = 0;
	private int y = 0;
	private int dx = 0;
	private int dy = 0;
	private ArrayList<Point> trail = new ArrayList<Point>();
	private int a1;
	private int a2;
	private int a3;
	private int a4;
	private int B;
	private Color c;
	private int boosts;
	public int count = 0;
	private int turns;
	
	//This constructor constructs the bikes by needing their position, the keys needed to control them, and the color
	public Bike(int x, int y, char direction, int a1, int a2, int a3, int a4, int B, Color c, int boosts) {
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.a4 = a4;
		this.x = x;
		this.y = y;
		if(direction == 'R') {
			this.dx = 5;
		}
		else {
			this.dx = -5;;
		}
		this.c = c;
		this.boosts = boosts;
		this.turns = this.boosts * 20;
		this.B = B;
	}
	
	//This method checks to see if the bike is hitting a bikes trail
	public boolean touch(ArrayList<Point> trail) {
		Point current = new Point(x, y);
		boolean check = false;
		for(int i = 0; i < trail.size(); i++) {
			if(trail.get(i).equals(current)) {
				check = true;
				break;
			}
		}
		if(trail.size() > 0) {
			for(int i = 0; i < trail.size() - 1; i++) {
				if(trail.get(i).equals(this.trail.get(this.trail.size() - 1))) {
					check = true;
					break;
				}
			}
		}
		return check;
	}
	
	//These getters and setters allow the user to manipulate and get all of the private variables
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void updateX() {
		this.x += dx;
	}

	public void updateY() {
		this.y += dy;
	}
	
	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getA1() {
		return a1;
	}

	public int getA2() {
		return a2;
	}

	public int getA3() {
		return a3;
	}

	public int getA4() {
		return a4;
	}
	
	public int getB() {
		return this.B;
	}

	public Color getC() {
		return c;
	}
	
	public void setC(Color c) {
		this.c = c;
	}

	public ArrayList<Point> getTrail() {
		return this.trail;
	}
	
	public void addTrail() {
		this.trail.add(new Point(x, y));
	}
	
	public int getBoosts() {
		return this.boosts;
	}
	
	public void setBoosts(int boosts) {
		this.boosts = boosts;
	}
	
	public int getTurns() {
		return this.turns;
	}
	
	public void setTurns(int turns) {
		this.turns = turns;
	}
}
