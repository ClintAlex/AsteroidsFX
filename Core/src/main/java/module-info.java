module Core {
    requires javafx.graphics;
    requires Common;
    requires CommonBullet;
    requires java.sql;  // Required if using JPA or JDBC
    requires spring.context;
    requires spring.core;
    exports dk.sdu.mmmi.cbse.main to spring.beans, spring.context, spring.core;
    requires spring.beans;
    opens dk.sdu.mmmi.cbse.main to javafx.graphics, spring.core;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
    //exports dk.sdu.mmmi.cbse.main to spring.beans;
}
