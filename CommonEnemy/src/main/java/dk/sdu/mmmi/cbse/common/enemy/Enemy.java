package dk.sdu.mmmi.cbse.common.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import javafx.scene.paint.Color;
public class Enemy extends Entity{
    public Enemy() {
        this.setLives(3);
        this.setColor(Color.DARKRED);
    }
}
