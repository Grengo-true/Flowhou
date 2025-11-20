package flowhou.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import java.util.ArrayList;

public class EnemySpawner extends Node2D implements Character.CharacterListener {
    private static final int MIN_QUEUE = 0;
    private static final int MAX_QUEUE = 100;
    
    private boolean cooldownStatus;
    private Enemy.TYPE enemyType;
    protected int queue;
    private float cooldownSeconds; 
    private Timer cooldownTimer;
    private ArrayList<Enemy> spawnedEnemies;
    
    public EnemySpawner(Vector2 newPosition, Enemy.TYPE newEnemyType, int newQueue, float newCooldownSeconds) {
        super(newPosition);
        this.cooldownTimer = new Timer();
        this.spawnedEnemies = new ArrayList<Enemy>();
        this.cooldownStatus = false;
        this.enemyType = newEnemyType;
        this.queue = Math.max(Math.min(newQueue, MAX_QUEUE), MIN_QUEUE);
        this.cooldownSeconds = newCooldownSeconds;
        
        // Start spawning if possible
        if (canSpawn()) {
            spawnEnemy();
        }
    }
    
    public void spawnEnemy() {
        if (!canSpawn()) return;
        
        spawnEnemyAt(getGlobalPosition());
        queue--; // Decrease queue count
        startCooldownTimer();
    }
    
    public void spawnEnemyAt(Vector2 spawnPos) {
        try {
            Enemy newEnemy = new Enemy(spawnPos, enemyType, 1);
            
            // Add death listener to the enemy
            newEnemy.addCharacterListener(this);
            
            // Add to game
            FlowhouGame.getGameInstance().addChild(newEnemy);
            spawnedEnemies.add(newEnemy);
            
        } catch (Exception e) {
            System.err.println("Failed to spawn enemy: " + e.getMessage());
        }
    }

    @Override
    public void process(float delta) {
        super.process(delta);
        
        // Auto-spawn while conditions are met
        if (canSpawn()) {
            spawnEnemy();
        }
        
        // Clean up dead enemies
        spawnedEnemies.removeIf(enemy -> !enemy.isActive());
    }
    
    @Override
    public void onCharacterDied(Character character) {
        if (character instanceof Enemy) {
            Enemy enemy = (Enemy) character;
            // Remove the enemy when it dies
            spawnedEnemies.remove(enemy);
        }
    }
    
    @Override
    public void dispose() {
        // Remove listeners from all enemies
        for (Enemy enemy : spawnedEnemies) {
            enemy.removeCharacterListener(this);
        }
        spawnedEnemies.clear();
        super.dispose();
    }
    
    public boolean canSpawn() {
        return (queue >= 1 && !cooldownStatus);
    }
    
    private void startCooldownTimer() {
        cooldownStatus = true;
        Task timerTask = new Task() {
            @Override
            public void run() {
                cooldownStatus = false;
            }
        };
        cooldownTimer.scheduleTask(timerTask, cooldownSeconds);
    }
    
    // Basic getters
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
    
    public int getQueue() {
        return queue;
    }
    
    public boolean isFinished() {
        return queue <= 0 && getActiveEnemyCount() == 0;
    }
}