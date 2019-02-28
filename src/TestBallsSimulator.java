import gui.*;
import java.util.*;
import java.awt.Color;

/**
  Test de la simulation des balles
  */
public class TestBallsSimulator {
    public static void main(String [] args) {
        /**
          Initialisation des variables nécessairs aux différents constructeurs
          */
        GUISimulator gui = new GUISimulator(600, 600, Color.BLACK);

        LinkedList<Ball> l = new LinkedList<Ball>();
        Ball p1 = new Ball(2, 3);
        Ball p2 = new Ball(250, 0);
        Ball p3 = new Ball(0, 250);
        l.add(p1); l.add(p2); l.add(p3);

        EventManager manager = new EventManager();
        BallsSimulator bal = new BallsSimulator(l, gui, manager);


        bal.getGUI().setSimulable(bal);

    }
}
