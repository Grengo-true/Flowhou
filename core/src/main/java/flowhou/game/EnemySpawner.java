package flowhou.game;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

public class EnemySpawner extends Node2D {
    private Class<? extends Enemy> enemyClass;
    private ArrayList<Enemy> spawnedEnemies;
    
    // Bullet pattern placeholder
    private Object bulletPattern; // TODO: Replace with BulletPattern class when implemented
    
    public EnemySpawner(Vector2 newPosition, Class<? extends Enemy> enemyClass) {
        super(newPosition);
        this.enemyClass = enemyClass;
        this.spawnedEnemies = new ArrayList<>();
        this.bulletPattern = null;
    }
    
    // Spawn a single enemy at spawner's position
    public Enemy spawnEnemy() {
        return spawnEnemyAt(getGlobalPosition());
    }
    
    // Spawn enemy at specific position
    public Enemy spawnEnemyAt(Vector2 spawnPos) {
        try {
            Enemy newEnemy = enemyClass.getConstructor(Vector2.class).newInstance(spawnPos);
            // Add to game scene
            if (parent != null) {
                parent.addChild(newEnemy);
            }
            
            spawnedEnemies.add(newEnemy);
            return newEnemy;
            
        } catch (Exception e) {
            System.err.println("Failed to spawn enemy: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    // Spawn enemy at offset from spawner's position
    public Enemy spawnEnemyAtOffset(Vector2 offset) {
        Vector2 spawnPos = getGlobalPosition().add(offset);
        return spawnEnemyAt(spawnPos);
    }
    
    // Spawn multiple enemies at specific positions
    public ArrayList<Enemy> spawnWaveAtPositions(Vector2[] positions) {
        ArrayList<Enemy> wave = new ArrayList<>();
        for (Vector2 pos : positions) {
            Enemy enemy = spawnEnemyAt(pos);
            if (enemy != null) {
                wave.add(enemy);
            }
        }
        return wave;
    }
    
    // Spawn multiple enemies at offsets from spawner
    public ArrayList<Enemy> spawnWaveAtOffsets(Vector2[] offsets) {
        ArrayList<Enemy> wave = new ArrayList<>();
        for (Vector2 offset : offsets) {
            Enemy enemy = spawnEnemyAtOffset(offset);
            if (enemy != null) {
                wave.add(enemy);
            }
        }
        return wave;
    }
    
    @Override
    public void process(float delta) {
        super.process(delta);
        
        // Clean up dead enemies from tracking list
        spawnedEnemies.removeIf(enemy -> !enemy.isActive());
    }
    
    @Override
    protected void dispose() {
        spawnedEnemies.clear();
        super.dispose();
    }
    
    // Getters and setters
    
    public void setBulletPattern(Object pattern) {
        // TODO: Change parameter type to BulletPattern when implemented
        this.bulletPattern = pattern;
    }
    
    public Object getBulletPattern() {
        return bulletPattern;
    }
    
    public ArrayList<Enemy> getSpawnedEnemies() {
        return new ArrayList<>(spawnedEnemies);
    }
    
    public int getActiveEnemyCount() {
        int count = 0;
        for (Enemy enemy : spawnedEnemies) {
            if (enemy.isActive()) count++;
        }
        return count;
    }
    
    public Class<? extends Enemy> getEnemyClass() {
        return enemyClass;
    }
    
    public void setEnemyClass(Class<? extends Enemy> enemyClass) {
        this.enemyClass = enemyClass;
    }
}