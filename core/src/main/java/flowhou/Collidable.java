package flowhou;

import com.badlogic.gdx.math.Rectangle;

public interface Collidable {
    void onCollision(Collidable other);
    boolean isActive();
}