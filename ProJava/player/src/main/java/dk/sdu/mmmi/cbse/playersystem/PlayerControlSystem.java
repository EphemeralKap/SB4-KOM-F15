package dk.sdu.mmmi.cbse.playersystem;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.project3.common.data.BehaviourEnum;
import dk.sdu.mmmi.cbse.project3.common.data.Entity;
import dk.sdu.mmmi.cbse.project3.common.data.Rotation;
import dk.sdu.mmmi.cbse.project3.common.data.Velocity;
import dk.sdu.mmmi.cbse.project3.common.services.IEntityProcessingService;
import static dk.sdu.mmmi.cbse.project3.common.utils.EntityFactoryUtil.createBullet;

/**
 *
 * @author jcs
 */
public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(Object world, Entity entity) {

        Rotation rotation = context(entity).one(Rotation.class);
        Velocity velocity = context(entity).one(Velocity.class);

        double thrust = .1;

        for (BehaviourEnum behaviour : context(entity).all(BehaviourEnum.class)) {

            switch (behaviour) {
                case MOVE_UP:
                    velocity.vectorX += Math.cos(rotation.angle) * thrust;
                    velocity.vectorY += Math.sin(rotation.angle) * thrust;
                    break;

                case MOVE_DOWN:
                    velocity.vectorX -= Math.cos(rotation.angle) * thrust;
                    velocity.vectorY -= Math.sin(rotation.angle) * thrust;
                    break;

                case MOVE_LEFT:
                    rotation.angle -= 0.1;
                    break;

                case MOVE_RIGHT:
                    rotation.angle += 0.1;
                    break;

                case SHOOT:
                    Entity e = createBullet(entity);
                    e.setDisposable(context(world).add(Entity.class, e));
                    break;
                default:
                    break;
            }

        }
    }

}
