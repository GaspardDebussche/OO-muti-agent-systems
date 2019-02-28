import java.util.*;
import java.awt.*;
import java.lang.*;

public class Boid {

    /** positions et vitesse courantes : */
    private Vector pos;
    private Vector vit;

    /** positions et vitesses suivantes : */
    private Vector pos_suiv;
    private Vector vit_suiv;

    /** Rayon voisins : rayon du cercle de voisins d'un boid */
    private double rayon_voisins;

    /** vision_voisins : angle de vision d'un boid */
    private double vision_voisins;

    /** rayon_separation : rayon du cercle autour du boid auquel la force de séparation s'exerce */
    private double rayon_separation;

    /** dist_cadre : distance du bord à partir de laquelle la force de répulsion des bords s'exerce */
    private double dist_cadre;

    /** vit_lim : vitesse limite du boid */
    private double vit_lim;

    /** c : couleur du boid */
    private Color c;


    public Boid(double rayon_voisins, double vision_voisins, double rayon_separation, double dist_cadre, double vit_lim, Color c) {
        this.rayon_voisins = rayon_voisins;
        this.vision_voisins = vision_voisins;
        this.rayon_separation = rayon_separation;
        this.dist_cadre = dist_cadre;
        this.vit_lim = vit_lim;
        this.c = c;

        this.pos = new Vector();
        this.pos_suiv = new Vector();
        this.vit = new Vector(1);
        this.vit_suiv = new Vector(1);

    }

    Vector getPos() {
        return this.pos;
    }
    void setPos(Vector pos) {
        this.pos = pos;
    }

    Vector getVit() {
        return this.vit;
    }
    void setVit(Vector vit) {
        this.vit = vit;
    }

    Vector getPos_suiv() {
        return this.pos_suiv;
    }
    void setPos_suiv(Vector pos_suiv) {
        this.pos_suiv = pos_suiv;
    }

    Vector getVit_suiv() {
        return this.vit_suiv;
    }
    void setVit_suiv(Vector vit_suiv) {
        this.vit_suiv = vit_suiv;
    }



    public LinkedList<Boid> voisins(LinkedList<Boid> les_boids, double rayon_voisins, double vision_voisins) {
        LinkedList<Boid> voisins = new LinkedList<Boid>();
        for (Boid b : les_boids) {
            if (b != this) {
                // On test si le boid est dans le cercle de voisins :
                if (Math.pow(b.getPos().getX() - this.pos.getX(), 2) + Math.pow(b.getPos().getY() - this.pos.getY(), 2) < Math.pow(rayon_voisins, 2)) {
                    double angle = Math.acos((this.vit.getX() * b.getVit().getX() + this.vit.getY() * b.getVit().getY()) / (Math.sqrt(Math.pow(this.pos.getX(), 2) + Math.pow(this.pos.getY(), 2)) * Math.sqrt(Math.pow(b.getPos().getX(), 2) + Math.pow(b.getPos().getY(), 2))));
                    // On test s'il est aussi dans l'angle de vision du boid concerné :
                    if (angle < vision_voisins) {
                        voisins.add(b);
                    }
                }
            }
        }
        return voisins;
    }

    public Vector cohesion(LinkedList<Boid> les_boids) {
        double n = 0;
        Vector force = new Vector(0, 0);
        double somme_pos_x = 0; // la somme des composantes x des positions des boids voisins
        double somme_pos_y = 0; // la somme des composantes y des positions des boids voisins
        if (this.voisins(les_boids, rayon_voisins, vision_voisins).size() != 0) {
            for (Boid b : this.voisins(les_boids, rayon_voisins, vision_voisins)) {
                somme_pos_x += b.getPos().getX();
                somme_pos_y += b.getPos().getY();
                n = n + 1;
            }

            Vector centre_masse = new Vector(somme_pos_x / n, somme_pos_y / n);
            force.setX(centre_masse.getX() - this.pos.getX());
            force.setY(centre_masse.getY() - this.pos.getY());
        }
        return force;
    }

