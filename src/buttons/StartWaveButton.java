package buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Queue;

import map.Map;
import critterModels.BossCritter;
import critterModels.Critter;
import critterModels.LargeCritter;
import critterModels.MediumCritter;
import critterModels.RegularCritter;
import domain.CritterWaveFactory;

public class StartWaveButton extends ClickButton implements Serializable {

	public StartWaveButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void drawButton(Graphics g) {
		selectColor(g, BUTTON_COLOR);
		g.fillRect(position.x, position.y, size.width, size.height);
		g.setColor(Color.white);
		g.drawRect(position.x, position.y, size.width, size.height);
		g.drawString("Start Wave", position.x, position.y + 45);
	}
	
	@Override
	protected void OnRelease() {
		button_selector.setStartWave(true);
		setSelected(false);
	}
	
	public void displayWaveInfo(Graphics g, int waveNumber, Map map) {

		Queue<Critter> wave = CritterWaveFactory.getInstance().createWave(waveNumber, map);
		Iterator<Critter> it = wave.iterator();
		Critter reg = new RegularCritter(0,0);
		Critter med = new MediumCritter(0,0);
		Critter lrg = new LargeCritter(0, 0);
		Critter boss = new BossCritter(0, 0);
		int count = 0;
		//TODO: If waveButton is hovered over show next Wave stats
		if (hovered) {
			g.drawString("Wave Info", 10, 220);
			if (it.next().getClass().equals(reg))
				g.drawString("Regular Critter: " + count++, 10, 230);
			if (it.next().getClass().equals(med)) {
				count = 0;
				g.drawString("Medium Critter: " + count++, 10, 240);
			}
			if (it.next().getClass().equals(lrg)) {
				count = 0;
				g.drawString("Large Critter: " + count++, 10, 250);
			}
			if (it.next().getClass().equals(boss))
				count = 0;
			g.drawString("Boss Critter: " + count++, 10, 260);
		}
	}
}
