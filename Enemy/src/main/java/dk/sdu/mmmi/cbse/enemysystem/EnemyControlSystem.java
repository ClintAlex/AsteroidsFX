package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class EnemyControlSystem implements IEntityProcessingService {

    private Random random = new Random();
    private long lastDirectionChangeTime = 0;
    private long directionChangeInterval = 5000; // Change direction every 5000 milliseconds (5 seconds)

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastDirectionChangeTime > directionChangeInterval) {
                // Change direction
                double newRotation = random.nextDouble() * 360; // Random new rotation
                enemy.setRotation(newRotation);
                lastDirectionChangeTime = currentTime;
            }

            // Move the enemy in the current direction
            double dx = Math.cos(Math.toRadians(enemy.getRotation()));
            double dy = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + dx);
            enemy.setY(enemy.getY() + dy);
        }
    }
}

/*// Shooting logic
        if (System.currentTimeMillis() - lastShotTime > shotCooldown) {
            lastShotTime = System.currentTimeMillis();
            shotCooldown = rand.nextInt((5000 - 3000) + 1) + 3000; // Randomize the shot cooldown for the next shot

            ServiceLoader<BulletSPI> loader = ServiceLoader.load(BulletSPI.class);
            BulletSPI bulletService = loader.findFirst().orElse(null);
            if (bulletService != null) {
                Entity bullet = bulletService.createBullet(enemy, gameData);
                bullet.setRotation(Math.toDegrees(angleToCenter)); // The bullet should face towards the center
                world.addEntity(bullet); // Add the bullet to the world
            }
        }*/