package api;

import java.util.Observer;
import java.util.Observable;

public abstract class View implements Observer {
    /*
     * This is a convenience method for the update implementation
     * required by the Observer interface.
     */
    public final void update(Observable o) {
	update(o, null);
    }

    public abstract void render(Game game);
}
