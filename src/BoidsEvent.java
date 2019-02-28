import gui.*;
import java.util.*;
import java.awt.Color;

public class BoidsEvent extends Event {
    /**
      Classe implémentant la gestion evenmentielle des boids
      */

    Boids les_boids;
    private GUISimulator gui;
    private EventManager manager;

    public BoidsEvent(long date, Boids les_boids, GUISimulator gui, EventManager manager) {
        super(date);
        this.les_boids = les_boids;
        this.gui = gui;
        this.manager = manager;
    }

    public void execute() {
        this.les_boids.translate();
        gui.addGraphicalElement(new Rectangle(TestBoidsSimulator.HEIGHT / 2, TestBoidsSimulator.WIDTH / 2, Color.WHITE, null, TestBoidsSimulator.HEIGHT, TestBoidsSimulator.WIDTH));
        for (Boid boid : this.les_boids.getMes_boids()) {
            Vector pos = new Vector(0, 0);
            Vector vit = new Vector(0, 0);
            pos.setX(boid.getPos_suiv().getX());
            pos.setY(boid.getPos_suiv().getY());
            vit.setX(boid.getVit_suiv().getX());
            vit.setY(boid.getVit_suiv().getY());
            boid.setPos(pos);
            boid.setVit(vit);
            gui.addGraphicalElement(new Oval((int) boid.getPos().getX(), (int) boid.getPos().getY(), this.les_boids.getC(), this.les_boids.getC(), 5));
            gui.addGraphicalElement(new Oval((int) (boid.getPos().getX() + 5 * boid.getVit().getX()/boid.getVit().norme()), (int) (boid.getPos().getY() + 5 * boid.getVit().getY()/boid.getVit().norme()), this.les_boids.getC(), this.les_boids.getC(), 2));
        }
        BoidsEvent e = new BoidsEvent(super.getDate() + 1, this.les_boids, this.gui, this.manager);
        this.manager.addEvent(e);

    }

    public void executeInit() {
        /**
          Méthode permettant de gérer le restart() du manager d'événement
          */
        gui.addGraphicalElement(new Rectangle(TestBoidsSimulator.HEIGHT / 2, TestBoidsSimulator.WIDTH / 2, Color.WHITE, null, TestBoidsSimulator.HEIGHT, TestBoidsSimulator.WIDTH));
        for (Boid boid : this.les_boids.getMes_boids()) {
            Vector pos = new Vector();
            Vector vit = new Vector(2);
            Vector pos_suiv = new Vector();
            Vector vit_suiv = new Vector(2);
            boid.setPos(pos);
            boid.setVit(vit);
            boid.setPos_suiv(pos_suiv);
            boid.setVit_suiv(vit_suiv);

            gui.addGraphicalElement(new Oval((int) boid.getPos().getX(), (int) boid.getPos().getY(), this.les_boids.getC(), this.les_boids.getC(), 5));
            gui.addGraphicalElement(new Oval((int) (boid.getPos().getX() + 5 * boid.getVit().getX()/boid.getVit().norme()), (int) (boid.getPos().getY() + 5 * boid.getVit().getY()/boid.getVit().norme()), this.les_boids.getC(), this.les_boids.getC(), 2));
        }
        BoidsEvent e = new BoidsEvent(0, this.les_boids, this.gui, this.manager);
        this.manager.addEvent(e);

    }
}
