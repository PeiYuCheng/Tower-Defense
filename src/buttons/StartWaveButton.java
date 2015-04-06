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
		
		g.drawImage(images.newWaveIcon.getScaledInstance(size.width, size.height, 0), 0, 0, null);
		super.drawButton(g);

	}
	
	@Override
	protected void OnRelease() {
		button_selector.setStartWave(true);
		setSelected(false);
	}
	
	@Override
	protected void OnEnter() {
		button_selector.setShowNextWaveInfo(true);
		super.OnEnter();
	}
	
	@Override
	protected void OnExit() {
		button_selector.setShowNextWaveInfo(false);
		super.OnExit();
	}
	
}
