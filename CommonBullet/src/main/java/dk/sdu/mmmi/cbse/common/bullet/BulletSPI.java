package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * The BulletSPI interface provides a method to create bullets.
 */
public interface BulletSPI {
    /**
     * Creates a bullet.
     * Precondition: e and gameData must not be null. e must be an instance of a class that can shoot bullets.
     * Postcondition: A new bullet is created and returned.
     *
     * @param e The entity that shoots the bullet.
     * @param gameData The game data.
     * @return The created bullet.
     */
    Entity createBullet(Entity e, GameData gameData);
}
