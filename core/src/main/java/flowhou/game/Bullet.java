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
	
	enum TYPE{
		BUBBLE
	}
	
	public Bullet(Vector2 newPosition, Vector2 newMovementInput, TYPE newType){
	    super(newPosition);
		setType(newType);
	    setMovementInput(newMovementInput);
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
	
	public void setLifeTime(float newLifetime) {
		this.lifetime = newLifetime;
	}
	
	public float getLifeTime(float newLifetime) {
		return this.lifetime;
	}
	
	public void setType(TYPE newType) {
		switch(newType) {
			case BUBBLE:
				getHurtbox().enableCircleCollision(0.2f);
				setTexture(new Texture(Gdx.files.internal("bubble8x.png")));
				setSpeedMultiplier(5.0f);
				setLifeTime(2.5f);
		}
	}
	
}
