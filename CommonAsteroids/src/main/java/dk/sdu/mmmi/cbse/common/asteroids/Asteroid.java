package dk.sdu.mmmi.cbse.common.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import javafx.scene.paint.Color;
public class Asteroid extends Entity
{
    public enum AsteroidSize {
        SMALL, MEDIUM, LARGE
    }
    private AsteroidSize size;

    public AsteroidSize getSize() {
        return size;
    }

    public void setSize(AsteroidSize size) {
        this.size = size;
    }

    public Asteroid() {
        this.setColor(Color.DARKGRAY);
    }
}
