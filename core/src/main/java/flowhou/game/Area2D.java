package flowhou.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Area2D extends Node2D {
    protected int collisionLayer;
    protected int collisionMask;
    protected Body collisionBody;
    protected boolean hasBox2DCollision = false;
    protected boolean bodyCreated = false;
    protected float collisionRadius;
    protected float collisionWidth;
    protected float collisionHeight;
    protected Vector2[] collisionVertices;
    protected String collisionType = "none";
    
    public Area2D(Vector2 newPosition, int newCollisionLayer, int newCollisionMask) {
        super(newPosition);
        setCollisionLayer(newCollisionLayer);
        setCollisionMask(newCollisionMask);
    }
    public void enableCircleCollision(float radius) {
        this.collisionRadius = radius;
        this.collisionType = "circle";
        this.hasBox2DCollision = true;
    }
    public void enableRectangleCollision(float width, float height) {
        this.collisionWidth = width;
        this.collisionHeight = height;
        this.collisionType = "rectangle";
        this.hasBox2DCollision = true;
    }
    
    public void enablePolygonCollision(Vector2[] vertices) {
        this.collisionVertices = vertices;
        this.collisionType = "polygon";
        this.hasBox2DCollision = true;
    }
    
    @Override
    public void process(float delta) {
        super.process(delta);
        if (hasBox2DCollision && !bodyCreated) {
            createBox2DBody();
            bodyCreated = true;
        }
        
        // Update Box2D body position
        if (hasBox2DCollision && collisionBody != null) {
        	collisionBody.setAwake(true);
            Vector2 meterPos = getGlobalPosition().cpy().scl(0.01f);
            collisionBody.setTransform(meterPos, 0);
        }
    }
    
    private void createBox2DBody() {
        FlowhouGame game = (FlowhouGame) Gdx.app.getApplicationListener();
        PhysicsManager physics = game.getPhysicsManager();
        Vector2 currentPos = getGlobalPosition();
        
        switch (collisionType) {
            case "circle":
                this.collisionBody = physics.createCircleCollider(this, currentPos.x, currentPos.y, collisionRadius);
                break;
            case "rectangle":
                this.collisionBody = physics.createRectangleCollider(this, currentPos.x, currentPos.y, collisionWidth, collisionHeight);
                break;
            case "polygon":
                this.collisionBody = physics.createPolygonCollider(this, currentPos.x, currentPos.y, collisionVertices);
                break;
        }
        
        System.out.println("CREATED Box2D " + collisionType + " body at: " + currentPos);
    }
    
    // Collision handler - override this in subclasses
    public void onCollision(Area2D other) {
    	if (parent == null || !(parent instanceof Entity)) {
    		return;
    	}
    	Entity parentAsEntity = (Entity)getParent();
    	parentAsEntity.onCollision(other);
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