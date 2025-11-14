package flowhou;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity extends Node2D {
    protected static final float SPEED_CONST = 350.0f;
    protected float speedMultiplier = 1.0f;
    protected Vector2 movementInput = new Vector2();
    protected Vector2 velocity = new Vector2();
    protected Sprite sprite;
    protected Rectangle collisionBox = new Rectangle();
    
    public Entity() {
    }
    
    @Override
    public void process(float delta) {
        super.process(delta); // Process children first
        updateVelocity();
        setPosition(getPosition().add(getVelocity().scl(delta)));
    }
    
    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (sprite != null) {
            sprite.draw(spriteBatch);
        }
        super.draw(spriteBatch);
    }
    
    @Override
    public void setPosition(Vector2 newPosition) {
        super.setPosition(newPosition);
        if (this.sprite != null) {
            sprite.setPosition(getPosition().x - sprite.getWidth()/2.0f, getPosition().y - sprite.getHeight()/2.0f);
        }
    }
    
    public void updateVelocity() {
        this.velocity.set(this.movementInput).scl(SPEED_CONST * speedMultiplier);
    }
    
    public void setSpeedMultiplier(float newSpeedMultiplier) {
        this.speedMultiplier = newSpeedMultiplier;
    }
    
    public float getSpeedMultiplier() {
        return this.speedMultiplier;
    }
    
    public void stop() {
        this.movementInput.setZero();
        this.velocity.setZero();
    }
    
    public void setMovementInput(Vector2 newMovementInput) {
        this.movementInput.set(newMovementInput).nor();
    }
    
    public void setMovementInput(float x, float y) {
        this.movementInput.set(x, y).nor();
    }
    
    public Vector2 getMovementInput() {
        return this.movementInput.cpy();
    }
    
    public Vector2 getVelocity() {
        return this.velocity.cpy();
    }
    
    public void setVelocity(Vector2 newVelocity) {
        this.speedMultiplier = newVelocity.len();
        this.movementInput = newVelocity.nor();
        updateVelocity();
    }
    
    public float getSpeed() {
        return this.velocity.len();
    }
    
    public boolean isMoving() {
        return !this.velocity.isZero();
    }
    
    public Sprite getSprite() {
        return sprite;
    }
    
    public void setSprite(Sprite newSprite) {
        this.sprite = newSprite;
    }
    
    public void setTexture(Texture newTexture) {
        if (newTexture == null) return;
        this.sprite.setTexture(newTexture);
    }
    
    public Rectangle getCollisionBox() {
        // Update hitbox position before returning
    	collisionBox.setPosition(position.x, position.y);
        return collisionBox;
    }
    
    public void setCollisionBox(Rectangle newHitbox) {
        this.collisionBox = newHitbox;
    }
    
    @Override
    public void dispose() {
        // Clean up resources if needed
        if (sprite != null) {
            // Note: Don't dispose the texture here unless you created it
            sprite = null;
        }
        super.dispose();
    }
}