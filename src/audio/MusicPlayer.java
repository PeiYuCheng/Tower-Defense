package audio;

import java.io.FileInputStream;
import java.io.IOException;

import sun.audio.*;

public class MusicPlayer {

	public MusicPlayer() {
		// TODO Auto-generated constructor stub
	}
	
	public static void music() {  
        AudioPlayer MGP = AudioPlayer.player;  
        AudioStream BGM;  
        AudioData MD;  
        ContinuousAudioDataStream loop = null;  

        try {  
            BGM = new AudioStream(new FileInputStream("Corncob.mp3"));  
            MD = BGM.getData();  
            loop = new ContinuousAudioDataStream(MD);  
        } catch(IOException error)  {  
            System.out.println("Error!!!");  

        }  
        MGP.start(loop);  
    }  

}
