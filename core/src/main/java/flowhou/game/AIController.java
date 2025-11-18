package flowhou.game;

import com.badlogic.gdx.math.Vector2;

public class AIController{
	protected Character owner;
    protected Character target;
    
    public AIController(Character newOwner, Character newTarget) {
    	this.owner = newOwner;
    	this.target = newTarget;
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
    
    public Vector2 getTargetDirection() {
    	if (owner == null || target == null) return new Vector2(0.0f,0.0f); 
    	return target.getGlobalPosition().add(owner.getGlobalPosition().scl(-1)).nor();
    }
}
