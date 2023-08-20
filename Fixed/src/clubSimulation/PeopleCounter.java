package clubSimulation;
import java.util.concurrent.atomic.*;

public class PeopleCounter {
	private final AtomicInteger peopleOutSide; //counter for people arrived but not yet in the building
	private final AtomicInteger peopleInside; //counter for patrons inside club
	private final AtomicInteger peopleLeft; //counter for patrons who have left the club
	private final AtomicInteger maxPeople; //maximum patrons allowed in the club at one time
	
	PeopleCounter(int max) {
		peopleOutSide = new AtomicInteger(0);
		peopleInside =new AtomicInteger(0);
		peopleLeft = new AtomicInteger(0);
		maxPeople = new AtomicInteger(max);
	}
		
	public int getWaiting() {
		return peopleOutSide.get();
	}

	public int getInside() {
		return peopleInside.get();
	}
	
	public int getTotal() {
		return (peopleOutSide.get() + peopleInside.get() + peopleLeft.get());
	}

	public int getLeft() {
		return peopleLeft.get();
	}
	
	public int getMax() {
		return maxPeople.get();
	}
	
	//someone arrived outside
	public void personArrived() {
		peopleOutSide.getAndIncrement();
	}
	
	//someone got inside
	public void personEntered() {
		peopleOutSide.getAndDecrement();
		peopleInside.getAndIncrement();
	}

	//someone left
	public void personLeft() {
		peopleInside.getAndDecrement();
		peopleLeft.getAndIncrement();
	}

	//too many people inside
	public boolean overCapacity() {
		return peopleInside.get() >= maxPeople.get();
    }
	
	//not used
	public void resetScore() {
		peopleInside.set(0);
		peopleOutSide.set(0);
		peopleLeft.set(0);
	}
}
