// AsteroidSplitterImpl.java
package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.Random;

public class AsteroidSplitterImpl implements IAsteroidSplitter {
    @Override
    public void createSplitAsteroid(Entity e, World w) {
        if (e instanceof Asteroid) {
            Asteroid parentAsteroid = (Asteroid) e;
            switch (parentAsteroid.getSize()) {
                case LARGE:
                    w.addEntity(createChildAsteroid(parentAsteroid, Asteroid.AsteroidSize.MEDIUM));
                    w.addEntity(createChildAsteroid(parentAsteroid, Asteroid.AsteroidSize.MEDIUM));
                    break;
                case MEDIUM:
                    w.addEntity(createChildAsteroid(parentAsteroid, Asteroid.AsteroidSize.SMALL));
                    w.addEntity(createChildAsteroid(parentAsteroid, Asteroid.AsteroidSize.SMALL));
                    break;
            }
        }
    }

    private Asteroid createChildAsteroid(Asteroid parent, Asteroid.AsteroidSize newSize) {
        Asteroid child = new Asteroid();
        child.setSize(newSize);
        child.setX(parent.getX() + (float)Math.random() - 0.5f); // Small positional offset
        child.setY(parent.getY() + (float)Math.random() - 0.5f);
        double angle = Math.random() * 2 * Math.PI; // Full circle
        float speed = 2 + (float)Math.random() * 3; // Speed between 2 and 5
        child.setX((float)Math.cos(angle) * speed);
        child.setY((float)Math.sin(angle) * speed);

        return child;
    }
}

