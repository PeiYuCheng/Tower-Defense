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
	
	public MusicPlayer() {
		// TODO Auto-generated constructor stub
	}
	
	public static void stopBGM() {
		if (BGM_clip != null) {
			BGM_clip.stop();
		}
	}
	
	public static void playMainMenuBGM() {
		stopBGM();
		music(mainMenuBGM);
	}
	
	public static void playInBetweenWavesBGM() {
		if (!currentFile.equals(inBetweenWavesBGM)) {
			stopBGM();
			music(inBetweenWavesBGM);
		}
	}
	
	public static void playMapMakingBGM() {
		stopBGM();
		music(mapMakingBGM);
	}
	
	public static void playWaveBGM() {
		stopBGM();
		music(waveBGM);
	}
	
	public static void music(File soundFile) {

		currentFile = soundFile;
		
		 try {
	         // Open an audio input stream.
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
	         // Get a sound clip resource.
				BGM_clip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
				BGM_clip.open(audioIn);
				BGM_clip.start();
				BGM_clip.loop(Clip.LOOP_CONTINUOUSLY);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
		
    }

}
