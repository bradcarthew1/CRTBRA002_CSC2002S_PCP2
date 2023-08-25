package SynchronizedClubSimulation;
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
		//ensures people inside and outside counters are together when Clubgoer enters
		synchronized (peopleInside) {
			synchronized (peopleOutSide) {
				peopleOutSide.getAndDecrement();
			}
			peopleInside.getAndIncrement();
		}
	}

	//someone left
	public void personLeft() {
		//ensures people inside and left counters are together when Clubgoer leaves
		synchronized (peopleInside) {
			peopleInside.getAndDecrement();
			synchronized (peopleLeft) {
				peopleLeft.getAndIncrement();
			}
		}
	}

	//too many people inside
	public boolean overCapacity() {
		return peopleInside.get() >= maxPeople.get();
    }
}
