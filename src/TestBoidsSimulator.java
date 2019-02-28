import gui.*;
import java.util.*;
import java.awt.Color;

/**
  Test de la simulation des boids
  */
public class TestBoidsSimulator {
    /**
      Différents paramètres peuvent êtres modifiés au plaisir de l'utilisateur.
      Ici, sont affichés deux groupes de boids pour le test
      */
    public static final int HEIGHT = 1000;
    public static final int WIDTH = 600;

    public static void main(String [] args) {

        GUISimulator gui = new GUISimulator(HEIGHT, WIDTH, Color.BLACK);
        gui.addGraphicalElement(new Rectangle(HEIGHT / 2, WIDTH / 2, Color.WHITE, null, HEIGHT, WIDTH));


        double dist_cadre = 50;

        int nb_boids1 = 200;
        double rayon_voisins1 = 70;
        double vision_voisins1 = (4 * Math.PI) / 5;
        double rayon_separation1 = 20;
        double vit_lim1 = 600;
        Color c1 = Color.RED;

        int nb_boids2 = 100;
        double rayon_voisins2 = 300;
        double vision_voisins2 = (3 * Math.PI) / 5;
        double rayon_separation2 = 400;
        double vit_lim2 = 200;
        Color c2 = Color.BLUE;

        // Initialisation des deux Boids :
        Boids b1 = new Boids(nb_boids1, rayon_voisins1, vision_voisins1, rayon_separation1, dist_cadre, vit_lim1, c1);
        Boids b2 = new Boids(nb_boids2, rayon_voisins2, vision_voisins2, rayon_separation2, dist_cadre, vit_lim2, c2);

        // Initialisation de l'EventManager :
        EventManager manager = new EventManager();

        // Initialisation des deux groupe de boids :
        BoidsSimulator boidsSimu1 = new BoidsSimulator(b1, gui, manager);
        BoidsSimulator boidsSimu2 = new BoidsSimulator(b2, gui, manager);

        boidsSimu1.getGui().setSimulable(boidsSimu1);
        boidsSimu2.getGui().setSimulable(boidsSimu2);


    }
}
