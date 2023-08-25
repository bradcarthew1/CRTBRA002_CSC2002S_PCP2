package SynchronizedClubSimulation;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.atomic.*;

public class PeopleLocation  { // this is a separate class so don't have to access thread
	private final int ID; //each person has an ID
	private final Color myColor; //colour of the person
	private final AtomicBoolean inRoom; //are they in the club?
	private final AtomicBoolean arrived; //have they arrived at the club?
	private GridBlock location; //which GridBlock are they on?
	
	PeopleLocation(int ID) {
		Random rand = new Random();
		float c = rand.nextFloat(); //a bit of a hack to get different colours
		myColor = new Color(c, rand.nextFloat(), c); //only set at beginning by thread
		inRoom = new AtomicBoolean(false); //not in club
		arrived = new AtomicBoolean(false); //have not arrive outside
		this.ID = ID;
	}
	
	//setter
	public void setInRoom(boolean in) {
		this.inRoom.set(in);
	}
	
	//getter
	public void setArrived() {
		this.arrived.set(true);;
	}

	//setter - synchronize setting of location property
	public synchronized void setLocation(GridBlock location) {
		this.location = location;
	}

	//getter - synchronize retrieval of x location
	public synchronized int getX() { return location.getX(); }
	
	//getter - synchronize retrieval of y location
	public synchronized int getY() { return location.getY(); }
	
	//getter
	public int getID() { return ID; }

	//getter
	public boolean inRoom() {
		return inRoom.get();
	}

	//getter and setter
	public Color getColor() { return myColor; }
}
