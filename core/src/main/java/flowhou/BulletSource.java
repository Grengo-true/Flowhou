package flowhou;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BulletSource {
	static final private float SPEED_CONST = 350.0f;
	private Vector2 position = new Vector2();
	private Vector2 bulletMovementInput = new Vector2();
	private float bulletSpeed = 0.0f;
	
	
	public BulletSource(){
		
	}
	
	public void setUp() {
		
	}
	
	public void process(float delta, SpriteBatch batch) {
	}
	
	public void setPosition(Vector2 newPosition) {
		this.position = newPosition;
	}
	
}
