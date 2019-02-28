import gui.*;
import java.util.*;
import java.awt.Color;

public class BoidsSimulator implements Simulable {
    /**
      Classe de simulation des boids
      */
    private Boids b;
    private GUISimulator gui;
    private EventManager manager;

    public BoidsSimulator(Boids b, GUISimulator gui, EventManager manager) {
        this.gui = gui;
        this.b = b;
        this.manager = manager;

        this.manager.addEvent(new BoidsEvent(0, this.b, this.gui, this.manager));

        for (Boid boid : b.getMes_boids()) {
            gui.addGraphicalElement(new Oval((int) boid.getPos().getX(), (int) boid.getPos().getY(), this.b.getC(), this.b.getC(), 5));
            gui.addGraphicalElement(new Oval((int) (boid.getPos().getX() + 5 * boid.getVit().getX()/boid.getVit().norme()), (int) (boid.getPos().getY() + 5 * boid.getVit().getY()/boid.getVit().norme()), this.b.getC(), this.b.getC(), 2));
        }
    }

    public GUISimulator getGui() {
        return this.gui;
    }



    @Override
    public void next() {
        this.gui.reset();
        this.manager.next();
    }

    @Override
    public void restart() {
        gui.reset();
        this.manager.restart();
    }
}
