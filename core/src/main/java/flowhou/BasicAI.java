package flowhou;

import com.badlogic.gdx.math.Vector2;

public class BasicAI extends AIController {
    private float moveTimer;
    private Vector2 direction;
    
    public BasicAI(Entity controlled) {
        super(controlled);
        this.moveTimer = 0;
        this.direction = new Vector2(0, -1); // Baja por defecto
    }
    
    @Override
    public void updateBehavior(float delta) {
        moveTimer += delta;
        
        // Cambia dirección cada 2 segundos
        if (moveTimer >= 2.0f) {
            direction.x = (float)(Math.random() * 2 - 1);
            moveTimer = 0;
        }
        
        controlled.setMovementInput(direction);
    }
}