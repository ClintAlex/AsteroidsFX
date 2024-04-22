// CollisionDetector.java
package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.asteroid.AsteroidSplitterImpl;
import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {

    private IAsteroidSplitter asteroidSplitter = new AsteroidSplitterImpl();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                if (entity1.getID().equals(entity2.getID()) || !collides(entity1, entity2)) {
                    continue;
                }

                // Handle bullet and asteroid collision
                if (entity1 instanceof Bullet && entity2 instanceof Asteroid) {
                    handleBulletAsteroidCollision((Bullet) entity1, (Asteroid) entity2, world);
                } else if (entity1 instanceof Asteroid && entity2 instanceof Bullet) {
                    handleBulletAsteroidCollision((Bullet) entity2, (Asteroid) entity1, world);
                }
            }
        }
    }

    private void handleBulletAsteroidCollision(Bullet bullet, Asteroid asteroid, World world) {
        world.removeEntity(bullet);
        switch (asteroid.getSize()) {
            case SMALL:
                System.out.println("Destroying small asteroid.");
                world.removeEntity(asteroid);
                break;
            case MEDIUM:
                System.out.println("Splitting medium asteroid.");
                asteroidSplitter.createSplitAsteroid(asteroid, world);
                world.removeEntity(asteroid);
                break;
            case LARGE:
                System.out.println("Splitting large asteroid.");
                asteroidSplitter.createSplitAsteroid(asteroid, world);
                world.removeEntity(asteroid);
                break;
        }
    }

    public boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) (entity1.getX() - entity2.getX());
        float dy = (float) (entity1.getY() - entity2.getY());
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}
