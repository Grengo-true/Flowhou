package flowhou;

import com.badlogic.gdx.math.Rectangle;

public interface Collidable {
    Rectangle getCollisionBox();
    void onCollision(Collidable other);
    boolean isActive();
}