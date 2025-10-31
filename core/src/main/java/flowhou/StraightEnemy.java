package flowhou;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class StraightEnemy extends Entity implements Collidable {
    private AIController ai;
    private int health;
    private boolean active;

    public StraightEnemy(Texture texture) {
        this.sprite = new Sprite(texture);
        this.ai = new StraightAI(this);
        this.health = 50;
        this.active = true;
        this.hitbox.setSize(32, 32);
        addChild(ai);
    }

    @Override
    public void process(float delta) {
        super.process(delta);
        if (ai != null && active) {
            ai.process(delta);
        }
        hitbox.setPosition(position.x, position.y);
    }

    @Override
    public Rectangle getCollisionBox() {
        return hitbox;
    }

    @Override
    public void onCollision(Collidable other) {
        if (other instanceof Player) {
            die();
        } else if (other instanceof Bullet) {
            takeDamage(25);
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            die();
        }
    }

    public void die() {
        active = false;
    }
}