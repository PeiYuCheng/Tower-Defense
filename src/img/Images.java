package img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {

	private BufferedImage regularCritterImage = null;
	
	public Images() {
		// TODO Auto-generated constructor stub
	}
	
	public void loadImages() {
		
		try {
			regularCritterImage = ImageIO.read(new File("menu background.jpg"));
		} catch (IOException e) {
		}
		
	}

}
