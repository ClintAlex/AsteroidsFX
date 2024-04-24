package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;
import java.util.ServiceLoader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EnemyPlugin implements IGamePluginService {
    private Random random = new Random();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    @Override
    public void start(GameData gameData, World world) {
        startEnemies(gameData, world);
        startChangingDirections(gameData, world);
        startShooting(gameData, world);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            world.removeEntity(enemy);
            System.out.println("fjende fjernet");
        }
        stopScheduler();
        System.out.println("stop");
    }

    private Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new Enemy();
        Random random = new Random();
        enemyShip.setPolygonCoordinates(14, -2, 9, -2, 9, -4, 7, -4, 7, -6, -3, -6, -3, -8, 1, -8, 1, -10, -11, -10, -11, -6, -9, -6, -9, -4, -7, -4, -7, -2, -11, -2, -11, 2, -7, 2, -7, 4, -9, 4, -9, 6, -11, 6, -11, 10, 1, 10, 1, 8, -3, 8, -3, 6, 3, 6, 3, 2, 5, 2, 5, -2, 3, -2, 3, -4, 5, -4, 5, -2, 7, -2, 7, 2, 5, 2, 5, 4, 3, 4, 3, 6, 7, 6, 7, 4, 9, 4, 9, 2, 14, 2);
        enemyShip.setRadius(10);
        int corner = random.nextInt(4) + 1;
        switch(corner) {
            case 1: // øverste venstre hjørne
                enemyShip.setX(random.nextInt(gameData.getDisplayWidth() / 4));
                enemyShip.setY(random.nextInt(gameData.getDisplayHeight() / 4));
                enemyShip.setRotation(random.nextInt(90) + 45);
                break;
            case 2: // øverste højre hjørne
                enemyShip.setX(random.nextInt(gameData.getDisplayWidth() / 4) + gameData.getDisplayWidth() * 3 / 4);
                enemyShip.setY(random.nextInt(gameData.getDisplayHeight() / 4));
                enemyShip.setRotation(random.nextInt(90) + 135);
                break;
            case 3: // neders højre hjørne
                enemyShip.setX(random.nextInt(gameData.getDisplayWidth() / 4) + gameData.getDisplayWidth() * 3 / 4);
                enemyShip.setY(random.nextInt(gameData.getDisplayHeight() / 4) + gameData.getDisplayHeight() * 3 / 4);
                enemyShip.setRotation(random.nextInt(90) + 225);
                break;
            case 4: // neders venstre hjørne
                enemyShip.setX(random.nextInt(gameData.getDisplayWidth() / 4));
                enemyShip.setY(random.nextInt(gameData.getDisplayHeight() / 4) + gameData.getDisplayHeight() * 3 / 4);
                enemyShip.setRotation(random.nextInt(90) + 315);
                break;
        }
        return enemyShip;
    }

    public void startEnemies(GameData gameData, World world) {
        scheduler.scheduleAtFixedRate(() -> {
            Entity enemyShip = createEnemyShip(gameData);
            world.addEntity(enemyShip);
        }, 5, 15, TimeUnit.SECONDS);
    }

    public void startChangingDirections(GameData gameData, World world) {
        scheduler.scheduleAtFixedRate(() -> {
            for (Entity enemy : world.getEntities(Enemy.class)) {
                double newRotation = random.nextDouble() * 360;
                double currentRotation = enemy.getRotation();
                double rotationDifference = newRotation - currentRotation;

                if (rotationDifference > 180) {
                    rotationDifference -= 360;
                } else if (rotationDifference < -180) {
                    rotationDifference += 360;
                }

                // En lidt mere flydende rotation så det er lidt mindre snappy
                for (int i = 0; i < 2000; i += 100) { // I millisekunder
                    final double finalRotationDifference = rotationDifference;
                    scheduler.schedule(() -> {
                        double updatedRotation = enemy.getRotation() + finalRotationDifference / 20; // Divide by 20 because 2000 / 100 = 20
                        enemy.setRotation(updatedRotation);
                    }, i, TimeUnit.MILLISECONDS);
                }
            }
        }, 2, random.nextInt((5 - 1) + 1) + 1, TimeUnit.SECONDS);
    }

    public void startShooting(GameData gameData, World world) {
        scheduler.scheduleAtFixedRate(() -> {
            for (Entity enemy : world.getEntities(Enemy.class)) {
                ServiceLoader<BulletSPI> loader = ServiceLoader.load(BulletSPI.class);
                BulletSPI bulletService = loader.findFirst().orElse(null);
                if (bulletService != null) {
                    Entity bullet = bulletService.createBullet(enemy, gameData);
                    bullet.setRotation(enemy.getRotation());
                    world.addEntity(bullet);
                } else {
                    System.out.println("BulletSPI service blev ikke kaldt"); // Til lidt debugging
                }
            }
        }, 3, random.nextInt((5 - 1) + 1) + 1, TimeUnit.SECONDS);
    }

    private void stopScheduler() {scheduler.shutdown();
    }
}
