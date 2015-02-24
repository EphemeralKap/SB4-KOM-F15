package dk.sdu.mmmi.cbse.project3.common.data;

import com.decouplink.Link;
import java.io.Serializable;

public final class Entity implements Serializable {

    private boolean destroyed;
    private Link<Entity> link;

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean dead) {
        this.destroyed = dead;
    }

    public void setDisposable(Link<Entity> l) {
        this.link = l;
    }

    public void dipose() {
        link.dispose();
    }
}
