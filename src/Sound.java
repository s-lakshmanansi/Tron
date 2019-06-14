//This class creates the sound for the bike moving 
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Sound {
	String START_FILE = "src/bike_start.mp3";
	String RIDE_FILE = "src/bike_ride.mp3";
	String BOOST_FILE = "src/fast.mp3";
	// These are the thread the file runs on 
	SoundThread soundThread = null;
	BoostThread p1Thread = null;
	BoostThread p2Thread = null;
	// This play the sound clip over the game flie 
	public void play(int i) {
		switch (i) {
		// Stops all the sound
		case 0:
			if (soundThread != null) {
				soundThread.stop();
			}
			break;
		// Bikes are readying up sound 
		case 1:
			if (soundThread != null) {
				soundThread.stop();
			}
			soundThread = new SoundThread(START_FILE);
			soundThread.start();
			break;
		// Bikes running around sound 
		case 2:
			if (soundThread != null) {
				soundThread.stop();
			}
			soundThread = new SoundThread(RIDE_FILE);
			soundThread.start();
			break;
		// The boost sound for player 1 
		case 3:
			if (p1Thread != null) {
				p1Thread.stop();
			}
			p1Thread = new BoostThread(BOOST_FILE);
			p1Thread.start();
			break;
		// The boost sound for player 2 
		case 4:
			if (p2Thread != null) {
				p2Thread.stop();
			}
			p2Thread = new BoostThread(BOOST_FILE);
			p2Thread.start();
			break;

		default:
			break;
		}
	}
	// This stops the bike boost sound 
	public void stopBike() {
	}
	// Creats the Threads 
	class SoundThread extends Thread {
		Player player = null;
		String fileName = null;
		
		// This get the file 
		public SoundThread(String fileName) {
			try {
				this.fileName = fileName;
				this.player = new Player(new FileInputStream(fileName));
			} catch (Exception e) {
				// log it
			}
		}

		@Override
		// Run the file over and over again
		public void run() {
			do {
				try {
					this.player.play();
					this.player = new Player(new FileInputStream(this.fileName));
				} catch (Exception e) {
					// log it
				}
			} while (true);
		}
	}
	// Creates the Threads 
	class BoostThread extends Thread {
		Player player = null;
		String fileName = null;
		// Gets the file of the boost 
		public BoostThread(String fileName) {
			try {
				this.fileName = fileName;
				this.player = new Player(new FileInputStream(fileName));
			} catch (Exception e) {
				// log it
			}
		}

		@Override
		// runs the boost sound 
		public void run() {
			try {
				this.player.play();
				this.player = new Player(new FileInputStream(this.fileName));
			} catch (Exception e) {
				// log it
			}
		}
	}

}
