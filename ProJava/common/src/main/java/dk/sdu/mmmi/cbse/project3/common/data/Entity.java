package dk.sdu.mmmi.cbse.project3.common.data;

import java.io.Serializable;

public final class Entity implements Serializable {

    private boolean destroyed;

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean dead) {
        this.destroyed = dead;
    }

}
