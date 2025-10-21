package flowhou;

import com.badlogic.gdx.math.Vector2;

public class Entity extends Node{
	protected static final float SPEED_CONST = 350.0f;
	protected float speedMultiplier = 1.0f;
	protected Vector2 movementInput = new Vector2();
	protected Vector2 velocity = new Vector2();
	protected float lastDelta = 0.0f;
	
	public Entity() {
	}
	public void updateVelocity() {
		this.velocity.set(this.movementInput).scl(SPEED_CONST * speedMultiplier);
	}
	
	public void stop() {
		this.velocity.setZero();
	}
	
	public void setMovementInput(Vector2 newMovementInput) {
		this.movementInput.set(newMovementInput).nor();
	}
	
	public Vector2 getMovementInput() {
		return this.movementInput;
	}
	public Vector2 getVelocity() {
		return this.velocity;
	}
	public void setVelocity(Vector2 newVelocity) {
		this.velocity.set(newVelocity);
	}
	public boolean isMoving() {
		return !this.velocity.isZero();
	}
	
	public void process(float delta) {
	}
}
