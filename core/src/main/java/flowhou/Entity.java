package flowhou;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity extends Node{
	protected static final float SPEED_CONST = 350.0f;
	protected float speedMultiplier = 1.0f;
	protected Vector2 movementInput = new Vector2();
	protected Vector2 velocity = new Vector2();
	protected Sprite sprite;
	protected Rectangle hitbox = new Rectangle();
	
	public Entity() {
	}
	
	public void process(float delta) {
		super.process(delta);
		updateVelocity();
		setPosition(getPosition().add(getVelocity().scl(delta)));
	}
	
	public void draw(SpriteBatch spriteBatch) {
        if (sprite == null) return;
        sprite.setPosition(getPosition().x, getPosition().y);
        sprite.draw(spriteBatch);
	}
	
	public void updateVelocity() {
		this.velocity.set(this.movementInput).scl(SPEED_CONST * speedMultiplier);
	}
	
	public void setSpeedMultiplier(float newSpeedMultiplier) {
		this.speedMultiplier = newSpeedMultiplier;
	}
	
	public float getSpeedMultiplier() {
		return this.speedMultiplier;
	}
	
	public void stop() {
		this.movementInput.setZero();
		this.velocity.setZero();
	}
	
	public void setMovementInput(Vector2 newMovementInput) {
		this.movementInput.set(newMovementInput).nor();
	}
	
	public void setMovementInput(float x, float y) {
	    this.movementInput.set(x, y).nor();
	}
	
	public Vector2 getMovementInput() {
		return this.movementInput.cpy();
	}
	public Vector2 getVelocity() {
		return this.velocity.cpy();
	}
	public void setVelocity(Vector2 newVelocity) {
		this.velocity.set(newVelocity);
	}
	
	public float getSpeed() {
		return this.velocity.len();
	}
	
	public boolean isMoving() {
		return !this.velocity.isZero();
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setSprite(Sprite newSprite) {
		this.sprite = newSprite;
	}
	public void setTexture(Texture newTexture) {
		if (newTexture == null) return;
		this.sprite.setTexture(newTexture);
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public void setHitbox(Rectangle newHitbox) {
		this.hitbox = newHitbox;
	}
	
	
}
