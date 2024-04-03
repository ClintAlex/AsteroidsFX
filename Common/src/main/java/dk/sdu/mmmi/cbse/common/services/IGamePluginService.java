package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * The IGamePluginService interface defines a contract for game plugin services,
 * which are responsible for managing the lifecycle of game components such as entities.
 */
public interface IGamePluginService {

    /**
     * Initializes and adds game components to the game at the start of the game or
     * when the game level is loaded.
     *
     * This method should set up initial game state, entities, and other components
     * required for the game to function.
     *
     * @param gameData contains data related to the current state of the game,
     *                 such as game settings and controls.
     * @param world contains the current state of the game world, including entities and their properties.
     */
    void start(GameData gameData, World world);

    /**
     * Cleans up and removes game components from the game when the game or a game level is exited.
     *
     * This method should ensure that any resources allocated during start are properly released
     * and that the game state is left in a consistent state.
     *
     * @param gameData contains data related to the current state of the game,
     *                 such as game settings and controls.
     * @param world contains the current state of the game world, including entities and their properties.
     */
    void stop(GameData gameData, World world);
}

