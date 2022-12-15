package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entity.base.Updateable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public class RenderableHolder {


	//images
	public static Image selectedBg;
	public static Image titleTxt;
	public static Image startTxt;
	public static Image howtoplayTxt;
	public static Image arrow;
	public static Image playTxt;
	public static Image player1;
	public static Image player2;
	public static Image player3;
	public static Image meteorite;
	public static Image enemy;
	public static Image clock;
	public static Image bomb;
	public static Image bombItem;
	public static Image bulletItem;
	public static Image pausedTxt;
	public static Image resumeTxt;
	public static Image exitTxt;
	public static Image yesTxt;
	public static Image noTxt;
	public static Image timeTxt;
	public static Image upTxt;
	public static Image playagainTxt;
	public static Image pausedBtn;
	public static Image shot1;
	public static Image shot2;
	public static Image shot3;
	public static Image bombshot;
	public static Image howToPlay;
	public static Image mainGameBg;
	
	//gif
	public static Image homeBg;
	public static Image destroyGif;
	
	//sound
	public static AudioClip homeSound;
	public static AudioClip onPressedSound;
	public static AudioClip onClickSound;
	public static AudioClip stopSound;
	public static AudioClip mainGameSound;
	public static AudioClip destroySound;
	public static AudioClip collectedSound;
	public static AudioClip errorSound;
	public static AudioClip mouseEnteredSound;
	public static AudioClip laserGunSound;
	
	//font
	public static Font endgameFont;
	
	//font path
	public static String gameFontPath;
	
	static {
		loadResource();
	}
	public static void loadResource() {
		String image = "image/";
		String sound = "sound/";
		String font = "font/";
		
		//image
		selectedBg = new Image(ClassLoader.getSystemResource(image+"selectedBg.jpg").toString());
		titleTxt = new Image(ClassLoader.getSystemResource(image+"text-space_guardian.png").toString());
		startTxt = new Image(ClassLoader.getSystemResource(image+"text-start.png").toString());
		howtoplayTxt = new Image(ClassLoader.getSystemResource(image+"text-how_to_play.png").toString());
		arrow = new Image(ClassLoader.getSystemResource(image+"arrow.png").toString());
		playTxt = new Image(ClassLoader.getSystemResource(image+"text-play.png").toString());
		player1 = new Image(ClassLoader.getSystemResource(image+"player1.png").toString());
		player2 = new Image(ClassLoader.getSystemResource(image+"player2.png").toString());		
		player3 = new Image(ClassLoader.getSystemResource(image+"player3.png").toString());
		meteorite = new Image(ClassLoader.getSystemResource(image+"meteorite.png").toString());
		enemy = new Image(ClassLoader.getSystemResource(image+"enemy.png").toString());
		clock = new Image(ClassLoader.getSystemResource(image+"clock.png").toString());
		bomb = new Image(ClassLoader.getSystemResource(image+"bomb.png").toString());
		bombItem = new Image(ClassLoader.getSystemResource(image+"bombitem.png").toString());
		bulletItem = new Image(ClassLoader.getSystemResource(image+"bulletitem.png").toString());
		resumeTxt = new Image(ClassLoader.getSystemResource(image+"text-resume.png").toString());
		exitTxt = new Image(ClassLoader.getSystemResource(image+"text-exit.png").toString());
		yesTxt = new Image(ClassLoader.getSystemResource(image+"text-yes.png").toString());
		noTxt = new Image(ClassLoader.getSystemResource(image+"text-no.png").toString());
		timeTxt = new Image(ClassLoader.getSystemResource(image+"text-time.png").toString());
		upTxt = new Image(ClassLoader.getSystemResource(image+"text-up.png").toString());
		playagainTxt = new Image(ClassLoader.getSystemResource(image+"text-play_again.png").toString());
		pausedTxt = new Image(ClassLoader.getSystemResource(image+"text-paused.png").toString());
		pausedBtn = new Image(ClassLoader.getSystemResource(image+"pause.png").toString());
		shot1 = new Image(ClassLoader.getSystemResource(image+"shot1.png").toString());
		shot2 = new Image(ClassLoader.getSystemResource(image+"shot2.png").toString());
		shot3 = new Image(ClassLoader.getSystemResource(image+"shot3.png").toString());
		bombshot = new Image(ClassLoader.getSystemResource(image+"bombshot.png").toString());
		howToPlay = new Image(ClassLoader.getSystemResource(image+"howtoplay.png").toString());
		mainGameBg = new Image(ClassLoader.getSystemResource(image+"spaceBg2.png").toString());
		
		//gif
		homeBg = new Image(ClassLoader.getSystemResource(image+"spaceBg.gif").toString());
		destroyGif = new Image(ClassLoader.getSystemResource(image+"destroy.gif").toString());
		
		//sound
		homeSound = new AudioClip(ClassLoader.getSystemResource(sound+"homesound.mp3").toString());
		mainGameSound = new AudioClip(ClassLoader.getSystemResource(sound+"maingamesound.mp3").toString());
		onClickSound = new AudioClip(ClassLoader.getSystemResource(sound+"buttonsound.mp3").toString());
		stopSound = new AudioClip(ClassLoader.getSystemResource(sound+"stopsound.mp3").toString());
		collectedSound = new AudioClip(ClassLoader.getSystemResource(sound+"collectedsound.mp3").toString());
		mouseEnteredSound = new AudioClip(ClassLoader.getSystemResource(sound+"mouseenteredsound.mp3").toString());
		mouseEnteredSound.setVolume(0.3);
		errorSound = new AudioClip(ClassLoader.getSystemResource(sound+"errorsound.mp3").toString());
		errorSound.setVolume(0.3);
		destroySound = new AudioClip(ClassLoader.getSystemResource(sound+"destroysound.mp3").toString());
		destroySound.setVolume(0.5);
		laserGunSound = new AudioClip(ClassLoader.getSystemResource(sound+"lasergunsound.mp3").toString());
		laserGunSound.setVolume(0.5);
		
		//fontpath
		gameFontPath = ClassLoader.getSystemResource(font + "OldSchoolAdventures-42j9.ttf").toString();
		
		//font
		endgameFont = Font.loadFont(ClassLoader.getSystemResource(font + "SpaceRacer-DOPlR.otf").toString(), 20);
	}

}
