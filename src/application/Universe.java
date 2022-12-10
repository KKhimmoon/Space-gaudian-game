package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Universe {
	int posX;
	int posY;
	private int h,w,r,g,b;
	private double opacity;
	public Universe() {
		super();
		posX = SpaceInvaders.RAND.nextInt(SpaceInvaders.WIDTH);
		posY = 0;
		w = SpaceInvaders.RAND.nextInt(5)+1;
		h = SpaceInvaders.RAND.nextInt(5)+1;
		r = SpaceInvaders.RAND.nextInt(100)+150;
		g = SpaceInvaders.RAND.nextInt(100)+150;
		// TODO Auto-generated constructor stub
		opacity = SpaceInvaders.RAND.nextFloat();
		if(opacity < 0) opacity *= -1;
		if(opacity > 0.5) opacity = 0.5;	
	}
	public void draw(GraphicsContext gc) {
		if(opacity >0.8) opacity -= 0.01;
		if(opacity <0.1) opacity = 0.5;	
		
		gc.setFill(Color.rgb(r, g, b,opacity));
		gc.fillOval(posX, posY, w, h);
		posY += 20;
	}
}
