package dk.sdu.mmmi.cbse.collision;

import com.decouplink.DisposableList;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.project3.common.data.BehaviourEnum;
import dk.sdu.mmmi.cbse.project3.common.data.Entity;
import dk.sdu.mmmi.cbse.project3.common.data.Position;
import dk.sdu.mmmi.cbse.project3.common.data.Radius;
import dk.sdu.mmmi.cbse.project3.common.services.IEntityProcessingService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jcs
 */
public class CollisionSystem implements IEntityProcessingService {

    DisposableList disposables = new DisposableList();

    @Override
    public void process(Object world, Entity source) {

        List<Entity> entities = new ArrayList<Entity>(context(world).all(Entity.class));
        disposables.dispose();

        for (Entity target : entities) {
            if (!(source.equals(target)) && testCollision(source, target)) {
                disposables.add(context(target).add(BehaviourEnum.class, BehaviourEnum.HIT));
            }
        }
    }

    private boolean testCollision(Entity source, Entity target) {

        Position srcPos = context(source).one(Position.class);
        Radius srcRadius = context(source).one(Radius.class);

        Position targetPos = context(target).one(Position.class);
        Radius targetRadius = context(target).one(Radius.class);

        float dx = srcPos.x - targetPos.x;
        float dy = srcPos.y - targetPos.y;

        double dist = Math.sqrt((dx * dx) + (dy * dy));
        boolean isCollision = dist <= srcRadius.value
                + targetRadius.value;

        if (isCollision) {
            System.out.println(String.format(
                    "%s hits %s, dist=%s, totalRadius=%s", source, target,
                    dist, srcRadius.value
                    + targetRadius.value));
        }

        return isCollision;
    }

}
