package clubSimulation;

import java.util.concurrent.atomic.AtomicInteger;

// GridBlock class to represent a block in the club.
// only one thread at a time "owns" a GridBlock

public class GridBlock {
	private final AtomicInteger isOccupied; //
	private final boolean isExit;  //is this the exit door?
	private final boolean isBar; //is it a bar block?
	private final boolean isDance; //is it the dance area?
	private int [] coords; //the coordinate of the block.
	
	GridBlock(boolean exitBlock, boolean barBlock, boolean danceBlock) throws InterruptedException {
		isExit = exitBlock;
		isBar = barBlock;
		isDance = danceBlock;
		isOccupied = new AtomicInteger(-1);
	}
	
	GridBlock(int x, int y, boolean exitBlock, boolean refreshBlock, boolean danceBlock) throws InterruptedException {
		this(exitBlock, refreshBlock, danceBlock);
		coords = new int [] {x,y};
	}
	
	public int getX() { return coords[0]; }
	
	public int getY() { return coords[1]; }
	
	public boolean get(int threadID) throws InterruptedException {
		synchronized (isOccupied) {
			if (isOccupied.get() == threadID)
				return true; //thread Already in this block

			if (isOccupied.get() >= 0)
				return false; //space is occupied

			isOccupied.set(threadID);  //set ID to thread that had block
			return true;
		}
	}
		
	public synchronized void release() {
		isOccupied.set(-1);
		notifyAll();
	}
	
	public boolean occupied() {
        return isOccupied.get() != -1;
    }
	
	public boolean isExit() {
		return isExit;	
	}

	public boolean isBar() {
		return isBar;
	}

	public boolean isDanceFloor() {
		return isDance;
	}
}
