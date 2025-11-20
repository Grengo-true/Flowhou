package flowhou.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Character extends Entity {
    protected static int MIN_LEVEL = 0;
    protected static int MAX_LEVEL = 10;
    protected static int MAX_BULLET_SOURCES = 2;
    protected ArrayList<BulletSource> bulletSources;
    protected Stats stats;
    protected int level;
    
    private ArrayList<CharacterListener> characterListeners = new ArrayList<>();
    
    public interface CharacterListener {
        void onCharacterDied(Character character);
    }
    
    public Character(Vector2 newPosition, int newLevel) {
        super(newPosition);
        setStats(new Stats());
        setBulletSources(new ArrayList<BulletSource>());
        setLevel(newLevel);
    }
    
    public void setupBulletSources() {
    }
    
    public ArrayList<BulletSource> getBulletSources() {
        return bulletSources;
    }
    
    public void setBulletSources(ArrayList<BulletSource> bulletSources) {
        this.bulletSources = bulletSources;
    }
    
    public static int getMaxBulletSources() {
        return MAX_BULLET_SOURCES;
    }
    
    public static void setMaxBulletSources(int maxBulletSources) {
        MAX_BULLET_SOURCES = maxBulletSources;
    }
    
    public void addBulletSource(BulletSource bulletSource) {
        if (bulletSource != null && !bulletSources.contains(bulletSource)) {
            bulletSources.add(bulletSource);
            addChild(bulletSource);
        }
    }
    
    public void removeBulletSource(BulletSource bulletSource) {
        if (bulletSources.remove(bulletSource)) {
            removeChild(bulletSource);
        }
    }
    
    public void evoke() {
        for (BulletSource bulletSource : bulletSources) {
            if (bulletSource.isReady()) {
                bulletSource.evoke();
            }
        }
    }
    
    public void setStats(Stats newStats) {
        this.stats = newStats;
    }
    
    public Stats getStats() {
        return this.stats;
    }
    
    @Override
    public void dispose() {
        for (BulletSource source : bulletSources) {
            source.dispose();
        }
        bulletSources.clear();
        clearCharacterListeners();
        super.dispose();
    }
    
    public void setLevel(int newLevel) {
        this.level = Math.max(Math.min(newLevel, MAX_LEVEL), MIN_LEVEL);
        onLevelChanged();
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public void onLevelChanged() {
    }
    
    public void die() {
        emitDied();
        dispose();
    }
    
    public void addCharacterListener(CharacterListener listener) {
        if (listener != null && !characterListeners.contains(listener)) {
            characterListeners.add(listener);
        }
    }
    
    public void removeCharacterListener(CharacterListener listener) {
        characterListeners.remove(listener);
    }
    
    public void clearCharacterListeners() {
        characterListeners.clear();
    }
    
    protected void emitDied() {
        for (CharacterListener listener : new ArrayList<>(characterListeners)) {
            listener.onCharacterDied(this);
        }
    }
}