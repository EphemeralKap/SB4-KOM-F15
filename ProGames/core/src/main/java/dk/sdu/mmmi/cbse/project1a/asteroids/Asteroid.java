package dk.sdu.mmmi.cbse.project1a.asteroids;

import dk.sdu.mmmi.cbse.project1a.engine.Body;
import dk.sdu.mmmi.cbse.project1a.engine.Entity;
import dk.sdu.mmmi.cbse.project1a.engine.Health;
import dk.sdu.mmmi.cbse.project1a.engine.Physics;
import dk.sdu.mmmi.cbse.project1a.events.Events;
import dk.sdu.mmmi.cbse.project1a.events.Events.CreateEvent;
import dk.sdu.mmmi.cbse.project1a.events.Events.HurtEvent;
import dk.sdu.mmmi.cbse.project1a.events.Events.IEntityListener;
import playn.core.Image;
import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

public class Asteroid extends Entity {

    public Asteroid() {

        Image asteroidImage = assets().getImageSync("images/Asteroid.png");
        view = graphics().createImageLayer(asteroidImage);
        view.setOrigin(asteroidImage.width() / 2f, asteroidImage.height() / 2f);
        view.setSize(asteroidImage.width(), asteroidImage.height());

        body = new Body(this);
        body.x = (float) (Math.random() * 800.0);
        body.y = (float) (Math.random() * 600.0);
        body.radius = asteroidImage.width() / 2f;

        physics = new Physics(this);
        physics.velocityX = (Math.random() * 10) - 5;
        physics.velocityY = (Math.random() * 10) - 5;

        health = new Health(this);
        health.hits = 2;
    }

    @Override
    public void onHurt(HurtEvent event) {

        view.setSize(0.5f * view.width(), 0.5f * view.height());
        body.radius = view.width() / 2f;

        if (body.radius < 20) {
            for (IEntityListener listener : listenerList) {
                listener.onDestroy(new Events.DestroyEvent(this));
            }
            return;
        } else {
            health.hit(event.source(), 1);
        }

        // Create new Asteroid if the asteroid itself is damaged
        Asteroid newaAsteroid = new Asteroid();
        newaAsteroid.body.x = (float) (body.x + (Math.random() * 800.0));
        newaAsteroid.body.y = (float) (body.y + (Math.random() * 600.0));
        newaAsteroid.body.radius = body.radius;
        newaAsteroid.view.setSize(view.width(), view.height());

        // Publish event
        for (IEntityListener listener : listenerList) {
            listener.onCreate(new CreateEvent(this, newaAsteroid));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Asteroid@");
        sb.append(this.hashCode());
        sb.append("={x=");
        sb.append(body.x);
        sb.append(",y=");
        sb.append(body.y);
        sb.append(", a=");
        sb.append(body.angle);
        sb.append(", r=");
        sb.append(body.radius);
        sb.append("}");
        return sb.toString();
    }
}
