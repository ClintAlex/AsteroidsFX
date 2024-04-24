// AsteroidSplitterImpl.java
package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.Random;

public class AsteroidSplitterImpl implements IAsteroidSplitter {
    @Override
    public void createSplitAsteroid(Entity e, World world) {
        if (e instanceof Asteroid) {
            Asteroid parentAsteroid = (Asteroid) e;
            switch (parentAsteroid.getSize()) {
                case LARGE:
                    world.addEntity(createChildAsteroid(parentAsteroid, Asteroid.AsteroidSize.MEDIUM));
                    world.addEntity(createChildAsteroid(parentAsteroid, Asteroid.AsteroidSize.MEDIUM));
                    break;
                case MEDIUM:
                    world.addEntity(createChildAsteroid(parentAsteroid, Asteroid.AsteroidSize.SMALL));
                    world.addEntity(createChildAsteroid(parentAsteroid, Asteroid.AsteroidSize.SMALL));
                    break;
            }
        }
    }

    private Asteroid createChildAsteroid(Asteroid parent, Asteroid.AsteroidSize newSize) {
        Asteroid child = new Asteroid();
        child.setSize(newSize);
        int size = 0;
        if (newSize == Asteroid.AsteroidSize.SMALL) {
            size = 10;
        } else if (newSize == Asteroid.AsteroidSize.MEDIUM) {
            size = 20;
        }
        child.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        child.setRadius(size);
        child.setX(parent.getX() + (float)Math.random() * 10 - 5);
        child.setY(parent.getY() + (float)Math.random() * 10 - 5);
        double angle = Math.random() * 360;
        child.setRotation(angle);

        System.out.println("Created child asteroid at X: " + child.getX() + " Y: " + child.getY() + " with rotation " + child.getRotation());
        return child;
    }

}

