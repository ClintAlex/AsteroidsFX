package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * The IEntityProcessingService interface defines a contract for entity processing services,
 * which are responsible for handling game logic related to entity interactions and behaviors
 * during each game update cycle.
 */
public interface IEntityProcessingService {

    /**
     * Processes game logic related to entities.
     *
     * Implementations of this method should contain logic that updates the state of entities
     * based on game rules, interactions between entities, or other criteria defined by the game.
     *
     * @param gameData contains data related to the current state of the game,
     *                 such as game settings and controls.
     * @param world contains the current state of the game world, including entities and their properties.
     */
    void process(GameData gameData, World world);
}

