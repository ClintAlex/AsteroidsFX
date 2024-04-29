package dk.sdu.mmmi.cbse.asteroidplugin;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Asteroid implements IGamePluginService {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    @Override
    public void start(GameData gameData, World world) {
        startAsteroids(gameData, world);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(dk.sdu.mmmi.cbse.common.asteroids.Asteroid.class)) {
            world.removeEntity(asteroid);
        }
        stopScheduler();
    }

    private Entity createSmallAsteroid(GameData gameData) {
        return createAsteroid(gameData, 10, dk.sdu.mmmi.cbse.common.asteroids.Asteroid.AsteroidSize.SMALL);
    }

    private Entity createMediumAsteroid(GameData gameData) {
        return createAsteroid(gameData, 20, dk.sdu.mmmi.cbse.common.asteroids.Asteroid.AsteroidSize.MEDIUM);
    }

    private Entity createLargeAsteroid(GameData gameData) {
        return createAsteroid(gameData, 30, dk.sdu.mmmi.cbse.common.asteroids.Asteroid.AsteroidSize.LARGE);
    }

    private Entity createAsteroid(GameData gameData, int size, dk.sdu.mmmi.cbse.common.asteroids.Asteroid.AsteroidSize asteroidSize) {
        dk.sdu.mmmi.cbse.common.asteroids.Asteroid asteroid = new dk.sdu.mmmi.cbse.common.asteroids.Asteroid();
        asteroid.setSize(asteroidSize);
        Random random = new Random();
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setRadius(size);
        int corner = random.nextInt(4) + 1;
        setPositionAndRotation(asteroid, corner, gameData);
        return asteroid;
    }

    private void setPositionAndRotation(dk.sdu.mmmi.cbse.common.asteroids.Asteroid asteroid, int corner, GameData gameData) {
        Random random = new Random();
        switch(corner) {
            case 1:
                asteroid.setX(random.nextInt(gameData.getDisplayWidth() / 4));
                asteroid.setY(random.nextInt(gameData.getDisplayHeight() / 4));
                asteroid.setRotation(random.nextInt(90) + 45);
                break;
            case 2:
                asteroid.setX(random.nextInt(gameData.getDisplayWidth() / 4) + gameData.getDisplayWidth() * 3 / 4);
                asteroid.setY(random.nextInt(gameData.getDisplayHeight() / 4));
                asteroid.setRotation(random.nextInt(90) + 135);
                break;
            case 3:
                asteroid.setX(random.nextInt(gameData.getDisplayWidth() / 4) + gameData.getDisplayWidth() * 3 / 4);
                asteroid.setY(random.nextInt(gameData.getDisplayHeight() / 4) + gameData.getDisplayHeight() * 3 / 4);
                asteroid.setRotation(random.nextInt(90) + 225);
                break;
            case 4:
                asteroid.setX(random.nextInt(gameData.getDisplayWidth() / 4));
                asteroid.setY(random.nextInt(gameData.getDisplayHeight() / 4) + gameData.getDisplayHeight() * 3 / 4);
                asteroid.setRotation(random.nextInt(90) + 315);
                break;
        }
    }

    public void startAsteroids(GameData gameData, World world) {
        scheduler.scheduleAtFixedRate(() -> {
            Entity asteroid = createRandomAsteroid(gameData);
            world.addEntity(asteroid);
        }, 0, 3000, TimeUnit.MILLISECONDS);
    }

    public Entity createRandomAsteroid(GameData gameData) {
        Random random = new Random();
        dk.sdu.mmmi.cbse.common.asteroids.Asteroid.AsteroidSize size = dk.sdu.mmmi.cbse.common.asteroids.Asteroid.AsteroidSize.values()[random.nextInt(dk.sdu.mmmi.cbse.common.asteroids.Asteroid.AsteroidSize.values().length)];
        switch (size) {
            case SMALL:
                return createSmallAsteroid(gameData);
            case MEDIUM:
                return createMediumAsteroid(gameData);
            case LARGE:
                return createLargeAsteroid(gameData);
            default:
                return null;
        }
    }

    public void stopScheduler() {
        scheduler.shutdown();
    }


}

