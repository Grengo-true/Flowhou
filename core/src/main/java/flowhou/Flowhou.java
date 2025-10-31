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
    private ArrayList<Collidable> collidables = new ArrayList<Collidable>(); 
    private float delta = 0.0f;
    private Texture enemyTexture;

    public void create () {
        font = new BitmapFont();
        
        Texture testTexture = new Texture(Gdx.files.internal("test64.png"));
        enemyTexture = new Texture(Gdx.files.internal("test64.png"));
        
        player = new Player(testTexture);
        player.setPosition(100, 100);
        collidables.add(player);
        
        // Crear enemigo
        StraightEnemy enemy = new StraightEnemy(enemyTexture);
        enemy.setPosition(200, 500);
        collidables.add(enemy); // Ahora funciona
        
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rainMusic.setVolume(0.0f);
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 576);
        gameBatch = new SpriteBatch();
        uiBatch = new SpriteBatch();
    }

    @Override
    public void render () {
        delta = Gdx.graphics.getDeltaTime();
        
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        gameBatch.setProjectionMatrix(camera.combined);
        gameBatch.begin();
        
        player.process(delta);
        player.draw(gameBatch);
        
        for (int i = collidables.size() - 1; i >= 0; i--) {
            Collidable col = collidables.get(i);
            if (col != player && col instanceof Entity) {
                Entity entity = (Entity) col;
                entity.process(delta);
                entity.draw(gameBatch);
                
                if (!col.isActive()) {
                    collidables.remove(i);
                }
            }
        }
        
        checkCollisions();
        
        gameBatch.end();
    }
    
    private void checkCollisions() {
        for (int i = 0; i < collidables.size(); i++) {
            for (int j = i + 1; j < collidables.size(); j++) {
                Collidable a = collidables.get(i);
                Collidable b = collidables.get(j);
                
                if (a.isActive() && b.isActive()) {
                    if (a.getCollisionBox().overlaps(b.getCollisionBox())) {
                        a.onCollision(b);
                        b.onCollision(a);
                    }
                }
            }
        }
    }

    @Override
    public void dispose () {
        player.destroy();
        gameBatch.dispose();
        uiBatch.dispose();
        font.dispose();
        enemyTexture.dispose();
    }
}