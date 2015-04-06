package img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Images implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BufferedImage regularCritterImage;
	public BufferedImage menuBackground;
	public BufferedImage grass;
	public BufferedImage street;
	
	public Images() {
		loadImages();
	}
	
	public void loadImages() {
		try {
			regularCritterImage = ImageIO.read(new File("src/img/regularCritter.png"));
			menuBackground = ImageIO.read(new File("src/img/menuBackground.jpg"));
			street = ImageIO.read(new File("src/img/street.png"));
			grass = ImageIO.read(new File("src/img/grass.jpg"));
		} catch (IOException e) {
			System.out.println("NO");
		}
	}
}
