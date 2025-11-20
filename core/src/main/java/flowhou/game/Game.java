package flowhou.game;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Game extends Node2D {
    private OrthographicCamera camera;
    private PhysicsManager physicsManager; // Store the reference
    private static Player player;
    
    public Game() {
        super(new Vector2(0,0));
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("Young Girl A.mp3"));
        rainMusic.setVolume(0.0f);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 576);
        camera.update();
        player = new Player(new Vector2(64,64), Player.TYPE.AME, 1);
        addChild(player);
        try {
        	TimeUnit.MILLISECONDS.sleep(10);
        }
        catch(Exception e){
        	System.out.println();
        }
        addChild(new EnemySpawner(new Vector2(128.0f , 128.0f) , Enemy.TYPE.THRONGLER, 10, 0.5f));
    }
    
    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
        spriteBatch.setProjectionMatrix(camera.combined);
    }
    
    public Player getPlayer() {
    	if (player == null) return null;
    	return player;
    }
    
    public static void setPlayer(Player newPlayer) {
    	player = newPlayer;
    }
}