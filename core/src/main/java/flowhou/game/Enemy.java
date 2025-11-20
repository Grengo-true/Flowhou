package flowhou.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Enemy extends Character{
	enum TYPE {
		THRONGLER
	}
	protected TYPE type;
	
    private AIController aiController;
    
    public Enemy(Vector2 newPosition, TYPE newType, int newLevel) {
        super(newPosition, newLevel);
        setType(newType);
    }
    
    @Override
    public void process(float delta) {
        super.process(delta);
        if (aiController != null && active) {
        	setMovementInput(aiController.getDirection());
        }
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
    
    public void setType(TYPE newType) {
    	this.type = newType;
    	switch (this.type) {
    		case THRONGLER:
    			setTexture(new Texture(Gdx.files.internal("thronglerIdle.png") ) );
    			getHurtbox().enableCircleCollision(0.5f);
    			setSpeedMultiplier(0.5f);
    			setAIController(new AIController(this, FlowhouGame.getGameInstance().getPlayer(), AIController.TYPE.FOLLOW));
    			break;
    	}
    }
}