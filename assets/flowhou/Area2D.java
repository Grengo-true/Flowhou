package flowhou;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Area2D extends Node2D {
    protected Rectangle detectionArea = new Rectangle();
    protected int collisionLayer;
    protected int collisionMask;
    
    public Area2D(Vector2 newPosition, int newDetectionAreaSize, int newCollisionLayer, int newCollisionMask) {
        super(newPosition);
        setDetectionArea(new Rectangle(getGlobalPosition().x - newDetectionAreaSize / 2.0f, getGlobalPosition().y - newDetectionAreaSize / 2.0f, newDetectionAreaSize, newDetectionAreaSize));
        setCollisionLayer(newCollisionLayer);
        setCollisionMask(newCollisionMask);
    }
    
    public void setSize(float width, float height) {
        this.detectionArea.setSize(width, height);
    }
    
    public Rectangle getDetectionArea() {
        return this.detectionArea;
    }
    
    public void setDetectionArea(Rectangle newDetectionArea) {
        this.detectionArea = newDetectionArea;
    }
    
    public boolean isColliding(Area2D collider) {
        if (this.collisionMask != collider.getCollisionLayer()) return false;
        return this.detectionArea.overlaps(collider.getDetectionArea());
    }
    
    @Override
    public void process(float delta) {
        super.process(delta);
        detectionArea.setPosition(
            globalPosition.x - detectionArea.width / 2, 
            globalPosition.y - detectionArea.height / 2
        );
    }
    
    public void setCollisionLayer(int newCollisionLayer) {
    	this.collisionLayer = newCollisionLayer;
    }
    
    public int getCollisionLayer() {
    	return this.collisionLayer;
    }
    
    public void setCollisionMask(int newCollisionMask) {
    	this.collisionMask = newCollisionMask;
    }
    
    public int getCollisionMask() {
    	return this.collisionMask;
    }
}