    public Vector alignement(LinkedList<Boid> les_boids) {
        double n = 0;
        Vector force = new Vector(0, 0);
        double somme_vit_x = 0; // somme des composantes en x des vitesses des boids voisins
        double somme_vit_y = 0; // somme des composantes en y des vitesses des boids voisins
        if (this.voisins(les_boids, rayon_voisins, vision_voisins).size() != 0) {
            for (Boid b : this.voisins(les_boids, rayon_voisins, vision_voisins)) {
                somme_vit_x += b.getVit().getX();
                somme_vit_y += b.getVit().getY();
                n = n + 1;
            }
            Vector direction = new Vector(somme_vit_x / n, somme_vit_y / n);
            force.setX(direction.getX());
            force.setY(direction.getY());
        }

        return force;
    }

    public Vector separation(LinkedList<Boid> les_boids, double rayon_separation) {
        Vector force = new Vector(0, 0);
        double n = 0;
        if (this.voisins(les_boids, rayon_voisins, vision_voisins).size() != 0) {
            double somme_x = 0;
            double somme_y = 0;
            for (Boid b : this.voisins(les_boids, rayon_voisins, vision_voisins)) {
                double distance = Math.pow((b.getPos().getX() - this.pos.getX()), 2) + Math.pow((b.getPos().getY() - this.pos.getY()), 2);
                if (distance <= Math.pow(rayon_separation, 2)) {
                    somme_x = somme_x - 5000 / (b.getPos().getX() - this.pos.getX());
                    somme_y = somme_y - 5000 / (b.getPos().getY() - this.pos.getY());
                    n = n + 1;
                }
            }
            if (n == 0) {
                n = 1;
            }

            Vector res = new Vector(somme_x / n, somme_y / n);
            force.setX(res.getX());
            force.setY(res.getY());
        }

        return force;
    }

    public Vector repulsion_bords(double dist_cadre) {
        Vector force = new Vector(0, 0);


        if (TestBoidsSimulator.HEIGHT - this.pos.getX() <= dist_cadre) {
            // le boid s'aprête à sortir à droite
            if (TestBoidsSimulator.HEIGHT - this.pos.getX() <= 0){
                force.setX(TestBoidsSimulator.HEIGHT - this.pos.getX());
            }
            else {
                force.setX(- 10000 / Math.pow(TestBoidsSimulator.HEIGHT - this.pos.getX(), 1));
            }
        }
        if (TestBoidsSimulator.WIDTH - this.pos.getY() <= dist_cadre) {
            // le boid s'aprête à sortir en bas
            if (TestBoidsSimulator.WIDTH - this.pos.getY() <= 0){
                force.setX(TestBoidsSimulator.WIDTH - this.pos.getX());
            }
            else {
                force.setY(- 10000 / Math.pow(TestBoidsSimulator.WIDTH - this.pos.getY(), 1));
            }
        }
        if (this.pos.getX() <= dist_cadre) {
            // le boid s'aprête à sortir à gauche
            if (this.pos.getX() <= 0){
                force.setX(this.pos.getX());
            }
            else {
                force.setX(10000 / Math.pow(this.pos.getX(), 1));
            }
        }
        if (this.pos.getY() <= dist_cadre) {
            // le boid s'aprête à sortir en haut
            if (this.pos.getY() <= 0){
                force.setY(this.pos.getY());
            }
            else {
                force.setY(10000 / Math.pow(this.pos.getY(), 1));
            }
        }


        return force;
    }


    public void translate(LinkedList<Boid> les_boids) {
        Vector somme_forces = new Vector(0, 0);

        somme_forces.add(this.cohesion(les_boids));
        somme_forces.add(this.alignement(les_boids));
        somme_forces.add(this.separation(les_boids, this.rayon_separation));
        somme_forces.add(this.repulsion_bords(dist_cadre));

        this.vit_suiv.setX(this.vit.getX() +  somme_forces.getX());
        this.vit_suiv.setY(this.vit.getY() +  somme_forces.getY());


        // On limite la vitesse des boids à vit_lim :
        double norme = this.vit_suiv.norme();
        if (norme > vit_lim) {
            double x = this.vit_suiv.getX();
            double y = this.vit_suiv.getY();
            this.vit_suiv.setX((x / norme) * vit_lim);
            this.vit_suiv.setY((y / norme) * vit_lim);
        }


        double x = this.pos.getX();
        double y = this.pos.getY();

        // On s'avance de 1% vers la direction donné :
        this.pos_suiv.setX(x + 0.01 * this.vit_suiv.getX());
        this.pos_suiv.setY(y + 0.01 * this.vit_suiv.getY());

    }


}
