package dk.sdu.mmmi.cbse.project3.entities;

import static com.decouplink.Utilities.context;
import static dk.sdu.mmmi.cbse.project3.entities.EntityFactory.createEnemyShip;

import com.decouplink.DisposableList;
import com.decouplink.Link;

import dk.sdu.mmmi.cbse.project3.common.data.Entity;
import dk.sdu.mmmi.cbse.project3.common.services.IGamePluginService;

public class EntityPlugin implements IGamePluginService {

	DisposableList entities = new DisposableList();

	@Override
	public void start(Object world) {

		// Add entities to the world
		Link<Entity> el = context(world).add(Entity.class, createEnemyShip());
		entities.add(el);
	}

	@Override
	public void stop() {
		// Remove entities
		entities.dispose();
	}

}
