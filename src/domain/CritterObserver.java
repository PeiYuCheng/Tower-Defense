package domain;

import java.util.Observable;
import java.util.Observer;

import critterModels.Critter;

public class CritterObserver implements Observer {

	private Critter observedCritter;

	public CritterObserver(Critter newObservedCritter) {
		this.observedCritter = newObservedCritter;
		newObservedCritter.addObserver(this);
	}

	public void attackCritter() {
		if (!observedCritter.isDead()) {
			System.out.println("The Tower hit the "
					+ observedCritter.getClass().getSimpleName()
					+ " critter and " + "the Critter's health is "
					+ observedCritter.getHealth());
		} else {
			System.out.println("The "
					+ observedCritter.getClass().getSimpleName()
					+ " Critter is dead!!!");
			System.out.println();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		attackCritter();
	}

}
