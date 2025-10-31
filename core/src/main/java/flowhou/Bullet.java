package flowhou;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet extends Entity{
	private Rectangle hurtBox = new Rectangle();
	private Rectangle sprite = new Rectangle();
	
	public Bullet(){
		
	}
	public void process(float delta, SpriteBatch batch) {
		updateMovement(delta);
	}
	
	public void updateMovement(float delta) {
		velocity = movementInput.scl(delta * SPEED_CONST * speedMultiplier);
		position = position.add(velocity);
		hurtBox.setPosition(position);
		sprite.setPosition(position);
	}
}
