package dk.sdu.mmmi.cbse.common.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
/**
 * The AsteroidSPI interface provides a method to create split asteroids.
 */
public interface AsteroidSPI {
    /**
     * Creates a split asteroid.
     * Precondition: e and w must not be null. e must be an instance of Asteroid.
     * Postcondition: A new asteroid is created and added to the world at the location of the original asteroid.
     *
     * @param e The original asteroid.
     * @param w The game world.
     */
    void createSplitAsteroid(Entity e, World w);
}
