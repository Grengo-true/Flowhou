package flowhou;

public abstract class AIController extends Node {
    protected Entity target;
    protected Entity controlled;
    
    public AIController(Entity controlled) {
        this.controlled = controlled;
    }
    
    public abstract void updateBehavior(float delta);
    
    public void setTarget(Entity newTarget) {
        this.target = newTarget;
    }
    
    @Override
    public void process(float delta) {
        super.process(delta);
        if (controlled != null) {
            updateBehavior(delta);
        }
    }
}
