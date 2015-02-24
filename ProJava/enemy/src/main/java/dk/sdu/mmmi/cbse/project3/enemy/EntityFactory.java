package dk.sdu.mmmi.cbse.project3.enemy;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.project3.common.data.Entity;
import dk.sdu.mmmi.cbse.project3.common.data.EntityEnum;
import static dk.sdu.mmmi.cbse.project3.common.data.EntityEnum.ENEMY;
import dk.sdu.mmmi.cbse.project3.common.data.Position;
import dk.sdu.mmmi.cbse.project3.common.data.Radius;
import dk.sdu.mmmi.cbse.project3.common.data.Rotation;
import dk.sdu.mmmi.cbse.project3.common.data.Scale;
import dk.sdu.mmmi.cbse.project3.common.data.Velocity;
import dk.sdu.mmmi.cbse.project3.common.data.View;

public class EntityFactory {

    public static Entity createEnemyShip() {
        Entity enemyShip = new Entity();
        context(enemyShip).add(EntityEnum.class, ENEMY);
        context(enemyShip).add(View.class, new View("images/EnemyShip.png"));
        context(enemyShip).add(Position.class, new Position(160, 80));
        context(enemyShip).add(Rotation.class, new Rotation());
        context(enemyShip).add(Velocity.class, new Velocity(1, 1));
        context(enemyShip).add(Scale.class, new Scale());
        context(enemyShip).add(Radius.class, new Radius(5));
        return enemyShip;
    }

}
