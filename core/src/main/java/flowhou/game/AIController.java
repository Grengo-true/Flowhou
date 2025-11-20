package flowhou.game;

import java.util.concurrent.Callable;

import com.badlogic.gdx.math.Vector2;

public class AIController{
	enum TYPE{
		FOLLOW
		
	}
	
	private TYPE type;
	protected Character owner;
    protected Character target;
    
    public AIController(Character newOwner, Character newTarget, TYPE newType) {
    	this.owner = newOwner;
    	this.target = newTarget;
    	setType(newType);
    }
    
    public void updateBehavior(float delta) {
    	
    }
    
    public void setOwner(Character newOwner) {
        this.owner = newOwner;
    }
    
    public Character getOwner() {
    	return this.owner;
    }
    
    public void setTarget(Character newTarget) {
        this.target = newTarget;
    }
    public Character getTarget() {
    	return this.target;
    }
    
    public Vector2 getDirection() {
    	Vector2 direction = new Vector2();
		switch(getType()) {
			case FOLLOW:
				direction = getDirectionToTarget();
		}
		return direction;
    }
    
    private Vector2 getDirectionToTarget() {
    	if (owner == null || target == null) return new Vector2(0.0f,0.0f); 
    	
    	return target.getGlobalPosition().add(owner.getGlobalPosition().scl(-1)).nor();
    }
    
    
    public void setType(TYPE newType) {
    	this.type = newType;
    	
    }
    public TYPE getType() {
    	return this.type;
    }
}
