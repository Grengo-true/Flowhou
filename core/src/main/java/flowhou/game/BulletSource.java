package flowhou.game;

import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class BulletSource extends Node2D{
	private boolean cooldownStatus;
	private float cooldownSeconds; 
	private Timer cooldownTimer;
	private Character caster;
	
	public BulletSource( Vector2 newPosition, Character newCaster,  float newCooldownSeconds){
		super(newPosition);
		this.caster = newCaster;
		cooldownTimer = new Timer();
		cooldownStatus = false;
		cooldownSeconds = newCooldownSeconds;
	}
	
	public void setUp() {
		
	}
	public void evoke() {
		this.cooldownStatus = true;
		Bullet newBullet = new Bullet(this.globalPosition, new Texture(Gdx.files.internal("bubble8x.png")), new Vector2(0,1), 3.0f, 2.0f);
		startCooldownTimer();
	}
	
	public void process(float delta) {
	}
	
	public boolean isReady() {
		return !this.cooldownStatus;
	}
	
	public void setCooldownStatus(boolean newOnCooldown) {
		this.cooldownStatus = newOnCooldown;
	}
	
	public boolean getCooldownStatus() {
		return this.cooldownStatus;
	}
	
	public void setCooldownSeconds(float newCooldownSeconds) {
		this.cooldownSeconds = newCooldownSeconds;
	}
	
	public float getCooldownSeconds() {
		return this.cooldownSeconds;
	}
	
	public void startCooldownTimer() {
		Task newtimerTask = new Task() {
			public void run() {
				setCooldownStatus(false);
			}
		};
		cooldownTimer.schedule(newtimerTask, cooldownSeconds);
	}
}
