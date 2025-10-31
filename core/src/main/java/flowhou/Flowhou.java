package flowhou;


import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;



public class Flowhou extends ApplicationAdapter {
       private OrthographicCamera camera;
       private SpriteBatch gameBatch;
	   private SpriteBatch uiBatch;   
	   private BitmapFont font;
	   private Player player;
	   private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	   private float delta = 0.0f;
	   
	public void create () {
		font = new BitmapFont(); // use libGDX's default Arial font
		      
		Texture testTexture = new Texture(Gdx.files.internal("test64.png"));
		player = new Player(testTexture);
		player.setPosition(100, 100);
          
	    // load the drop sound effect and the rain background "music" 
	    Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
	    rainMusic.setVolume(0.0f);
	      
	    // camera
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 1024, 576);
	    gameBatch = new SpriteBatch();
		uiBatch = new SpriteBatch();   
	}
	

	@Override
	public void render () {
		//get delta

		delta = Gdx.graphics.getDeltaTime();
		
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update();
		//actualizar
		gameBatch.setProjectionMatrix(camera.combined);
		gameBatch.begin();
		player.process(delta);    
		player.draw(gameBatch);
		gameBatch.end();
	}
	
	@Override
	public void dispose () {
		player.destroy();
        gameBatch.dispose();
        uiBatch.dispose();
	    font.dispose();
	}
}

