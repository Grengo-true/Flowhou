package flowhou.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Ame extends Player {
	
	public Ame(Vector2 newPosition){
		super(newPosition, new Texture(Gdx.files.internal("ameIdle.png") ) , new Stats(0, 10, 10, 10, 10), 2.0f);
	}
}
