package flowhou;

import com.badlogic.gdx.math.Vector2;

public class StraightAI extends AIController {
    
    public StraightAI(Entity controlled) {
        super(controlled);
    }
    
    @Override
    public void updateBehavior(float delta) {
        controlled.setMovementInput(0, -1); // Solo baja recto
    }
}