package gui;

import java.util.ArrayList;
import java.util.Random;

public class Items extends javafx.scene.shape.Circle{
	private final String shieldURL;
	private final String healURL;
	private final String bulletURL;
	private final String bombURL;
	public Items() {
		super();
		this.shieldURL = "shield.png";
		this.healURL = "heal.png";
		this.bulletURL = "bullet.png";
		this.bombURL = "bomb.png";
		this.prefHeight(30);
		this.prefWidth(30);
	}
	void randominitialize() {
		
	}
	public void givenList_whenNumberElementsChosen_shouldReturnRandomElementsRepeat() {
	    Random rand = new Random();
	    ArrayList<String> givenList = new ArrayList();

	    int numberOfElements = 2;

	    for (int i = 0; i < numberOfElements; i++) {
	        int randomIndex = rand.nextInt(givenList.size());
	        String randomElement = givenList.get(randomIndex);
	    }
	}
	
}
