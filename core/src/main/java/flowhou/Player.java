package flowhou;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Character {
	// Constants
	private static int MAX_SOURCES = 8;
	
	
	// Game objects
	private Stats stats = new Stats();
	
	private Rectangle sprite = new Rectangle();
	private Texture texture;
	private Sound hurtSound;
	private BulletSource[] bulletSources = new BulletSource[MAX_SOURCES];
	
	// State variables
	private boolean isHurt = false;
	private int maxHurtTime = 50;
	private int hurtTime;
	private float speedMultiplier = 1.0f;
	
	// Movement variables
	private Vector2 movementInput = new Vector2();
	private Vector2 velocity = new Vector2();
	
	public Player(Texture newTexture, Sound sound) {
		texture = newTexture;
		hurtSound = sound;
	}
	
	// Public methods
	public void setup() {
		for (int i = 0; i < MAX_SOURCES; i++) {
			bulletSources[i] = new BulletSource();
		}
	}
	
	public void process(float delta, SpriteBatch batch) {
		updateMovement(delta);
		draw(batch);
	}
	
	public void draw(SpriteBatch batch) {
		if (!isHurt) {
			batch.draw(texture, sprite.x, sprite.y);
		} else {
			batch.draw(texture, sprite.x, sprite.y + MathUtils.random(-5, 5));
			hurtTime--;
			if (hurtTime <= 0) isHurt = false;
		}
	}
	
	public void takeDamage() {
		isHurt = true;
		hurtTime = maxHurtTime;
		hurtSound.play();
	}
	
	public void destroy() {
		texture.dispose();
	}
	
	public void attack() {
	}
	
	// Getters
	public Stats getStats() {
		return stats;
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public boolean isHurt() {
		return isHurt;
	}
	
	// Movement methods
	public void updateMovement(float delta) {
		movementInput = getPlayerInput();
		speedMultiplier = getSpeedMultiplier();
		velocity = movementInput.scl(delta * SPEED_CONST * speedMultiplier);
		setPosition(position.add(velocity));
	}
	
	public Vector2 getPlayerInput() {
		Vector2 newInput = new Vector2();
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			newInput.x = -1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			newInput.x += 1;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			newInput.y = -1;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			newInput.y += 1;
		}
		
		return newInput.nor();
	}
	
	public float getSpeedMultiplier() {
		float newSpeedMultiplier = 1.0f;
		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) newSpeedMultiplier = 0.5f;
		return newSpeedMultiplier;
	}
	
	// Position methods
	public void setPosition(Vector2 newPosition) {
		this.position = newPosition;
		setChildPosition(newPosition);
	}
	
	private void setChildPosition(Vector2 newPosition) {
		for (BulletSource bulletSource : bulletSources) {
			bulletSource.setPosition(newPosition);
		}
		hitbox.setPosition(newPosition);
		sprite.setPosition(newPosition);
	}
}