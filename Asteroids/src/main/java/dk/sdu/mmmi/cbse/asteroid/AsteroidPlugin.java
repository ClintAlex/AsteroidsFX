package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AsteroidPlugin implements IGamePluginService {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    @Override
    public void start(GameData gameData, World world) {
        startAsteroids(gameData, world);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
        stopScheduler();
    }

    private Entity createSmallAsteroid(GameData gameData) {
        return createAsteroid(gameData, 10, Asteroid.AsteroidSize.SMALL);
    }

    private Entity createMediumAsteroid(GameData gameData) {
        return createAsteroid(gameData, 20, Asteroid.AsteroidSize.MEDIUM);
    }

    private Entity createLargeAsteroid(GameData gameData) {
        return createAsteroid(gameData, 30, Asteroid.AsteroidSize.LARGE);
    }

    private Entity createAsteroid(GameData gameData, int size, Asteroid.AsteroidSize asteroidSize) {
        Asteroid asteroid = new Asteroid();
        asteroid.setSize(asteroidSize);
        Random random = new Random();
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setRadius(size);
        int corner = random.nextInt(4) + 1;
        setPositionAndRotation(asteroid, corner, gameData);
        return asteroid;
    }

    private void setPositionAndRotation(Asteroid asteroid, int corner, GameData gameData) {
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
        Asteroid.AsteroidSize size = Asteroid.AsteroidSize.values()[random.nextInt(Asteroid.AsteroidSize.values().length)];
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