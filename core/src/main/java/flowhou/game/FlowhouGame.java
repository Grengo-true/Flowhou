package flowhou.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class FlowhouGame extends ApplicationAdapter {
    private SpriteBatch gameBatch;
    private Game gameInstance;
    private float delta;
    private PhysicsManager physicsManager;
    
    @Override
    public void create() {
    	gameBatch = new SpriteBatch();
    	physicsManager = new PhysicsManager();
        gameInstance = new Game();
        updateDelta();
    }

    @Override
    public void render() {
    	updateDelta();
    	
    	physicsManager.update(delta);
    	
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        gameBatch.begin();
        gameInstance.draw(gameBatch);
        gameInstance.process(delta);
        gameBatch.end();
    }

    @Override
    public void dispose() {
    	gameBatch.dispose();
    	gameInstance.dispose();
    	physicsManager.dispose();
    }
    
    public Game getGameInstance() {
    	return this.gameInstance;
    }
    
    public float getDelta() {
    	return this.delta;
    }
    
    private void updateDelta() {
    	this.delta = Gdx.graphics.getDeltaTime();
    }
    
    public PhysicsManager getPhysicsManager() {
    	return this.physicsManager;
    }
}
