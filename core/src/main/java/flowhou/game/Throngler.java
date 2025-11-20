package flowhou.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Throngler extends Enemy {
	
	public Throngler(Vector2 newPosition){
		super(newPosition, new Texture(Gdx.files.internal("thronglerIdle.png") ) , new Stats(0, 10, 10, 10, 10), 0.1f);
	}
}
