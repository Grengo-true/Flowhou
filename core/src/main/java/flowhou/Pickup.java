package flowhou;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Pickup extends Node2D implements Collidable {
    private Rectangle hitbox = new Rectangle();
    private Sprite sprite;
    private boolean active;
    private String type; // "lifes", "power", "storm", etc
    
    public Pickup(String type, Texture texture) {
        this.type = type;
        this.sprite = new Sprite(texture);
        this.active = true;
        this.hitbox.setSize(32, 32);
    }
    
    @Override
    public void onCollision(Collidable other) {
        if (other instanceof Player && active) {
            collect((Player) other);
        }
    }
    
    @Override
    public boolean isActive() {
        return active;
    }
    
    public void collect(Player player) {
        // aplicar efecto
        active = false;
    }
    
    public void draw(SpriteBatch batch) {
        if (active && sprite != null) {
            sprite.setPosition(position.x - sprite.getWidth()/2.0f, position.y- sprite.getHeight()/2.0f);
            sprite.draw(batch);
        }
    }
    
    @Override
    public void setPosition(Vector2 newPosition) {
        super.setPosition(newPosition);
        hitbox.setPosition(newPosition);
    }
    
    public String getType() {
        return type;
    }
}