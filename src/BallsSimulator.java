import gui.*;
import java.util.*;
import java.awt.*;

/**
  Classe de simulation des balles
  */

public class BallsSimulator implements Simulable {

    private Balls balles;
    private GUISimulator gui;
    private EventManager manager;


    public BallsSimulator(LinkedList<Ball> b, GUISimulator ig, EventManager manager) {
        this.gui = ig;
        this.balles = new Balls(b);
        this.manager = manager;
        this.manager.addEvent(new BallsEvent(0, this.balles, this.gui, this.manager));
        for (Ball p : this.balles.getMesballes()) {
            this.gui.addGraphicalElement(new Oval(p.getX(), p.getY(), Color.BLUE, Color.BLUE, 10));
        }
    }

    public GUISimulator getGUI() {
        return this.gui;
    }

    @Override
    public void next() {
        this.manager.next();
    }

    @Override
    public void restart() {
        this.gui.reset();
        balles.reInit();
        for (Ball p : this.balles.getMesballes()) {
            this.gui.addGraphicalElement(new Oval(p.getX(), p.getY(), Color.BLUE, Color.BLUE, 10));
        }
    }
}
