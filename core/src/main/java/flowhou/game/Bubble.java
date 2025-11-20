package flowhou.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bubble extends Bullet {
	
	public Bubble(Vector2 newPosition, Vector2 newMovementInput) {
		super(newPosition, new Texture(Gdx.files.internal("bubble8x.png")), 0.1f, newMovementInput, 2.0f, 2.0f);
	}
}
