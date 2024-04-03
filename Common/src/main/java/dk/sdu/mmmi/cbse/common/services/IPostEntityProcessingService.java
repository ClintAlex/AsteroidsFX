package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * The IPostEntityProcessingService interface defines a contract for post-entity processing services,
 * which are responsible for handling game logic that needs to be executed after all IEntityProcessingService
 * logic has been completed.
 */
public interface IPostEntityProcessingService {

    /**
     * Processes game logic that occurs after all IEntityProcessingService logic has been executed.
     *
     * Implementations of this method can include logic for cleanup, resolving state changes,
     * or any other final adjustments to the game state or entities after the main entity processing has occurred.
     *
     * @param gameData contains data related to the current state of the game,
     *                 such as game settings and controls.
     * @param world contains the current state of the game world, including entities and their properties.
     */
    void process(GameData gameData, World world);
}

