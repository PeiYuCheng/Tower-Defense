import java.util.Observable;

import critter.Critter;
import critter.CritterObserver;
import critter.Medium;

public class CritterDriver {

	public static void main(String[] args) {

		// Critter regular = new Regular(0, 0);
		// Critter boss = new Boss(0, 0);
		// Player p = new Player();
		// CritterFactory wave1 = new CritterFactory(1);
		// CritterFactory wave15 = new CritterFactory(15);
		// CritterFactory wave25 = new CritterFactory(25);
		// CritterFactory wave50 = new CritterFactory(50);
		//
		// System.out.println("Regular Critter test");
		// regular.setHealth(0);
		// if (regular.isDead())
		// System.out.println("Case 1: PASS");
		// regular.reward(p);
		// if (p.cash == regular.getReward())
		// System.out.println("Case 2: PASS");
		//
		// regular.setHealth(50);
		// if (!regular.isDead())
		// System.out.println("Case 3: PASS");
		// regular.reward(p);
		// if (p.cash == regular.getReward())
		// System.out.println("Case 4: PASS");
		//
		// if (regular.isHit() && regular.getHealth() == 40)
		// System.out.println("Case 5: PASS");
		//
		// if (!regular.hasReachedExit())
		// System.out.println("Case 6: PASS");
		// regular.setPosX(Tile.END_TILE[0]);
		// regular.setPosY(Tile.END_TILE[1]);
		// if (regular.hasReachedExit())
		// System.out.println("Case 7: PASS");
		//
		// regular.damage(p);
		// if (p.lives == 9)
		// System.out.println("Case 8: PASS");
		//
		// p = new Player();
		// System.out.println();
		// System.out.println("boss Critter test");
		// boss.setHealth(0);
		// if (boss.isDead())
		// System.out.println("Case 1: PASS");
		// boss.reward(p);
		// if (p.cash == boss.getReward())
		// System.out.println("Case 2: PASS");
		//
		// boss.setHealth(50);
		// if (!boss.isDead())
		// System.out.println("Case 3: PASS");
		// boss.reward(p);
		// if (p.cash == boss.getReward())
		// System.out.println("Case 4: PASS");
		//
		// if (boss.isHit() && boss.getHealth() == 40)
		// System.out.println("Case 5: PASS");
		//
		// if (!boss.hasReachedExit())
		// System.out.println("Case 6: PASS");
		// boss.setPosX(Tile.END_TILE[0]);
		// boss.setPosY(Tile.END_TILE[1]);
		// if (boss.hasReachedExit())
		// System.out.println("Case 7: PASS");
		//
		// boss.damage(p);
		// if (p.lives <= 0)
		// System.out.println("Case 8: PASS");
		//
		// System.out.println();
		// System.out.println("CritterFactory test");
		// wave1.startWalking();
		// if (wave1.getGroup().isEmpty())
		// System.out.println("Case 1: PASS");
		//
		// System.out.println();
		// wave15.startWalking();
		// if (wave15.getGroup().isEmpty())
		// System.out.println("Case 2: PASS");
		//
		// System.out.println();
		// wave25.startWalking();
		// if (wave25.getGroup().isEmpty())
		// System.out.println("Case 3: PASS");
		//
		// System.out.println();
		// wave50.startWalking();
		// if (wave50.getGroup().isEmpty())
		// System.out.println("Case 4: PASS");
		
		Critter observedCritter1 = new Medium(10, 10);
		CritterObserver newObserver1 = new CritterObserver(observedCritter1);
		
		for (int i = 0; i < 20; i++) {
			observedCritter1.setHealth(observedCritter1.getHealth() - 10);
			observedCritter1.isHit();
		}
		
	}
}
