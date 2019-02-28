import java.util.*;
import java.awt.*;

public class Boids {
    private int nb_boids;
    private LinkedList<Boid> mes_boids;

    /** cf Boid pour détail des constantes suivante */
    private double rayon_voisins ;
    private double vision_voisins;
    private double rayon_separation;
    private double dist_cadre;
    private double vit_lim;
    private Color c;


    public int getNb_boids() {
        return this.nb_boids;
    }

    public LinkedList<Boid> getMes_boids() {
        return this.mes_boids;
    }

    public Color getC() {
        return this.c;
    }
    public Boids(int nb_boids, double rayon_voisins, double vision_voisins, double rayon_separation, double dist_cadre, double vit_lim, Color c) {
        this.rayon_voisins = rayon_voisins;
        this.vision_voisins = vision_voisins;
        this.rayon_separation = rayon_separation;
        this.dist_cadre = dist_cadre;
        this.vit_lim = vit_lim;
        this.c = c;

        // Initialisation de la liste des boids :
        this.nb_boids = nb_boids;
        LinkedList<Boid> boids = new LinkedList<Boid>();
        for (int i = 0; i < nb_boids; i++) {
            Boid boid = new Boid(rayon_voisins, vision_voisins, rayon_separation, dist_cadre, vit_lim, c);
            boids.add(boid);
        }
        this.mes_boids = boids;
    }

    void translate() {
        for (Boid b : this.mes_boids) {
            b.translate(this.mes_boids);
        }
    }
}
