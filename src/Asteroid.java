/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */

//
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

//Obstacles are all practically the same
public class Asteroid extends Obstacle {
	public Asteroid(double x, double y, double w, double h, double vx, double vy) {
		super(setImage(), x, y, w, h, vx, vy);

	}
	//Set image
	private static Image setImage() {
		Image img = null;
		try {
			int num = 1;
			if(Math.random() <= 0.3) {
				num = 2;
			}
			else if(Math.random() <= 0.6) {
				num = 3;
			}
			img = new Image(new FileInputStream("media/images/obstacles/asteroid" + num + ".png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	//Applies special hitbox here if needed
	@Override
	public boolean checkPlayerCollision(Player sprite) {
       return checkCollision(sprite);
	}
	
	//Overrides fromTop method
	@Override
	public boolean fromTop() {
		return false;
	}		
}
