package flowhou.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Pickup extends Entity{
	enum Type{
    	LIFE,
    	POWER,
    	STORM
    }
	
    private Rectangle hitbox = new Rectangle();
    private Sprite sprite;
    private boolean active;
    private Type type;
    
    
    public Pickup(Vector2 newPosition, Texture newTexture , Type type, float radius) {
    	super(newPosition, newTexture, radius);
        setType(type);
        this.active = true;
        this.hitbox.setSize(32, 32);
    }
    
    @Override
    public void setPosition(Vector2 newPosition) {
        super.setPosition(newPosition);
        hitbox.setPosition(newPosition);
    }
    
    public Type getType() {
        return this.type;
    }
    
    public void setType(Type newType) {
    	this.type = newType;
    }
}