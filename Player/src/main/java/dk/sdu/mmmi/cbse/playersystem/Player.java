package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import javafx.scene.paint.Color;

/**
 *
 * @author Emil
 */
public class Player extends Entity {
    public Player() {
        this.setLives(3);
        this.setColor(Color.DARKGREEN);
    }
}
