package flowhou;

import com.badlogic.gdx.math.Rectangle;

public class Area2D extends Node2D {
    private Rectangle hitbox = new Rectangle();
    
    public Area2D() {
        super();
    }
    
    public Area2D(float width, float height) {
        super();
        this.hitbox.setSize(width, height);
    }
    
    public void setHitboxSize(float width, float height) {
        this.hitbox.setSize(width, height);
    }
    
    public Rectangle getHitbox() {
        hitbox.setPosition(
            globalPosition.x - hitbox.width / 2.0f, 
            globalPosition.y - hitbox.height / 2.0f
        );
        return this.hitbox;
    }
    
    public boolean isColliding(Area2D other) {
        if (other == null) return false;
        
        boolean collision = this.getHitbox().overlaps(other.getHitbox());
        
        if (collision) {
            System.out.println("Colision idiota!");
        }
        
        return collision;
    }
    
    @Override
    public void process(float delta) {
        super.process(delta);
        getHitbox();
    }
}