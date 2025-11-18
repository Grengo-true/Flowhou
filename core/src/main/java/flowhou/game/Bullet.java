package flowhou.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Entity{
	private float lifetime;
	private float totalDelta = 0.0f;
	private Area2D hitbox;
	
	public Bullet(Vector2 newPosition, Texture newTexture, Vector2 newMovementInput, float newSpeedMultiplier, float newLifetime){
		super(newPosition, newTexture);
		
		setMovementInput(newMovementInput);
		setSpeedMultiplier(newSpeedMultiplier);
		setLifeTime(newLifetime);
		
		this.hitbox = new Area2D(Vector2.Zero, 4, 1);
		FlowhouGame game = (FlowhouGame) Gdx.app.getApplicationListener();
		game.getGameInstance().addChild(this);
		
	}
	
	public void process(float delta) {
		super.process(delta);
		
		totalDelta += delta;
		
		if (sprite != null) {
			sprite.setPosition(position.x - this.sprite.getWidth()/2.0f, position.y - this.sprite.getHeight()/2.0f);
		}
		
		if (totalDelta > lifetime) {
			dispose();
		}
	}
	
	public Area2D getHitBox() {
		return this.hitbox;
	}
	
	public void setHitBox(Area2D newHitbox) {
		this.hitbox = newHitbox;
	}
	
	public void setLifeTime(float newLifetime) {
		this.lifetime = newLifetime;
	}
	
	public float getLifeTime(float newLifetime) {
		return this.lifetime;
	}
	
}
