
//Created by Sidharth Lakshmanan, Yash Koda, Ranveer Rhandhwa, Prabhat Aluri
// APCS P.5
//
//This class represents the class that runs the Tron game as well as the class that is used for the listeners
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;

public class Game extends JComponent implements KeyListener, ActionListener {
	private Bike p1 = new Bike(750, 400, 'L', 37, 38, 39, 40, 80, Color.BLUE, 3);
	private Bike p2 = new Bike(250, 400, 'R', 83, 69, 70, 68, 81, Color.ORANGE, 3);
	private Timer t1 = new Timer(20, this);
	private int p1score = 0;
	private int p2score = 0;
	private boolean endScreen = false;
	private Sound sound = new Sound();

	//This main method created the frame as well as initialized the listeners
	public static void main(String[] args) {
		JFrame window = new JFrame("Tron");
		Game game = new Game();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setSize(1000, 1000);
		window.setVisible(true);
		window.add(game);
		game.restart("start");
		window.addKeyListener(game);

	}
	
	//this draws the bikes and the layout
	public void paintComponent(Graphics g) {
		if (endScreen) {
			layout(g);
			if (p1score > p2score) {
				g.setColor(p1.getC());
			} else if (p2score > p1score) {
				g.setColor(p2.getC());
			} else {
				g.setColor(Color.GRAY);
			}
			g.fillRect(0, 0, 1000, 800);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Calibri", Font.BOLD, 50));
			g.drawString("Thanks For Playing!!", 260, 400);
		} else {
			layout(g);
			bikes(g);
		}
	}
	
	//This draws the layout of the map
	public void layout(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 800);
		g.setColor(Color.RED);
		g.setFont(new Font("Calibri", Font.BOLD, 60));
		g.drawString("Tron", 400, 900);
		g.setColor(p1.getC());
		g.drawString(p1score + "", 750, 900);
		g.setColor(p2.getC());
		g.drawString(p2score + "", 100, 900);
	}
	
	//This draws the paths and the bikes as well as checks to see if any bike lost
	public void bikes(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(p1.getX(), p1.getY(), 5, 5);
		g.fillRect(p2.getX(), p2.getY(), 5, 5);
		if (p1.getTurns() % 20 != 0) {
			g.setColor(Color.RED);
		} else {
			g.setColor(p1.getC());
		}
		for (int i = 0; i < p1.getTrail().size(); i++) {
			g.fillRect(p1.getTrail().get(i).getX(), p1.getTrail().get(i).getY(), 5, 5);
		}
		if (p2.getTurns() % 20 != 0) {
			g.setColor(Color.RED);
		} else {
			g.setColor(p2.getC());
		}
		for (int i = 0; i < p2.getTrail().size(); i++) {
			g.fillRect(p2.getTrail().get(i).getX(), p2.getTrail().get(i).getY(), 5, 5);
		}
		int i = 0;
		if (p1.getX() > 995 || p1.getY() > 795 || p1.getX() < 0 || p1.getY() < 0 || p1.touch(p1.getTrail())
				|| p1.touch(p2.getTrail())) {
			i++;
		}
		if (p2.getX() > 995 || p2.getY() > 795 || p2.getX() < 0 || p2.getY() < 0 || p2.touch(p2.getTrail())
				|| p2.touch(p1.getTrail())) {
			i += 2;
		}
		if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
			i += 3;
		}
		if (i == 1) {
			p2score++;
			t1.stop();
			restart("Orange Won!! ");
		} else if (i == 2) {
			p1score++;
			t1.stop();
			restart("Blue Won!! ");
		} else if (i >= 3) {
			t1.stop();
			restart("It was just a draw... ");
		}

	}
	
	//This method created the dialog box signifying the end of the game/restarting the game
	private void restart(String winner) {
		sound.play(0);
		if (!winner.equals("start")) {
			sound.play(1);
			UIManager.put("OptionPane.minimumSize", new Dimension(500, 500));
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Calibri", Font.BOLD, 36)));
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Calibri", Font.BOLD, 20)));
			int selected = JOptionPane.showConfirmDialog(this, winner + "Do you want to play again?");
			if (selected == JOptionPane.YES_OPTION) {
				p1 = new Bike(750, 400, 'L', 37, 38, 39, 40, 80, p1.getC(), 3);
				p2 = new Bike(250, 400, 'R', 83, 69, 70, 68, 81, p2.getC(), 3);
				t1 = new Timer(20, this);
				sound.play(2);
				t1.start();
			} else {
				t1 = new Timer(20, this);
				endScreen = true;
				sound.play(0);
				t1.start();
			}
		} else {
			sound.play(1);
			UIManager.put("OptionPane.minimumSize", new Dimension(500, 500));
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Calibri", Font.BOLD, 36)));
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Calibri", Font.BOLD, 20)));
			int selected = JOptionPane.showConfirmDialog(this,
					"P1:ESDF, Boost: Q\nP2:ARROW KEYS, Boost: P\nAre You Ready?");
			if (selected == JOptionPane.YES_OPTION) {
				sound.play(2);
				t1.start();
			} else {
				restart("start");
			}
		}
	}
	
	//This is the time based listener that refreshes the screen to create movement of the bikes
	public void actionPerformed(ActionEvent e) {
		if (p1.getTurns() % 20 != 0) {
			p1.addTrail();
			p1.updateX();
			p1.updateY();
			p1.setTurns(p1.getTurns() - 1);
			sound.play(3);
		} else if (p2.getTurns() % 20 != 0) {
			p2.addTrail();
			p2.updateX();
			p2.updateY();
			p2.setTurns(p2.getTurns() - 1);
			sound.play(4);
		}
		p1.addTrail();
		p2.addTrail();
		p1.updateX();
		p1.updateY();
		p2.updateX();
		p2.updateY();
		repaint();
	}
	
	//This checks to see if a key has been pressed
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		getKey(key, p1);
		getKey(key, p2);
	}
	
	//This checks to see if the key that was pressed moves the bikes and if so, how
	private void getKey(int key, Bike a) {
		if (a.count % 2 == 1) {
			if (key == a.getA1()) {
				a.setDx(-5);
				a.setDy(0);
				a.count++;
			} else if (key == a.getA3()) {
				a.setDx(5);
				a.setDy(0);
				a.count++;
			}
		} else {
			if (key == a.getA2()) {
				a.setDx(0);
				a.setDy(-5);
				a.count++;
			} else if (key == a.getA4()) {
				a.setDx(0);
				a.setDy(5);
				a.count++;
			}
		}
		if (key == a.getB() && a.getTurns() % 20 == 0 && a.getTurns() > 0) {
			a.setTurns(a.getTurns() - 1);
		}
	}
	//These are methods that were required but not needed for the key listener to work
	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

}
