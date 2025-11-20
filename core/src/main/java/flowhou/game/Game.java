package flowhou.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Game extends Node2D {
    private OrthographicCamera camera;
    private PhysicsManager physicsManager; // Store the reference
    
    public Game() {
        super(new Vector2(0,0));
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("Young Girl A.mp3"));
        rainMusic.setVolume(0.0f);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 576);
        camera.update();
        
        addChild(new Ame(new Vector2(64,64)));
        
        addChild(new Throngler(new Vector2(128,128)));
        addChild(new Throngler(new Vector2(523,555)));
        addChild(new Throngler(new Vector2(444,444)));
    }
    
    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
        spriteBatch.setProjectionMatrix(camera.combined);
    }
}