import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires java.base;
    requires Common;
    requires CommonAsteroids;
    requires CommonEnemy;
    requires CommonBullet;
    requires Asteroid;
    requires Player;
    exports dk.sdu.mmmi.cbse.collisionsystem;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;
    uses dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
}