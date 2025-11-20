package flowhou.game;

public class Stats {
	protected int minLives;
	protected int maxLives;
	protected int lives;
	protected int storm;
	protected int power;
	
	public Stats(int newMinLives, int newMaxLives, int newLives, int newStorm, int newPower) {
		setMinLives(newMinLives);
		setMaxLives(newMaxLives);
		setLives(newLives);
		setStorm(newStorm);
		setPower(newPower);
	}
	
	public int getMinLives() {
		return this.minLives;
	}
	
	public int getMaxLives() {
		return this.maxLives;
	}
	
	public int getLives() {
		return this.lives;
	}
	public int getStorm() {
		return this.storm;
	}
	public int getPower() {
		return this.power;
	}
	
	public void setMinLives(int newMinLives) {
		this.minLives = newMinLives;
		clampLives();
	}
	
	public void setMaxLives(int newMaxLives) {
		this.minLives = newMaxLives;
		clampLives();
	}
	
	public void setLives(int newLives) {
		this.lives = newLives;
		clampLives();
	}
	public void setStorm(int newStorm) {
		this.storm = newStorm;
	}
	public void setPower(int newPower) {
		this.power = newPower;
	}
	
	public boolean shouldDie() {
		return (this.lives <= this.minLives);
	}
	
	private void clampLives() {
		this.lives = Math.max( Math.min(this.minLives, this.lives), this.maxLives );
	}
}
