package img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {

	private static Images images;
	
	// menu
	public BufferedImage menuBackground;
	
	// side menu icons
	public BufferedImage moneyIcon;
	public BufferedImage healthIcon;
	public BufferedImage upgradeIcon;
	public BufferedImage sellIcon;
	public BufferedImage attackModeIcon;
	public BufferedImage newWaveIcon;
	
	// towers
	public BufferedImage regularTowerLevelOne;
	public BufferedImage regularTowerLevelTwo;
	public BufferedImage regularTowerLevelThree;
	public BufferedImage splashTowerLevelOne;
	public BufferedImage splashTowerLevelTwo;
	public BufferedImage splashTowerLevelThree;
	public BufferedImage radialTowerLevelOne;
	public BufferedImage radialTowerLevelTwo;
	public BufferedImage radialTowerLevelThree;
	
	// critters
	public BufferedImage regularCritterRight;
	public BufferedImage regularCritterLeft;
	public BufferedImage regularCritterUp;
	public BufferedImage regularCritterDown;
	public BufferedImage mediumCritterRight;
	public BufferedImage mediumCritterLeft;
	public BufferedImage mediumCritterUp;
	public BufferedImage mediumCritterDown;
	public BufferedImage largeCritterRight;
	public BufferedImage largeCritterLeft;
	public BufferedImage largeCritterUp;
	public BufferedImage largeCritterDown;
	public BufferedImage bossCritterRight;
	public BufferedImage bossCritterLeft;
	public BufferedImage bossCritterUp;
	public BufferedImage bossCritterDown;
	
	// cell icons
	public BufferedImage grass;
	public BufferedImage path;
	
	private Images() {}
	
	public static Images getUniqueInstance() {
		if (images == null) {
			images = new Images();
			images.loadImages();
		}
		return images;
	}
	
	public void loadImages() {
		try {
			menuBackground = ImageIO.read(new File("src/img/menuBackground.jpg"));
			
			moneyIcon = ImageIO.read(new File("src/img/dogeMoney.png"));
			healthIcon = ImageIO.read(new File("src/img/dogeHeart.png"));
			upgradeIcon = ImageIO.read(new File("src/img/upgradeDoge.jpg"));
			sellIcon = ImageIO.read(new File("src/img/sellDoge.jpg"));
			attackModeIcon = ImageIO.read(new File("src/img/attackModeDoge.jpg"));
			newWaveIcon = ImageIO.read(new File("src/img/newWaveDoge.jpg"));
			
			regularTowerLevelOne = ImageIO.read(new File("src/img/reg1.gif"));
			regularTowerLevelTwo = ImageIO.read(new File("src/img/reg2.gif"));
			regularTowerLevelThree = ImageIO.read(new File("src/img/reg3.gif"));
			splashTowerLevelOne = ImageIO.read(new File("src/img/splash1.gif"));
			splashTowerLevelTwo = ImageIO.read(new File("src/img/splash2.gif"));
			splashTowerLevelThree = ImageIO.read(new File("src/img/splash3.gif"));
			radialTowerLevelOne = ImageIO.read(new File("src/img/radial1.gif"));
			radialTowerLevelTwo = ImageIO.read(new File("src/img/radial2.gif"));
			radialTowerLevelThree = ImageIO.read(new File("src/img/radial3.gif"));
			
			regularCritterDown = ImageIO.read(new File("src/img/regularCritterD.gif"));
			regularCritterLeft = ImageIO.read(new File("src/img/regularCritterL.gif"));
			regularCritterUp = ImageIO.read(new File("src/img/regularCritterU.gif"));
			regularCritterRight = ImageIO.read(new File("src/img/regularCritterR.gif"));
			mediumCritterDown = ImageIO.read(new File("src/img/mediumCritterD.gif"));
			mediumCritterLeft = ImageIO.read(new File("src/img/mediumCritterL.gif"));
			mediumCritterUp = ImageIO.read(new File("src/img/mediumCritterU.gif"));
			mediumCritterRight = ImageIO.read(new File("src/img/mediumCritterR.gif"));
			largeCritterDown = ImageIO.read(new File("src/img/mediumCritterD.gif"));
			largeCritterLeft = ImageIO.read(new File("src/img/mediumCritterL.gif"));
			largeCritterUp = ImageIO.read(new File("src/img/mediumCritterU.gif"));
			
			
			grass = ImageIO.read(new File("src/img/grass.png"));
		} catch (IOException e) {
			System.out.println("NO");
		}
	}
}
