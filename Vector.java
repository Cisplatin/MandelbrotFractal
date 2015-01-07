public class Vector {
	private double x, y;
	// Creates a new vector
	Vector(double _x, double _y) {
		this.x = _x;
		this.y = _y;
	}
	// Returns the x value
	public double getX() {
		return this.x;
	}
	// Returns the y value
	public double getY() {
		return this.y;
	}
	// Sets the x value
	public void setX(double n) {
		this.x = n;
	}
	// Sets the y value
	public void setY(double n) {
		this.y = n;
	}
}