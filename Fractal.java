import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.*;

/*
Fractal Set
Created by Simon Hajjar
Version 1.0

This program will display a fractal set, and allow for zooming on
any clicked portions.
*/

public class Fractal extends JPanel implements MouseListener {
	// Variables ensuring the proper timing of the run() function
	private static final int FRAMES_PER_SECOND = 1;
	private Timer animationTimer;
	private TimerTask animationTask;
	// Variables allowing the running of the JPanel
	public static final int SCREEN_SIZE_X = 650, SCREEN_SIZE_Y = 650;
	private Mandlebrot set;
	private boolean draw;
	// Constructor for the Fractal class
	public Fractal() {
		this.animationTimer = new Timer("Fractal");
		this.animationTask = new AnimationUpdater();
		this.setFocusable(true);
		this.addMouseListener(this);
		
		this.set = new Mandlebrot();
		this.draw = true;
	}
	// Called when the mouse is clicked
    public void mouseClicked(MouseEvent e) {
		this.set.zoom(e.getX(), e.getY());
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
	// Creates a screen of wanted size
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(SCREEN_SIZE_X, SCREEN_SIZE_Y);
    }
    // Starts the program
    public void start() {
        this.animationTimer.schedule(this.animationTask, 0, 1000 / FRAMES_PER_SECOND);
    }
    // Paints all components onto the screen
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		this.set.drawSet(g);
	}
    // The running class, as to continually update animation
    private class AnimationUpdater extends TimerTask {
        @Override
        public void run() {
            repaint();
        }
    }
    // The setup of both the JFrame and Particles
    public static void main(String[] args) {
        JFrame window = new JFrame();
        Fractal fractalEngine = new Fractal();
        window.getContentPane().add(fractalEngine);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        fractalEngine.start();
    }
}