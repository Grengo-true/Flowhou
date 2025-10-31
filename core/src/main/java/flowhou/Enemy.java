package flowhou;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;


public class Enemy extends Entity implements Collidable {
    private AIController ai;
    private int health;
    private boolean active;
    
    public Enemy(Texture texture, AIController aiController) {
        this.sprite = new Sprite(texture);
        this.ai = aiController;
        this.health = 100;
        this.active = true;
        addChild(ai);
    }
    
    @Override
    public void process(float delta) {
        super.process(delta);
        if (ai != null && active) {
            ai.process(delta);
        }
    }
    
    @Override
    public Rectangle getCollisionBox() {
        return hitbox;
    }
    
    @Override
    public void onCollision(Collidable other) {
        if (other instanceof Bullet) {
            takeDamage(10);
        }
    }
    
    @Override
    public boolean isActive() {
        return active;
    }
    
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            die();
        }
    }
    
    public void die() {
        active = false;
    }
    
    public void setAI(AIController newAI) {
        if (this.ai != null) {
            removeChild(this.ai);
        }
        this.ai = newAI;
        addChild(newAI);
    }
}