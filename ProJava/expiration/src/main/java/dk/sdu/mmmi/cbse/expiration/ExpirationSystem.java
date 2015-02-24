package dk.sdu.mmmi.cbse.expiration;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.project3.common.data.Entity;
import dk.sdu.mmmi.cbse.project3.common.data.Expires;
import dk.sdu.mmmi.cbse.project3.common.data.GameTime;
import dk.sdu.mmmi.cbse.project3.common.services.IEntityProcessingService;

/**
 *
 * @author jcs
 */
public class ExpirationSystem implements IEntityProcessingService {

    @Override
    public void process(Object world, Entity entity) {

        Expires expires = context(entity).one(Expires.class);

        if (expires != null) {
            int delta = context(world).one(GameTime.class).delta;
            expires.reduceLifeTime(delta);

            if (expires.isExpired()) {
                entity.setDestroyed(true);
            }
        }
    }
}
