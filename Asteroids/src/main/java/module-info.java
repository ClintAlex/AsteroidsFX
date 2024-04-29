import dk.sdu.mmmi.cbse.asteroidplugin.Asteroid;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroid {
    exports dk.sdu.mmmi.cbse.asteroid;
    exports dk.sdu.mmmi.cbse.asteroidplugin;
    requires Common;
    requires CommonAsteroids;
    provides IGamePluginService with Asteroid;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroid.AsteroidProcessor;
}