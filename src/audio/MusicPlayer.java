package audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.*;

public class MusicPlayer {

	private static File mainMenuBGM = new File("src/audio/Corncob.wav");
	private static File inBetweenWavesBGM = new File("src/audio/In Between Waves.wav");
	private static File mapMakingBGM = new File("src/audio/Making a Map.wav");
	private static File waveBGM = new File("src/audio/Wave.wav");
	
	private static File currentFile;
	
	private static Clip BGM_clip;
	
	public MusicPlayer() {}
	
	public static void stopBGM() {
		if (BGM_clip != null) {
			BGM_clip.stop();
		}
	}
	
	public static void playMainMenuBGM() {
		stopBGM();
		music(mainMenuBGM);
		changeBGMVolume(-10);
	}
	
	public static void playInBetweenWavesBGM() {
		if (!currentFile.equals(inBetweenWavesBGM)) {
			stopBGM();
			music(inBetweenWavesBGM);
			changeBGMVolume(-10);
		}
	}
	
	public static void playMapMakingBGM() {
		stopBGM();
		music(mapMakingBGM);
		changeBGMVolume(0);
	}
	
	public static void playWaveBGM() {
		if (!currentFile.equals(waveBGM)) {
			stopBGM();
			music(waveBGM);
			changeBGMVolume(0);
		}
	}
	
	public static void music(File soundFile) {

		currentFile = soundFile;
		
		 try {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
				BGM_clip = AudioSystem.getClip();
				BGM_clip.open(audioIn);
				BGM_clip.start();
				BGM_clip.loop(Clip.LOOP_CONTINUOUSLY);
	     }
		 catch (IOException e) {
		         e.printStackTrace();
	     }
		 catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	     }
		 catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
		
    }
	
	public static void changeBGMVolume(float volume) {
		FloatControl gainControl = (FloatControl) BGM_clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(volume);
	}

}
