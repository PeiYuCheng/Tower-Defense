package img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {

	private static Images images;
	public BufferedImage regularCritterImage;
	public BufferedImage menuBackground;
	
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
			regularCritterImage = ImageIO.read(new File("src/img/regularCritter.png"));
			menuBackground = ImageIO.read(new File("src/img/menuBackground.jpg"));
		} catch (IOException e) {
			System.out.println("NO");
		}
	}
}
