package flowhou.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class PhysicsManager {
    private World world;
    private Array<Body> bodiesToRemove = new Array<>();
    
    public PhysicsManager() {
        world = new World(new Vector2(0, 0), true);
        setupCollisionDetection();
    }
    
    // Circle collider
    public Body createCircleCollider(Node2D node, float x, float y, float radius) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        
        Body body = world.createBody(bodyDef);
        
        CircleShape circle = new CircleShape();
        circle.setRadius(radius);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.isSensor = true;
        fixtureDef.density = 0f;
        
        body.createFixture(fixtureDef);
        body.setUserData(node);
        circle.dispose();
        
        return body;
    }
    
    // Rectangle collider
    public Body createRectangleCollider(Node2D node, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        
        Body body = world.createBody(bodyDef);
        
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(width / 2, height / 2); // Box2D uses half-width/half-height
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.isSensor = true;
        fixtureDef.density = 0f;
        
        body.createFixture(fixtureDef);
        body.setUserData(node);
        rectangle.dispose();
        
        return body;
    }
    
    // Polygon collider (for complex shapes)
    public Body createPolygonCollider(Node2D node, float x, float y, Vector2[] vertices) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        
        Body body = world.createBody(bodyDef);
        
        PolygonShape polygon = new PolygonShape();
        polygon.set(vertices);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;
        fixtureDef.isSensor = true;
        fixtureDef.density = 0f;
        
        body.createFixture(fixtureDef);
        body.setUserData(node);
        polygon.dispose();
        
        return body;
    }
    
    // Edge collider (for boundaries)
    public Body createEdgeCollider(Node2D node, float x1, float y1, float x2, float y2) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        
        Body body = world.createBody(bodyDef);
        
        EdgeShape edge = new EdgeShape();
        edge.set(x1, y1, x2, y2);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = edge;
        fixtureDef.isSensor = true;
        fixtureDef.density = 0f;
        
        body.createFixture(fixtureDef);
        body.setUserData(node);
        edge.dispose();
        
        return body;
    }
    
    // ... rest of your existing methods (update, dispose, etc.)
    public void update(float delta) {
        world.step(delta, 6, 2);
        
        for (Body body : bodiesToRemove) {
            world.destroyBody(body);
        }
        bodiesToRemove.clear();
    }
    
    private void setupCollisionDetection() {
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                
                Area2D nodeA = (Area2D) fixtureA.getBody().getUserData();
                Area2D nodeB = (Area2D) fixtureB.getBody().getUserData();
                
                if (nodeA != null && nodeB != null) {
                    nodeA.onCollision(nodeB);
                    nodeB.onCollision(nodeA);
                }
            }
            
            @Override public void endContact(Contact contact) {}
            @Override public void preSolve(Contact contact, Manifold oldManifold) {}
            @Override public void postSolve(Contact contact, ContactImpulse impulse) {}
        });
    }
    
    public void removeBody(Body body) {
        bodiesToRemove.add(body);
    }
    
    public void dispose() {
        world.dispose();
    }
}