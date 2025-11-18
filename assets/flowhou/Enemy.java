package flowhou;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Enemy extends Character implements Collidable {
    private AIController aiController;
    private boolean active;
    
    public Enemy(Vector2 newPosition, Texture newTexture) {
    	super(newPosition, newTexture);
        this.aiController = new AIController(this, null);
        this.active = true;
    }
    
    @Override
    public void process(float delta) {
        super.process(delta);
        if (aiController != null && active) {
        	setMovementInput(aiController.getTargetDirection());
        }
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
    }
    
    public void die() {
        active = false;
    }
    
    public void setAIController(AIController newAIController) {
        this.aiController = newAIController;
    }
    public AIController getAIControler() {
    	return this.aiController;
    }
}