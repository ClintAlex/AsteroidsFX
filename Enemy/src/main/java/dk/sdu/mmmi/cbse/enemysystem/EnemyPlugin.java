package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemyShip(GameData gameData) {
        Enemy enemyShip = new Enemy();
        // Set initial properties like position, shape, etc.
        // For example:
        enemyShip.setPolygonCoordinates(14, -2, 9, -2, 9, -4, 7, -4, 7, -6, -3, -6, -3, -8, 1, -8, 1, -10, -11, -10, -11, -6, -9, -6, -9, -4, -7, -4, -7, -2, -11, -2, -11, 2, -7, 2, -7, 4, -9, 4, -9, 6, -11, 6, -11, 10, 1, 10, 1, 8, -3, 8, -3, 6, 3, 6, 3, 2, 5, 2, 5, -2, 3, -2, 3, -4, 5, -4, 5, -2, 7, -2, 7, 2, 5, 2, 5, 4, 3, 4, 3, 6, 7, 6, 7, 4, 9, 4, 9, 2, 14, 2
        ); // Define the shape
        enemyShip.setX(gameData.getDisplayWidth() / 2);
        enemyShip.setY(gameData.getDisplayHeight() / 2);
        enemyShip.setRotation(0); // Initial rotation facing up
        enemyShip.setRadius(10); // Example radius
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        if (enemy != null) {
            world.removeEntity(enemy);
        }
    }
}
