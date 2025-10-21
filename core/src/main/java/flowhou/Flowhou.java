package flowhou;



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
	   private SpriteBatch batch;	   
	   private BitmapFont font;
	   
	   private Player player;
	   private Lluvia lluvia;
	   private float delta = 0.0f;
	   
	public void create () {
		font = new BitmapFont(); // use libGDX's default Arial font
		 
		  // load the images for the droplet and the bucket, 64x64 pixels each 	     
		  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
		  player = new Player(new Texture(Gdx.files.internal("bucket.png")),hurtSound);
          
	      // load the drop sound effect and the rain background "music" 
          Texture gota = new Texture(Gdx.files.internal("drop.png"));
          Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));
          
          Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
          dropSound.setVolume(1, 0);
          
	      Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
	      rainMusic.setVolume(0.0f);
          lluvia = new Lluvia(gota, gotaMala, dropSound, rainMusic);
	      
	      // camera
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 1024, 576);
	      batch = new SpriteBatch();
	      
	      // creacion del jugador
	      player.setup();
	      lluvia.crear();
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
			batch.setProjectionMatrix(camera.combined);
			batch.begin();

			player.process(delta, batch);      
			batch.end();
		}
	
	@Override
	public void dispose () {
		player.destroy();
        lluvia.destruir();
	    batch.dispose();
	    font.dispose();
	}
}

