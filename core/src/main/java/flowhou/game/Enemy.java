package flowhou.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Enemy extends Character{
    private AIController aiController;
    private boolean active;
    private int health;
    private int maxHealth;
    
    public Enemy(Vector2 newPosition, Texture newTexture, int maxHealth) {
        super(newPosition, newTexture);
        this.aiController = new AIController(this, null);
        this.active = true;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        
        this.sprite.setOriginCenter();
        this.sprite.setPosition(sprite.getWidth()/2.0f, sprite.getHeight()/2.0f);
    }
    
    @Override
    public void process(float delta) {
        super.process(delta);
        if (aiController != null && active) {
        	setMovementInput(aiController.getTargetDirection());
        }
    }
    
    @Override
    public boolean isActive() {
        return active;
        
    }
    
    public void takeDamage(int damage) {
        if (!active) return;      
        health -= damage;        
        if (health <= 0) {
            die();
        }
    }
    
    public void die() {
        if (!active) return;       
        active = false;       
        dispose();
    }
    public int getHealth() {
        return this.health;
    }
    
    public void setHealth(int newHealth) {
        this.health = Math.max(0, Math.min(newHealth, maxHealth));
        if (this.health <= 0) {
            die();
        }
    }
    
    public int getMaxHealth() {
        return this.maxHealth;
    }
    
    public void setMaxHealth(int newMaxHealth) {
        this.maxHealth = Math.max(1, newMaxHealth);
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
    }
    
    public float getHealthPercentage() { //para barritas de vida
        return (float) health / (float) maxHealth;
    }
    

    @Override
    public void dispose() {
        active = false;
        super.dispose();
    }
    
    public void setAIController(AIController newAIController) {
        this.aiController = newAIController;
    }
    public AIController getAIControler() {
    	return this.aiController;
    }
    @Override
    public void onCollision(Node2D other) {
        super.onCollision(other);
        if (other instanceof Area2D) {
            Area2D area = (Area2D) other;           
            if (area.getParent() instanceof Bullet) {
                Bullet bullet = (Bullet) area.getParent();
                takeDamage(10);
                bullet.dispose();
            }
        }
    }
}