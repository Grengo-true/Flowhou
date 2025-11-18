package flowhou;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Character implements Collidable {
    
    public Player(Vector2 newPosition, Texture spriteTexture) {
        super(newPosition, spriteTexture);
        this.sprite.setOriginCenter();               
        this.sprite.setPosition(sprite.getWidth()/2.0f, sprite.getHeight()/2.0f);
        setupBulletSources();
    }
    
    @Override
    public void setupBulletSources() {
        for (int i = 0; i < MAX_BULLET_SOURCES; i++) {
            BulletSource newBulletSource = new BulletSource(new Vector2(50 * (((i + 1) % 2 == 0) ? -1 : 1) ,10) ,this, 0.1f);
            addBulletSource(newBulletSource);
        }
    }
    
    public void process(float delta) {
        super.process(delta);
        manageInputs();
    }
    
    public void manageInputs() {
        Vector2 newInput = new Vector2();
        
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            newInput.x += -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            newInput.x += 1;
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            newInput.y += -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            newInput.y += 1;
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            setSpeedMultiplier(0.7f);
        }
        else {
            setSpeedMultiplier(1.0f);
        }
        setMovementInput(newInput);
        
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            evoke();
        }
    }
    
    public void takeDamage() {
    }

    @Override
    public void onCollision(Collidable other) {
        if (other instanceof Enemy) {
            takeDamage();
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }
    
    
}