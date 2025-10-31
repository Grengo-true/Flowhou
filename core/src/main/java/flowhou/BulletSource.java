package flowhou;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

public class BulletSource extends Node{
	private boolean cooldownStatus = true;
	private Timer cooldownTimer;
	
	public BulletSource(){
		cooldownTimer = new Timer();
	}
	
	public void setUp() {
		
	}
	public void evoke() {
		this.cooldownStatus = true;
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
}
