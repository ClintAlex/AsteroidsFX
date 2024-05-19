package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.Optional;
import java.util.ServiceLoader;

public class CollisionDetector implements IPostEntityProcessingService {

    private Optional<AsteroidSPI> asteroidSplitter;

    public CollisionDetector() {
        ServiceLoader<AsteroidSPI> loader = ServiceLoader.load(AsteroidSPI.class);
        asteroidSplitter = loader.findFirst();
    }

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
                // Handle player and asteroid collision
                else if (entity1 instanceof Player && entity2 instanceof Asteroid) {
                    world.removeEntity(entity1);
                } else if (entity1 instanceof Asteroid && entity2 instanceof Player) {
                    world.removeEntity(entity2);
                }
                // Handle player and enemy bullet collision
                else if (entity1 instanceof Player && entity2 instanceof Bullet && ((Bullet) entity2).getOwner() instanceof Enemy) {
                    handleBulletEntityCollision((Bullet) entity2, entity1, world);
                } else if (entity1 instanceof Bullet && entity2 instanceof Player && ((Bullet) entity1).getOwner() instanceof Enemy) {
                    handleBulletEntityCollision((Bullet) entity1, entity2, world);
                }
                // Handle player and enemy ship collision
                else if (entity1 instanceof Player && entity2 instanceof Enemy) {
                    world.removeEntity(entity1);
                } else if (entity1 instanceof Enemy && entity2 instanceof Player) {
                    world.removeEntity(entity2);
                }
                //Handle player bullet and enemy ship collision
                else if (entity1 instanceof Bullet && ((Bullet) entity1).getOwner() instanceof Player && entity2 instanceof Enemy) {
                    handleBulletEntityCollision((Bullet) entity1, entity2, world);
                } else if (entity1 instanceof Enemy && entity2 instanceof Bullet && ((Bullet) entity2).getOwner() instanceof Player) {
                    handleBulletEntityCollision((Bullet) entity2, entity1, world);
                }
            }
        }
    }

    private void handleBulletAsteroidCollision(Bullet bullet, Asteroid asteroid, World world) {
        if (bullet.getOwner() instanceof Enemy) {
            return;
        }
        world.removeEntity(bullet);
        if (asteroidSplitter.isPresent()) {
            switch (asteroid.getSize()) {
                case SMALL:
                    System.out.println("Destroying small asteroid.");
                    world.removeEntity(asteroid);
                    break;
                case MEDIUM:
                case LARGE:
                    System.out.println("Splitting " + asteroid.getSize().name().toLowerCase() + " asteroid.");
                    asteroidSplitter.get().createSplitAsteroid(asteroid, world);
                    world.removeEntity(asteroid);
                    break;
            }
        } else {
            System.out.println("Asteroid splitting not available, removing asteroid.");
            world.removeEntity(asteroid);
        }
    }

    private void handleBulletEntityCollision(Bullet bullet, Entity entity, World world) {
        entity.decrementLives();
        System.out.println("Entity hit. Remaining lives: " + entity.getLives());
        world.removeEntity(bullet);
        if (entity.getLives() <= 0) {
            System.out.println(entity.getID()+ " destroyed.");
            world.removeEntity(entity);
        }
    }

    public boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) (entity1.getX() - entity2.getX());
        float dy = (float) (entity1.getY() - entity2.getY());
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}
