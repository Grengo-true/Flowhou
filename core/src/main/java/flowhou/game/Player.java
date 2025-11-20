package flowhou.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Character{
	enum TYPE{
		AME
	}
	private TYPE type;
	
    public Player(Vector2 newPosition, TYPE newType , int newLevel) {
        super(newPosition, newLevel);           
        setupBulletSources();
        setType(newType);
    }
    
    @Override
    public void setupBulletSources() {
        for (int i = 0; i < MAX_BULLET_SOURCES; i++) {
            BulletSource newBulletSource = new BulletSource(new Vector2(50 * (((i + 1) % 2 == 0) ? -1 : 1) ,10) ,this, 0.1f);
            addBulletSource(newBulletSource);
        }
    }
    
    public void process(float delta) {
        super.process(delta);
        manageInputs();
    }
    
    public void manageInputs() {
        Vector2 newInput = new Vector2();
        
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            newInput.x += -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            newInput.x += 1;
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            newInput.y += -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            newInput.y += 1;
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            setSpeedMultiplier(0.7f);
        }
        else {
            setSpeedMultiplier(1.0f);
        }
        setMovementInput(newInput);
        
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            evoke();
        }
    }
    
    public void takeDamage() {
    }

    @Override
    public void onCollision(Area2D other) {
    }

    @Override
    public boolean isActive() {
        return active;
    }
    
    @Override
    public void onLevelChanged() {
    	getStats().setPower(getStats().getPower() + getLevel());
    }
    
    public void setType(TYPE newType) {
    	this.type = newType;
    	switch (this.type) {
    		case AME:
    			setTexture(new Texture(Gdx.files.internal("ameIdle.png")));
    			getHurtbox().enableCircleCollision(2.5f);
    			break;
    	}
    }
    
}