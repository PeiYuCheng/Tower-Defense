package img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Images implements Serializable {

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

//	public ImageIcon menuBackgroundII;
//	public ImageIcon moneyIconII;
//	public ImageIcon healthIconII;
//	public ImageIcon upgradeIconII;
//	public ImageIcon sellIconII;
//	public ImageIcon attackModeIconII;
//	public ImageIcon newWaveIconII;
//	public ImageIcon regularTowerLevelOneII;
//	public ImageIcon regularTowerLevelTwoII;
//	public ImageIcon regularTowerLevelThreeII;
//	public ImageIcon splashTowerLevelOneII;
//	public ImageIcon splashTowerLevelTwoII;
//	public ImageIcon splashTowerLevelThreeII;
//	public ImageIcon radialTowerLevelOneII;
//	public ImageIcon radialTowerLevelTwoII;
//	public ImageIcon radialTowerLevelThreeII;
//	public ImageIcon regularCritterDownII;
//	public ImageIcon regularCritterLeftII;
//	public ImageIcon regularCritterUpII;
//	public ImageIcon regularCritterRightII;
//	public ImageIcon mediumCritterDownII;
//	public ImageIcon mediumCritterLeftII;
//	public ImageIcon mediumCritterUpII;
//	public ImageIcon mediumCritterRightII;
//	public ImageIcon largeCritterDownII;
//	public ImageIcon largeCritterLeftII;
//	public ImageIcon largeCritterUpII;
//	public ImageIcon largeCritterRightII;
	
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
			largeCritterDown = ImageIO.read(new File("src/img/largeCritterD.gif"));
			largeCritterLeft = ImageIO.read(new File("src/img/largeCritterL.gif"));
			largeCritterUp = ImageIO.read(new File("src/img/largeCritterU.gif"));
			largeCritterRight = ImageIO.read(new File("src/img/largeCritterR.gif"));
			
			
//			menuBackgroundII = new ImageIcon(menuBackground);
//			
//			moneyIconII = new ImageIcon(moneyIcon);
//			healthIconII = new ImageIcon(healthIcon);
//			upgradeIconII = new ImageIcon(upgradeIcon);
//			sellIconII = new ImageIcon(sellIcon);
//			attackModeIconII = new ImageIcon(attackModeIcon);
//			newWaveIconII = new ImageIcon(newWaveIcon);
//			
//			regularTowerLevelOneII = new ImageIcon(regularTowerLevelOne);
//			regularTowerLevelTwoII = new ImageIcon(regularTowerLevelTwo);
//			regularTowerLevelThreeII = new ImageIcon(regularTowerLevelThree);
//			splashTowerLevelOneII = new ImageIcon(splashTowerLevelOne);
//			splashTowerLevelTwoII = new ImageIcon(splashTowerLevelTwo);
//			splashTowerLevelThreeII = new ImageIcon(splashTowerLevelThree);
//			radialTowerLevelOneII = new ImageIcon(radialTowerLevelOne);
//			radialTowerLevelTwoII = new ImageIcon(radialTowerLevelTwo);
//			radialTowerLevelThreeII = new ImageIcon(radialTowerLevelThree);
//			
//			regularCritterDownII = new ImageIcon(regularCritterDown);
//			regularCritterLeftII = new ImageIcon(regularCritterLeft);
//			regularCritterUpII = new ImageIcon(regularCritterUp);
//			regularCritterRightII = new ImageIcon(regularCritterRight);
//			mediumCritterDownII = new ImageIcon(mediumCritterDown);
//			mediumCritterLeftII = new ImageIcon(mediumCritterLeft);
//			mediumCritterUpII = new ImageIcon(mediumCritterUp);
//			mediumCritterRightII = new ImageIcon(mediumCritterRight);
//			largeCritterDownII = new ImageIcon(largeCritterDown);
//			largeCritterLeftII = new ImageIcon(largeCritterLeft);
//			largeCritterUpII = new ImageIcon(largeCritterUp);
//			largeCritterRightII = new ImageIcon(largeCritterRight);
			
		} catch (IOException e) {
			System.out.println("NO");
		}
	}
}
