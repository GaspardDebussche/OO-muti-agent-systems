import gui.*;
import java.util.*;
import java.awt.*;

public class BallsEvent extends Event {

    /**
      Classe BallsEvent permettant de gérer l'interface graphique des balles avec la gestion d'événements
      */
    private Balls balles;
    private GUISimulator gui;
    private EventManager manager;

    public BallsEvent(long date, Balls b, GUISimulator g, EventManager manager) {
        super(date);
        this.balles = b;
        this.gui = g;
        this.manager = manager;
    }

    public void execute() {
        this.gui.reset();
        this.balles.translate(this.gui);
        for (Ball p : this.balles.getMesballes()) {
            this.gui.addGraphicalElement(new Oval(p.getX(), p.getY(), Color.BLUE, Color.BLUE, 10));
        }

        BallsEvent e = new BallsEvent(super.getDate() + 1, this.balles, this.gui, this.manager);
        this.manager.addEvent(e);
    }

}
