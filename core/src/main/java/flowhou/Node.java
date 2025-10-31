package flowhou;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Node {
	protected Vector2 position = new Vector2();
	protected Vector2 globalPosition = new Vector2();
	protected Node parent = null;
	protected ArrayList<Node> children = new ArrayList<>();
	protected float lastDelta = 0.0f;
	
	public Node() {
	}
	
	public void process(float delta) {
		lastDelta = delta;
	}
	
	public Vector2 getPosition() {
		return this.position.cpy();
	}
	
	public Vector2 getGlobalPosition() {
		return this.globalPosition.cpy();
	}
	
	public Vector2 getPositionFrom(Node targetNode) {
		return getGlobalPosition().sub(targetNode.getPosition());
	}
	
	public void setPosition(Vector2 newPosition) {
		this.position.set(newPosition);
		updateGlobalPosition();
		updateChildrenGlobalPositions();
	}
	
	public void setPosition(float newX, float newY) {
		this.position.set(newX, newY);
		updateGlobalPosition();
		updateChildrenGlobalPositions();
	}
	
	public void setGlobalPosition(Vector2 newGlobalPosition) {
		this.globalPosition.set(newGlobalPosition);
		// Fixed missing semicolon
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
	public void addChild(Node child) {
		if (child != null && child != this && !children.contains(child)) {
			children.add(child);
			child.setParent(this);
		}
	}
	
	public void removeChild(Node child) {
		if (children.remove(child)) {
			child.setParent(null);
		}
	}
	
	public ArrayList<Node> getChildren() {
		// Return copy, it's safer
		return new ArrayList<>(children); 
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void setParent(Node newParent) {
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
		for (Node child : children) {
			child.updateGlobalPosition();
			// Recursive update
			child.updateChildrenGlobalPositions();
		}
	}
	
}
