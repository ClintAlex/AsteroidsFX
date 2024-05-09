module Enemy {
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    requires java.base;
    requires Common;
    requires CommonBullet;
    requires CommonEnemy;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmmi.cbse.enemysystem.EnemyControlSystem;
    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;
}