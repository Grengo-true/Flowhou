package flowhou;

public class Stats {
	protected static final int MIN_LIVES = 0;
	
	protected int lives;
	protected int storm;
	protected int power;
	
	public int getLives() {
		return this.lives;
	}
	public int getStorm() {
		return this.storm;
	}
	public int getPower() {
		return this.power;
	}
	public void setLives(int newLives) {
		this.lives = newLives;
	}
	public void setStorm(int newStorm) {
		this.storm = newStorm;
	}
	public void setPower(int newPower) {
		this.power = newPower;
	}
	
	public boolean shoudlDie() {
		return (this.lives == MIN_LIVES);
	}
}
