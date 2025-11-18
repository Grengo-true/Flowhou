package flowhou;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Game extends Node2D {
	private static Game instance;
	private OrthographicCamera camera;
	
	public Game() {
		super(new Vector2(0,0));
		Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		rainMusic.setVolume(0.0f);
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 576);
        camera.update();
        
        addChild(new Player(new Vector2(64,64), new Texture(Gdx.files.internal("ameIdle.png"))));
        addChild(new Enemy(new Vector2(128,128) , new Texture(Gdx.files.internal("thronglerIdle.png"))));  
        
	}
	
	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		
		return instance;
	}
	
	public static void setInstance(Game game) {
		instance = game;
	}
	@Override
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
		spriteBatch.setProjectionMatrix(camera.combined);
	}
	
}
