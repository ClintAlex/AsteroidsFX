package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.Asteroid.AsteroidSize;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.playersystem.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CollisionDetectorTest {
    private CollisionDetector collisionDetector;
    private World world;
    private GameData gameData;
    private final float xCoord = 100;
    private final float yCoord = 100;

    @BeforeEach
    public void setUp() {
        collisionDetector = new CollisionDetector();
        world = new World();
        gameData = new GameData();
    }

    @Test
    public void testBulletSmallAsteroidCollision() {
        Asteroid asteroid = new Asteroid();
        Bullet bullet = new Bullet();
        bullet.setX(xCoord);
        bullet.setY(yCoord);
        bullet.setRadius(1);
        asteroid.setX(xCoord);
        asteroid.setY(yCoord);
        asteroid.setSize(AsteroidSize.SMALL);
        asteroid.setRadius(10);

        world.addEntity(bullet);
        world.addEntity(asteroid);

        collisionDetector.process(gameData, world);

        assertTrue(world.getEntities().isEmpty());
    }

    @Test
    public void testEnemyBulletPlayerCollision() {
        Bullet bullet = new Bullet();
        Player player = new Player();
        bullet.setX(xCoord);
        bullet.setY(yCoord);
        bullet.setRadius(1);
        bullet.setOwner(new Enemy());
        player.setX(xCoord);
        player.setY(yCoord);
        player.setRadius(1);
        player.setLives(1);

        world.addEntity(bullet);
        world.addEntity(player);

        collisionDetector.process(gameData, world);

        assertFalse(world.getEntities().contains(bullet));
        assertFalse(world.getEntities().contains(player));
    }

    @Test
    public void testPlayerBulletEnemyCollision() {
        Bullet bullet = new Bullet();
        Enemy enemy = new Enemy();
        bullet.setX(xCoord);
        bullet.setY(yCoord);
        bullet.setRadius(1);
        bullet.setOwner(new Player());
        enemy.setX(xCoord);
        enemy.setY(yCoord);
        enemy.setRadius(1);
        enemy.setLives(1);

        world.addEntity(bullet);
        world.addEntity(enemy);

        collisionDetector.process(gameData, world);

        assertFalse(world.getEntities().contains(bullet));
        assertFalse(world.getEntities().contains(enemy));
    }
}
