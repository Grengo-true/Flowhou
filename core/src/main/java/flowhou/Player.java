package flowhou;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity implements Collidable {
    private static int MAX_BULLET_SOURCES = 8;
    private Stats stats = new Stats();
    private ArrayList<BulletSource> bulletSources = new ArrayList<BulletSource>();
    private boolean active = true;

    public Player(Texture spriteTexture) {
        setPosition(new Vector2(0,0));
        setSprite(new Sprite(spriteTexture, 64, 64));
        this.hitbox.setSize(64, 64);
    }
	
	// Public methods
	public void setup() {
		for (int i = 0; i < MAX_BULLET_SOURCES; i++) {
			bulletSources.add(new BulletSource());
		}
	}
	
	public void process(float delta) {
		super.process(delta);
		manageInputs();
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
	}
	
	public void takeDamage() {
	}
	
	public void destroy() {
	}
	
	public void attack() {
	}
	public Stats getStats() {
		return stats;
	}
	
	public void manageInputs() {
		Vector2 newInput = new Vector2();
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			newInput.x = -1;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			newInput.x += 1;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			newInput.y = -1;
		}
		
		else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			newInput.y += 1;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
			setSpeedMultiplier(0.5f);
		}
		else {
			setSpeedMultiplier(1.0f);
		}
		setMovementInput(newInput);
		
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			evoke();
		}
		
	}
	public void evoke() {
		for (BulletSource bulletSource : bulletSources){
			if (bulletSource.isReady()){
				bulletSource.evoke();
			}
		}
		
	}
	 @Override
	    public Rectangle getCollisionBox() {
	        hitbox.setPosition(position.x, position.y);
	        return hitbox;
	    }

	    @Override
	    public void onCollision(Collidable other) {
	        if (other instanceof Enemy) {
	            takeDamage();
	        }
	    }

	    @Override
	    public boolean isActive() {
	        return active;
	    }

	}
