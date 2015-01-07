import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;

public class Mandlebrot {
	private static final int maxIterations = 1000, zoom = 20;
	private Vector real, imaginary;
	
	// Creates a new mandlebrot set
	public Mandlebrot() {
		this.real = new Vector(-2.6, 1.4);
		this.imaginary = new Vector(-2, 2);
	}
	
	// Draws a new mandlebrot set
	public void drawSet(Graphics g) {
		double realC, imagC;
		for(int x = 0; x < Fractal.SCREEN_SIZE_X; x++) {
			realC = map(x, 0, Fractal.SCREEN_SIZE_X, this.real.getX(), this.real.getY());
			for(int y = 0; y < Fractal.SCREEN_SIZE_Y; y++) {
				imagC = map(y, 0, Fractal.SCREEN_SIZE_Y, this.imaginary.getX(), this.imaginary.getY());
				double realZ = 0, imagZ = 0;
				int iterations = 0;
				boolean converges = true;
				while(iterations < Mandlebrot.maxIterations && converges) {
					if(Math.sqrt(realZ * realZ + imagZ + imagZ) > 2) {
						converges = false;
					} else {
						double t = realZ;
						realZ = realZ * realZ - imagZ * imagZ + realC;
						imagZ = 2 * t * imagZ + imagC;
					}
					iterations++;
				}
				if(iterations > 255) {
					iterations = 255;
				}
				g.setColor(new Color(iterations, iterations, iterations));
				g.drawLine(x, y, x, y);
			}
		}
	}
	
	// Zooms in on the fractal at a specific part
	public void zoom(double x, double y) {
		double t = this.real.getX();
		this.real.setX(map(x - this.zoom, 0, Fractal.SCREEN_SIZE_X, this.real.getX(), this.real.getY()));
		this.real.setY(map(x + this.zoom, 0, Fractal.SCREEN_SIZE_Y, t, this.real.getY()));
		t = this.imaginary.getX();
		this.imaginary.setX(map(y - this.zoom, 0, Fractal.SCREEN_SIZE_X, this.imaginary.getX(), this.imaginary.getY()));
		this.imaginary.setY(map(y + this.zoom, 0, Fractal.SCREEN_SIZE_Y, t, this.imaginary.getY()));
	}
	
	// Maps a value to a new range
	private double map(double n, double a, double b, double c, double d) {
		return c + (n - a) * (d - c) / (b - a);
	}
}