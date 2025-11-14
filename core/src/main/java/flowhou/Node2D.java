package flowhou;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Node2D {
    protected Vector2 position = new Vector2();
    protected Vector2 globalPosition = new Vector2();
    protected Node2D parent = null;
    protected ArrayList<Node2D> children = new ArrayList<>();
    protected float lastDelta = 0.0f;
    
    public Node2D() {
    }
    
    public void process(float delta) {
        lastDelta = delta;
        
        // Process all children recursively
        for (int i = 0; i < children.size(); i++) {
            children.get(i).process(delta);
        }
    }
    
    public void draw(SpriteBatch spriteBatch) {
        // Draw all children recursively
        for (int i = 0; i < children.size(); i++) {
            children.get(i).draw(spriteBatch);
        }
    }
    
    public Vector2 getPosition() {
        return this.position.cpy();
    }
    
    public Vector2 getGlobalPosition() {
        return this.globalPosition.cpy();
    }
    
    public Vector2 getPositionFrom(Node2D targetNode) {
        return getGlobalPosition().sub(targetNode.getPosition());
    }
    
    public void setPosition(Vector2 newPosition) {
        this.position.set(newPosition);
        updateGlobalPosition();
        updateChildrenGlobalPositions();
    }
    
    public void setGlobalPosition(Vector2 newGlobalPosition) {
        this.globalPosition.set(newGlobalPosition);
        updatePositionFromGlobal();
        updateChildrenGlobalPositions();
    }
    
    public void updateGlobalPosition() {
        if (parent != null) {
            // Global position = parent's global + local position
            this.globalPosition.set(parent.getGlobalPosition()).add(this.position);
        } else {
            // If no parent, global position is same as local
            this.globalPosition.set(this.position);
        }
    }
    
    private void updatePositionFromGlobal() {
        if (parent != null) {
            // Local position = global - parent's global
            this.position.set(this.globalPosition).sub(parent.getGlobalPosition());
        } else {
            // No parent, local = global
            this.position.set(this.globalPosition);
        }
    }
    
    // Parent-child relationship methods
    public void addChild(Node2D child) {
        if (child != null && child != this && !children.contains(child)) {
            children.add(child);
            child.setParent(this);
        }
    }
    
    public void removeChild(Node2D child) {
        if (children.remove(child)) {
            child.setParent(null);
        }
    }
    
    public ArrayList<Node2D> getChildren() {
        // Return original
        return this.children; 
    }
    
    public Node2D getParent() {
        return parent;
    }
    
    public void setParent(Node2D newParent) {
        // Remove from current parent
        if (this.parent != null) {
            this.parent.removeChild(this);
        }
        // Set new parent
        this.parent = newParent;
        // Update global position based on new parent
        updateGlobalPosition();
        updateChildrenGlobalPositions();
    }
    
    private void updateChildrenGlobalPositions() {
        for (Node2D child : children) {
            child.updateGlobalPosition();
            // Recursive update
            child.updateChildrenGlobalPositions();
        }
    }
    
    protected void dispose() {
        if (parent != null) {
            parent.removeChild(this);
        }
        
        for (int i = children.size() - 1; i >= 0; i--) {
        	Node2D child = children.get(i);
            child.dispose();
        }
        
        children.clear();
        parent = null;
        position.set(0, 0);
        globalPosition.set(0, 0);
    }
}