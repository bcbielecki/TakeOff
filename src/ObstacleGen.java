/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */
import java.util.List;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ObstacleGen {
	private GraphicsContext gc;
	private List<Obstacle> obstacles;
	private int counter;
	private int initialSpawn = 10;
	public final int STAGE_1 = 3000;
	public final int STAGE_2 = 6000;
	public final int STAGE_3 = 9000;
	public final int STAGE_4 = 10000;
	
	public ObstacleGen(GraphicsContext gc) {
		this.gc = gc;
		counter = 0;
		
		obstacles = new ArrayList<Obstacle>();
		for(int i = 0; i < initialSpawn; i++) {
			spawn();
		}
		
	}
	
	public void generate(int counter) {
	}
	
	public void moveAll() {
		for(int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).move(gc);
			if(obstacles.get(i).getY() > gc.getCanvas().getHeight() + 50) { //50 as buffer to ensure off screen
				obstacles.remove(i);
				spawn();
			}
		}
	}

	public void spawn() {
		Obstacle obs = null;
		if(counter >= 0 && counter < STAGE_1) {
			obs = new Cloud(Math.random() * gc.getCanvas().getWidth() - 100, Math.random() * -1 * ((gc.getCanvas().getHeight() * 2)) - 2 * 100,
						200.0, 100.0, 0, 2 * Math.random()+ 2.0);
		}
		else if(counter >= STAGE_1 && counter < STAGE_2) {
			obs = new Cloud(Math.random() * gc.getCanvas().getWidth() - 100, Math.random() * -1 * ((gc.getCanvas().getHeight() * 2)) - 2 * 100,
					200.0, 100.0, 0, 2 * Math.random()+ 2.0);
		}
		else if(counter >= STAGE_2 && counter < STAGE_3) {
			obs = new Cloud(Math.random() * gc.getCanvas().getWidth() - 100, Math.random() * -1 * ((gc.getCanvas().getHeight() * 2)) - 2 * 100,
					200.0, 100.0, 0, 2 * Math.random()+ 2.0);
		}
		else if(counter >= STAGE_3 && counter < STAGE_4) {
			obs = new Cloud(Math.random() * gc.getCanvas().getWidth() - 100, Math.random() * -1 * ((gc.getCanvas().getHeight() * 2)) - 2 * 100,
					200.0, 100.0, 0, 2 * Math.random()+ 2.0);
		}
		else if(counter >= STAGE_4) {
			obs = new Cloud(Math.random() * gc.getCanvas().getWidth() - 100, Math.random() * -1 * ((gc.getCanvas().getHeight() * 2)) - 2 * 100,
					200.0, 100.0, 0, 2 * Math.random()+ 2.0);
		}
		
		obstacles.add(obs);
		adjustObstacles();
	}
	
	public boolean checkCollisionAll(Sprite thing) {
		for(Obstacle obs : obstacles) {
			if(obs.checkCollision(thing)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCollisionPlayerAll(Player thing) {
		for(Obstacle obs : obstacles) {
			if(obs.checkPlayerCollision(thing)) {
				return true;
			}
		}
		return false;
	}
	
	public void adjustObstacles() {
		for(int i = 0; i < obstacles.size(); i++) {
			if(obstacles.get(i).getY() > 0) {
				continue;
			}
			else {
				while(checkCollisionAllObstacles(obstacles.get(i), i)) {
					obstacles.get(i).setX(Math.random() * (gc.getCanvas().getWidth() - obstacles.get(i).getWidth()));
					obstacles.get(i).setY(Math.random() * -1 * ((gc.getCanvas().getHeight() * 2)) - 2 * obstacles.get(i).getHeight());
				}
			}
		}
	}
	
	public boolean checkCollisionAllObstacles(Sprite thing, int index) {
		for(int i = 0; i < obstacles.size(); i++) {
			if(i == index) {
				continue;
			}
			else if(obstacles.get(i).checkCollision(obstacles.get(index))) {
				return true;
			}
		}
		return false;
	}
	
	
	public void drawAll() {
		for(Obstacle obs : obstacles) {
			obs.draw(gc);
		}
	}
	
	public void counterAdd() {
		counter++;
	}
	
	public int getCounter() {
		return counter;
	}
}
