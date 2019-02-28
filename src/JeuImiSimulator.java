import gui.*;
import java.lang.Math;
import java.awt.Color;
import java.util.*;


public class JeuImiSimulator implements Simulable {
    /**
      Classe rendant compte le Jeu de l'immigration
      */
    private Grille g;
    private GUISimulator gui;

    public JeuImiSimulator(int height, int width, GUISimulator ig, int m){
        // Initialisation :
        int taille_cellule = 8;
        this.gui = ig;
        this.g = new Grille(height, width, m);
        restart();
    }

    public void setG(Grille g) {
        this.g = g;
    }

    public Grille getG() {
        return this.g;
    }

    public void setGui(GUISimulator gui) {
        this.gui = gui;
    }

    public GUISimulator getGui() {
        return this.gui;
    }

    @Override
    public void next(){
        /**
          Fonction rendant compte de la façon dont est modifié une cellule
          */
        int taille_cellule = 8;
        int n = g.getN();
        gui.reset();
        for (int i = 0; i < g.getHeight(); i++) {
            for (int j = 0; j < g.getWidth(); j++) {
                /* On realise des tests pour prendre en compte les differents voisins pertinents */
                int sum = 0;
                int etat_cour_cellule;
                etat_cour_cellule = g.getCellules()[Math.floorMod(i, g.getHeight())][Math.floorMod(j, g.getWidth())].getEtat_cour();
                if (g.getCellules()[Math.floorMod(i - 1, g.getHeight())][Math.floorMod(j - 1, g.getWidth())].getEtat_cour() == (etat_cour_cellule + 1)%n) {
                    sum += 1;
                }
                if (g.getCellules()[Math.floorMod(i, g.getHeight())][Math.floorMod(j - 1, g.getWidth())].getEtat_cour() == (etat_cour_cellule + 1)%n) {
                    sum += 1;
                }
                if (g.getCellules()[Math.floorMod(i + 1, g.getHeight())][Math.floorMod(j - 1, g.getWidth())].getEtat_cour() == (etat_cour_cellule + 1)%n) {
                    sum += 1;
                }
                if (g.getCellules()[Math.floorMod(i - 1, g.getHeight())][Math.floorMod(j + 1, g.getWidth())].getEtat_cour() == (etat_cour_cellule + 1)%n) {
                    sum += 1;
                }
                if (g.getCellules()[Math.floorMod(i, g.getHeight())][Math.floorMod(j + 1, g.getWidth())].getEtat_cour() == (etat_cour_cellule + 1)%n) {
                    sum += 1;
                }
                if (g.getCellules()[Math.floorMod(i + 1, g.getHeight())][Math.floorMod(j + 1, g.getWidth())].getEtat_cour() == (etat_cour_cellule + 1)%n) {
                    sum += 1;
                }
                if (g.getCellules()[Math.floorMod(i - 1, g.getHeight())][Math.floorMod(j, g.getWidth())].getEtat_cour() == (etat_cour_cellule + 1)%n) {
                    sum += 1;
                }
                if (g.getCellules()[Math.floorMod(i + 1, g.getHeight())][Math.floorMod(j, g.getWidth())].getEtat_cour() == (etat_cour_cellule + 1)%n) {
                    sum += 1;
                }
                /* Suivant le nombre de voisins pertinents,
                   on donne un nouvel etat à la cellule : */
                if (sum >= 3 ) {
                    g.getCellules()[i][j].setEtat_prec((etat_cour_cellule +1)%n);
                    int etat_prec_cell = g.getCellules()[i][j].getEtat_prec();
                    gui.addGraphicalElement(new Rectangle(i*taille_cellule + 50, j*taille_cellule + 50, new Color((255/n)*etat_prec_cell,(255/n)*etat_prec_cell, (255/n)*etat_prec_cell), new Color((255/n)*etat_prec_cell,(255/n)*etat_prec_cell, (255/n)*etat_prec_cell), taille_cellule, taille_cellule));
                }
                else {
                    g.getCellules()[i][j].setEtat_prec(etat_cour_cellule);
                    int etat_prec_cell = g.getCellules()[i][j].getEtat_prec();
                    gui.addGraphicalElement(new Rectangle(i*taille_cellule + 50, j*taille_cellule + 50, new Color((255/n)*etat_prec_cell,(255/n)*etat_prec_cell, (255/n)*etat_prec_cell), new Color((255/n)*etat_prec_cell,(255/n)*etat_prec_cell, (255/n)*etat_prec_cell), taille_cellule, taille_cellule));
                }

            }
        }

        for (int i = 0; i < g.getHeight(); i++) {
            for (int j = 0; j < g.getWidth(); j++) {
                // On met à jour etat_cour
                g.getCellules()[i][j].setEtat_cour(g.getCellules()[i][j].getEtat_prec());

            }
        }
    }
    @Override
    public void restart() {
        /**
          Fonction qui permet de refaitre une nouvelle initialisation
          */
        gui.reset();
        int taille_cellule = 8;
        g = new Grille(g.getHeight(), g.getWidth(), g.getN());
        for (int i = 0; i < g.getHeight(); i++) {
            for (int j = 0; j < g.getWidth(); j++) {
                int etat_cour_cell = g.getCellules()[i][j].getEtat_cour();
                int n = g.getN();
                gui.addGraphicalElement(new Rectangle(i*taille_cellule + 50, j*taille_cellule + 50, new Color((255/n)*etat_cour_cell,(255/n)*etat_cour_cell, (255/n)*etat_cour_cell), new Color((255/n)*etat_cour_cell,(255/n)*etat_cour_cell, (255/n)*etat_cour_cell), taille_cellule, taille_cellule));

            }
        }
    }
}
