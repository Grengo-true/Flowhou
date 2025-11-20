package flowhou.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Enemy extends Character{
    private AIController aiController;
    private boolean active;
    
    public Enemy(Vector2 newPosition, Texture newTexture, Stats newStats, float newHurtboxRadius) {
        super(newPosition, newTexture, newStats, newHurtboxRadius);
        this.aiController = new AIController(this, null);
        this.active = true;
        
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
    }
    
    public void die() {
        if (!active) return;       
        active = false;       
        dispose();
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
    public void onCollision(Area2D other) {
        super.onCollision(other);
        if (other instanceof Area2D) {
            Area2D area = (Area2D) other;           
            if (area.getParent() instanceof Bullet) {
                Bullet bullet = (Bullet) area.getParent();
                getStats().setLives(0);
                if (getStats().shouldDie() ){
                	dispose();
                }
                bullet.dispose();
            }
        }
    }
}