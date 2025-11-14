package flowhou;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Entity{
	private Rectangle hurtBox;
	private float lifetime;
	private float totalDelta = 0.0f;
	public Bullet(Vector2 newGlobalPosition, Vector2 newMovementInput, float newSpeedMultiplier, float newLifetime){
		Game.getInstance().addChild(this);
		setGlobalPosition(newGlobalPosition);
		this.hurtBox = new Rectangle(0,0, 8, 8);
		this.sprite = new Sprite(new Texture(Gdx.files.internal("bubble8x.png")), 8, 8);
		this.sprite.setPosition(this.sprite.getWidth()/2.0f, this.sprite.getHeight()/2.0f);
		setMovementInput(newMovementInput);
		setSpeedMultiplier(newSpeedMultiplier);
		this.lifetime = newLifetime;
		
	}
	
	public void process(float delta) {
		super.process(delta);
		System.out.println(getPosition());
		
		totalDelta += delta;
		if (this.hurtBox != null) {
			hurtBox.setPosition(position.x - this.hurtBox.getWidth()/2.0f, position.y - this.hurtBox.getWidth()/2.0f);
		}
		if (sprite != null) {
			sprite.setPosition(position.x - this.sprite.getWidth()/2.0f, position.y - this.sprite.getHeight()/2.0f);
		}
		if (totalDelta > lifetime) {
			dispose();
		}
	}
	
	public Rectangle getHurtBox() {
		return this.hurtBox;
	}
	
}
