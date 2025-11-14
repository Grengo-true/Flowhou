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
    private Stats stats = new Stats();
    private boolean active = true;

    public Player(Texture spriteTexture, Vector2 position) {
        setPosition(position);
        this.sprite = new Sprite(spriteTexture);
        this.sprite.setOriginCenter();               
        this.sprite.setPosition(sprite.getWidth()/2.0f, sprite.getHeight()/2.0f);
        this.collisionBox.setSize(64, 64);
        this.collisionBox.setPosition(-32, -32);
        setupBulletSources();
    }
    
    @Override
    public void setupBulletSources() {
        for (int i = 0; i < MAX_BULLET_SOURCES; i++) {
            BulletSource newBulletSource = new BulletSource(this, 0.1f);
            if (i == 0) {
                newBulletSource.setPosition(new Vector2(sprite.getWidth()/2.0f, 0));
            } else {
                newBulletSource.setPosition(new Vector2(-sprite.getWidth()/2.0f, 0));
            }
            
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
            newInput.x = -1;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            newInput.x += 1;
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            newInput.y = -1;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            newInput.y += 1;
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            setSpeedMultiplier(0.5f);
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
    
    public Stats getStats() {
        return stats;
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