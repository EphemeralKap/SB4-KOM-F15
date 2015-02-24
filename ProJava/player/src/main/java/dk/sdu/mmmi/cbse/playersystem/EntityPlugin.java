package dk.sdu.mmmi.cbse.playersystem;

import com.decouplink.DisposableList;
import com.decouplink.Link;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.project3.common.data.Entity;
import dk.sdu.mmmi.cbse.project3.common.data.EntityEnum;
import static dk.sdu.mmmi.cbse.project3.common.data.EntityEnum.PLAYER;
import dk.sdu.mmmi.cbse.project3.common.data.Position;
import dk.sdu.mmmi.cbse.project3.common.data.Radius;
import dk.sdu.mmmi.cbse.project3.common.data.Rotation;
import dk.sdu.mmmi.cbse.project3.common.data.Scale;
import dk.sdu.mmmi.cbse.project3.common.data.Velocity;
import dk.sdu.mmmi.cbse.project3.common.data.View;
import dk.sdu.mmmi.cbse.project3.common.services.IGamePluginService;

public class EntityPlugin implements IGamePluginService {

    DisposableList entities = new DisposableList();

    public EntityPlugin() {
    }

    @Override
    public void start(Object world) {

        // Add entities to the world
        Link<Entity> pl = context(world).add(Entity.class, createPlayerShip());
        entities.add(pl);
    }

    public Entity createPlayerShip() {
        Entity playerShip = new Entity();

        context(playerShip).add(EntityEnum.class, PLAYER);
        context(playerShip).add(View.class, new View("images/Ship.png"));
        context(playerShip).add(Position.class, new Position(360, 280));
        context(playerShip).add(Rotation.class, new Rotation());
        context(playerShip).add(Velocity.class, new Velocity());
        context(playerShip).add(Scale.class, new Scale());
        context(playerShip).add(Radius.class, new Radius(5));

        return playerShip;
    }

    @Override
    public void stop() {
        // Remove entities
        entities.dispose();
    }

}
