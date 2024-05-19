package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import javafx.scene.paint.Color;

public class Bullet extends Entity {

        private Entity owner;

        public Bullet() {
            this.owner = owner;
            this.setColor(Color.BLACK);
        }

        public Entity getOwner() {
            return owner;
        }

        public void setOwner(Entity owner) {
            this.owner = owner;
        }
}
