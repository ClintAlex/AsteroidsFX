package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class CollisionDetector implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }
                // Mellem fjender og asteroider
                if ((entity1 instanceof Enemy && entity2 instanceof Asteroid) || (entity1 instanceof Asteroid && entity2 instanceof Enemy)) {
                    continue;
                }
                // Mellem asteroider
                if (entity1 instanceof Asteroid && entity2 instanceof Asteroid) {
                    continue;
                }
                if (this.collides(entity1, entity2)) {
                    world.removeEntity(entity1);
                    world.removeEntity(entity2);
                }
            }
        }
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}