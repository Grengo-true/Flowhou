package flowhou.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity extends Node2D {
    protected static final float SPEED_CONST = 350.0f;
    protected float speedMultiplier;
    protected Vector2 movementInput;
    protected Vector2 velocity = new Vector2(0,0);
    protected Sprite sprite;
    protected Area2D hurtbox;
    protected boolean active;
    
    public Entity(Vector2 newPosition, Texture newTexture) {
        super(newPosition);
        setSprite(new Sprite(newTexture));
        setVelocity(new Vector2(0,0));
        setSpeedMultiplier(1.0f);
        setActive(true);
        
        // Create hurtbox with Box2D - no size parameter, just collision layers
        setHurtbox(new Area2D(Vector2.Zero, 1, 2)); // Layer 1, collides with layer 2
        
        // Enable collision for the hurtbox
        getHurtbox().enableCircleCollision(16f); // 16 pixel radius collision
        
        // Add hurtbox as child so it moves with the entity
        addChild(getHurtbox());
    }
    
    @Override
    public void process(float delta) {
        super.process(delta);
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
    
    public Area2D getHurtbox() {
        return this.hurtbox;
    }
    
    public void setHurtbox(Area2D newHurtbox) {
        this.hurtbox = newHurtbox;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(boolean newActive) {
        this.active = newActive;
    }
    
    // Override this in subclasses to handle collisions
    public void onCollision(Node2D other) {
        System.out.println(this.getClass().getSimpleName() + " collided with " + other.getClass().getSimpleName());
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