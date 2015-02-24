package dk.sdu.mmmi.cbse.project3.enemy;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.project3.common.data.Entity;
import dk.sdu.mmmi.cbse.project3.common.data.EntityEnum;
import static dk.sdu.mmmi.cbse.project3.common.data.EntityEnum.ENEMY;
import dk.sdu.mmmi.cbse.project3.common.data.Rotation;
import dk.sdu.mmmi.cbse.project3.common.services.IEntityProcessingService;
import static dk.sdu.mmmi.cbse.project3.common.utils.EntityFactoryUtil.createBullet;

public class EnemyProcessingService implements IEntityProcessingService {

    private int turnDirection = 1;

    @Override
    public void process(Object world, Entity entity) {

        EntityEnum b = context(entity).one(EntityEnum.class);

        if (b.equals(ENEMY)) {

            // Get context from entity
            Rotation rotation = context(entity).one(Rotation.class);

            // Generate random movement direction
            if (Math.random() < 0.05) {
                turnDirection = -turnDirection;
            }

            rotation.angle += turnDirection * 0.05;

            // Fire
            if (Math.random() < 0.02) {
                Entity e = createBullet(entity);
                e.setDisposable(context(world).add(Entity.class, e));
            }
        }
    }
}
