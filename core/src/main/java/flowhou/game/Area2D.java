package flowhou.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Area2D extends Node2D {
    protected int collisionLayer;
    protected int collisionMask;
    protected Body collisionBody;
    protected boolean hasBox2DCollision = false;
    
    public Area2D(Vector2 newPosition, int newCollisionLayer, int newCollisionMask) {
        super(newPosition);
        setCollisionLayer(newCollisionLayer);
        setCollisionMask(newCollisionMask);
    }
    
    // Enable circle collision
    public void enableCircleCollision(float radius) {
        // Direct access to PhysicsManager
        FlowhouGame game = (FlowhouGame) Gdx.app.getApplicationListener();
        PhysicsManager physics = game.getPhysicsManager();
        
        this.collisionBody = physics.createCircleCollider(this, globalPosition.x, globalPosition.y, radius);
        this.hasBox2DCollision = true;
    }
    
    // Enable rectangle collision
    public void enableRectangleCollision(float width, float height) {
        // Direct access to PhysicsManager
        FlowhouGame game = (FlowhouGame) Gdx.app.getApplicationListener();
        PhysicsManager physics = game.getPhysicsManager();
        
        this.collisionBody = physics.createRectangleCollider(this, globalPosition.x, globalPosition.y, width, height);
        this.hasBox2DCollision = true;
    }
    
    // Enable polygon collision
    public void enablePolygonCollision(Vector2[] vertices) {
        // Direct access to PhysicsManager
        FlowhouGame game = (FlowhouGame) Gdx.app.getApplicationListener();
        PhysicsManager physics = game.getPhysicsManager();
        
        this.collisionBody = physics.createPolygonCollider(this, globalPosition.x, globalPosition.y, vertices);
        this.hasBox2DCollision = true;
    }
    
    @Override
    public void process(float delta) {
        super.process(delta);
        
        // Update Box2D body position to match node position
        if (hasBox2DCollision && collisionBody != null) {
            collisionBody.setTransform(globalPosition, 0);
        }
    }
    
    // Collision handler - override this in subclasses
    public void onCollision(Node2D other) {
        System.out.println(this.getClass().getSimpleName() + " collided with " + other.getClass().getSimpleName());
    }
    
    // Remove collision body when this node is disposed
    @Override
    protected void dispose() {
        if (hasBox2DCollision && collisionBody != null) {
            FlowhouGame game = (FlowhouGame) Gdx.app.getApplicationListener();
            PhysicsManager physics = game.getPhysicsManager();
            physics.removeBody(collisionBody);
        }
        super.dispose();
    }
    
    // Getters and setters
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
    
    public Body getCollisionBody() {
        return collisionBody;
    }
}