package critter;

import java.util.Observable;
import java.util.Observer;

public class CritterObserver implements Observer{
	
	private Critter observedCritter;

	public CritterObserver (Critter newObservedCritter) {
		this.observedCritter = newObservedCritter;
		newObservedCritter.addObserver(this);
	}
	
	public void attackCritter() {
		if (!observedCritter.isDead()) {
			System.out.println("The Tower hit the Critter and " + "\nThe Critter's health is " + observedCritter.getHealth());
		}
		else {
			System.out.println("The Critter is dead!!!");
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		attackCritter();
	}

}
