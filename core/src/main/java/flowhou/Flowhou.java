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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;



public class Flowhou extends ApplicationAdapter {
    
    private SpriteBatch gameBatch;
    private SpriteBatch uiBatch;
    private BitmapFont font;
    private Game game;
    private ArrayList<Collidable> collidables = new ArrayList<Collidable>(); 
    private float currentDelta = 0.0f;

    public void create () {
        font = new BitmapFont();
        
        
        game = new Game();
        Game.setInstance(game);
        
        
        gameBatch = new SpriteBatch();
        uiBatch = new SpriteBatch();
    }
    @Override
    public void render () {
    	currentDelta = Gdx.graphics.getDeltaTime();
        
        ScreenUtils.clear(0, 0, 0.2f, 1);
        
        
        gameBatch.begin();
        
        game.process(currentDelta);
        game.draw(gameBatch);
        
        gameBatch.end();
    }

    @Override
    public void dispose () {
    	game.dispose();
        gameBatch.dispose();
        uiBatch.dispose();
        font.dispose();
    }
}