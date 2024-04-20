package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemyShip : world.getEntities(Enemy.class)) {
            double changeX = Math.cos(Math.toRadians(enemyShip.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemyShip.getRotation()));

            enemyShip.setX(enemyShip.getX() + changeX * 0.5);
            enemyShip.setY(enemyShip.getY() + changeY * 0.5);

            if (enemyShip.getX() < 0) {
                enemyShip.setX(enemyShip.getX() + gameData.getDisplayWidth());
            }

            if (enemyShip.getX() > gameData.getDisplayWidth()) {
                enemyShip.setX(enemyShip.getX() - gameData.getDisplayWidth());
            }

            if (enemyShip.getY() < 0) {
                enemyShip.setY(enemyShip.getY() + gameData.getDisplayHeight());
            }

            if (enemyShip.getY() > gameData.getDisplayHeight()) {
                enemyShip.setY(enemyShip.getY() - gameData.getDisplayHeight());
            }

        }

    }

}