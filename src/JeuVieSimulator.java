import gui.*;
import java.lang.Math;
import java.awt.Color;


public class JeuVieSimulator implements Simulable {
    /**
      Classe rendant compte du Jeu de la Vie
      */
    private Grille g;
    private GUISimulator gui;

    public JeuVieSimulator(int height, int width, GUISimulator ig, int m) {
        /* Initialisation */
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
    public void next() {
        /**
          Fonction rendant compte de la façon dont est modifié une cellule
          */
        int taille_cellule = 8;
        gui.reset();
        for (int i = 0; i < g.getHeight(); i++) {
            for (int j = 0; j < g.getWidth(); j++) {
                /* On realise des tests pour prendre en compte les differents voisins pertinents */
                int sum = 0;
                if (g.getCellules()[Math.floorMod(i - 1, g.getHeight())][Math.floorMod(j - 1, g.getWidth())].getEtat_cour() == 1) {
                    sum += 1;
                }
                if (g.getCellules()[Math.floorMod(i, g.getHeight())][Math.floorMod(j - 1, g.getWidth())].getEtat_cour() == 1) {
                    sum += 1;
                }
                if (g.getCellules()[Math.floorMod(i + 1, g.getHeight())][Math.floorMod(j - 1, g.getWidth())].getEtat_cour() == 1) {
                    sum += 1;
                }
                if (g.getCellules()[Math.floorMod(i - 1, g.getHeight())][Math.floorMod(j + 1, g.getWidth())].getEtat_cour() == 1) {
                    sum += 1;
                }
                if (g.getCellules()[Math.floorMod(i, g.getHeight())][Math.floorMod(j + 1, g.getWidth())].getEtat_cour() == 1) {
                    sum += 1;
                }
                if (g.getCellules()[Math.floorMod(i + 1, g.getHeight())][Math.floorMod(j + 1, g.getWidth())].getEtat_cour() == 1) {
                    sum += 1;
                }
                if (g.getCellules()[Math.floorMod(i - 1, g.getHeight())][Math.floorMod(j, g.getWidth())].getEtat_cour() == 1) {
                    sum += 1;
                }
                if (g.getCellules()[Math.floorMod(i + 1, g.getHeight())][Math.floorMod(j, g.getWidth())].getEtat_cour() == 1) {
                    sum += 1;
                }

                /*Suivant l'etat courant de la cellule et de son nombre de voisins pertinents,
                  on donne un nouvel etat à la cellule */
                if (g.getCellules()[i][j].getEtat_cour() == 1) {
                    if (sum == 3 || sum == 2) {
                        g.getCellules()[i][j].setEtat_prec(1);
                        gui.addGraphicalElement(new Rectangle(i*taille_cellule + 50, j*taille_cellule + 50, Color.RED, Color.RED, taille_cellule, taille_cellule));
                    }
                    else {
                        g.getCellules()[i][j].setEtat_prec(0);
                        gui.addGraphicalElement(new Rectangle(i*taille_cellule + 50, j*taille_cellule + 50, Color.BLACK, Color.BLACK, taille_cellule, taille_cellule));
                    }
                }
                else {
                    if (sum == 3) {
                        g.getCellules()[i][j].setEtat_prec(1);
                        gui.addGraphicalElement(new Rectangle(i*taille_cellule + 50, j*taille_cellule + 50, Color.RED, Color.RED, taille_cellule, taille_cellule));
                    }
                    else {
                        gui.addGraphicalElement(new Rectangle(i*taille_cellule + 50, j*taille_cellule + 50, Color.BLACK, Color.BLACK, taille_cellule, taille_cellule));
                    }
                }
            }
        }

        for (int i = 0; i < g.getHeight(); i++) {
            for (int j = 0; j < g.getWidth(); j++) {
                /* On met à jour etat_cour */
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
        g = new Grille(g.getHeight(), g.getWidth(), g.getN()); //On reprend les memes paramètres
        for (int i = 0; i < g.getHeight(); i++) {
            for (int j = 0; j < g.getWidth(); j++) {
                if (g.getCellules()[i][j].getEtat_cour() == 1) {
                    gui.addGraphicalElement(new Rectangle(i*taille_cellule + 50, j*taille_cellule + 50, Color.RED, Color.RED, taille_cellule, taille_cellule));
                }
                else {
                    gui.addGraphicalElement(new Rectangle(i*taille_cellule + 50, j*taille_cellule + 50, Color.BLACK, Color.BLACK, taille_cellule, taille_cellule));
                }
            }
        }
    }
}
