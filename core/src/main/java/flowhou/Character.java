package flowhou;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Character extends Entity {
    protected static int MAX_BULLET_SOURCES = 2;
    protected ArrayList<BulletSource> bulletSources;
    protected Stats stats;
    
    public Character() {
        this.bulletSources = new ArrayList<BulletSource>();
        this.stats = new Stats();
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
    
    // Add/remove bullet sources
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
        
        super.dispose();
    }
}