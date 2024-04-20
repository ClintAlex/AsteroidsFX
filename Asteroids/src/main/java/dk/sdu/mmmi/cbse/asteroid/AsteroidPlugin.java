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

public class AsteroidPlugin implements IGamePluginService
{
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

    public Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        Random random = new Random();
        int size = random.nextInt(25)+5;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setRadius(size);
        int corner = random.nextInt(4) + 1;
        switch(corner) {
            case 1: // øverst til venstre
                asteroid.setX(random.nextInt(gameData.getDisplayWidth() / 4));
                asteroid.setY(random.nextInt(gameData.getDisplayHeight() / 4));
                asteroid.setRotation(random.nextInt(90) + 45);
                break;
            case 2: // øverst til højre
                asteroid.setX(random.nextInt(gameData.getDisplayWidth() / 4) + gameData.getDisplayWidth() * 3 / 4);
                asteroid.setY(random.nextInt(gameData.getDisplayHeight() / 4));
                asteroid.setRotation(random.nextInt(90) + 135);
                break;
            case 3: // nedre højre hjørne
                asteroid.setX(random.nextInt(gameData.getDisplayWidth() / 4) + gameData.getDisplayWidth() * 3 / 4);
                asteroid.setY(random.nextInt(gameData.getDisplayHeight() / 4) + gameData.getDisplayHeight() * 3 / 4);
                asteroid.setRotation(random.nextInt(90) + 225);
                break;
            case 4: // ned venstre hjørne
                asteroid.setX(random.nextInt(gameData.getDisplayWidth() / 4));
                asteroid.setY(random.nextInt(gameData.getDisplayHeight() / 4) + gameData.getDisplayHeight() * 3 / 4);
                asteroid.setRotation(random.nextInt(90) + 315);
                break;
        }
        return asteroid;
    }
    public void startAsteroids(GameData gameData, World world) {
        scheduler.scheduleAtFixedRate(() -> {
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }, 0, 3, TimeUnit.SECONDS);
    }

    public void stopScheduler() {
        scheduler.shutdown();
    }
}
