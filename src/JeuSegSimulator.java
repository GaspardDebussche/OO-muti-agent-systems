import gui.*;
import java.lang.Math;
import java.awt.Color;
import java.util.*;


public class JeuSegSimulator implements Simulable {
    /**
      Classe rendant compte du Jeu de Schelling
      */
    private GrilleSeg g;
    private GUISimulator gui;
    private int seuil;


    public JeuSegSimulator(int height, int width, GUISimulator ig, int m, int seuil, int habitations_vacantes) {
        // Initialisation :
        int taille_cellule = 8;
        this.gui = ig;
        this.g = new GrilleSeg(height, width, m, habitations_vacantes);
        this.seuil = seuil;
        restart();
    }

    public void setG(GrilleSeg g) {
        this.g = g;
    }

    public GrilleSeg getG() {
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
          Fonction rendant compte de la façon dont est modifié une habitation
          */
        int taille_cellule = 8;
        int n = g.getN();
        gui.reset();
        for (int i = 0; i < g.getHeight(); i++) {
            for (int j = 0; j < g.getWidth(); j++) {
                // On realise des tests pour prendre en compte les differents voisins pertinents
                int sum = 0;
                int etat_cour_cellule;
                etat_cour_cellule = g.getCellules()[Math.floorMod(i, g.getHeight())][Math.floorMod(j, g.getWidth())].getEtat_cour();
                if ((g.getCellules()[Math.floorMod(i - 1, g.getHeight())][Math.floorMod(j - 1, g.getWidth())].getEtat_cour() != etat_cour_cellule) && (g.getCellules()[Math.floorMod(i - 1, g.getHeight())][Math.floorMod(j - 1, g.getWidth())].getEtat_cour() != 0)) {
                    sum += 1;
                }
                if ((g.getCellules()[Math.floorMod(i, g.getHeight())][Math.floorMod(j - 1, g.getWidth())].getEtat_cour() != etat_cour_cellule) && (g.getCellules()[Math.floorMod(i, g.getHeight())][Math.floorMod(j - 1, g.getWidth())].getEtat_cour() != 0)) {
                    sum += 1;
                }
                if ((g.getCellules()[Math.floorMod(i + 1, g.getHeight())][Math.floorMod(j - 1, g.getWidth())].getEtat_cour() != etat_cour_cellule) && (g.getCellules()[Math.floorMod(i + 1, g.getHeight())][Math.floorMod(j - 1, g.getWidth())].getEtat_cour() != 0)) {
                    sum += 1;
                }
                if ((g.getCellules()[Math.floorMod(i - 1, g.getHeight())][Math.floorMod(j + 1, g.getWidth())].getEtat_cour() != etat_cour_cellule) && (g.getCellules()[Math.floorMod(i - 1, g.getHeight())][Math.floorMod(j + 1, g.getWidth())].getEtat_cour() != 0)) {
                    sum += 1;
                }
                if ((g.getCellules()[Math.floorMod(i, g.getHeight())][Math.floorMod(j + 1, g.getWidth())].getEtat_cour() != etat_cour_cellule) && (g.getCellules()[Math.floorMod(i, g.getHeight())][Math.floorMod(j + 1, g.getWidth())].getEtat_cour() != 0)) {
                    sum += 1;
                }
                if ((g.getCellules()[Math.floorMod(i + 1, g.getHeight())][Math.floorMod(j + 1, g.getWidth())].getEtat_cour() != etat_cour_cellule) && (g.getCellules()[Math.floorMod(i + 1, g.getHeight())][Math.floorMod(j + 1, g.getWidth())].getEtat_cour() != 0)) {
                    sum += 1;
                }
                if ((g.getCellules()[Math.floorMod(i - 1, g.getHeight())][Math.floorMod(j, g.getWidth())].getEtat_cour() != etat_cour_cellule) && (g.getCellules()[Math.floorMod(i - 1, g.getHeight())][Math.floorMod(j, g.getWidth())].getEtat_cour() != 0)) {
                    sum += 1;
                }
                if ((g.getCellules()[Math.floorMod(i + 1, g.getHeight())][Math.floorMod(j, g.getWidth())].getEtat_cour() != etat_cour_cellule) && (g.getCellules()[Math.floorMod(i + 1, g.getHeight())][Math.floorMod(j, g.getWidth())].getEtat_cour() != 0)) {
                    sum += 1;
                }
                /*Suivant l'etat courant de la cellule et de son nombre de voisins pertinents,
                  on donne à l'habitation une position d'une case vacante */
                if ((sum >= this.seuil) && (g.getCellules()[i][j].getEtat_cour() != 0)) {
                    g.getCellules()[i][j].setEtat_prec(0);
                    Random r = new Random();
                    int a = r.nextInt(g.getCase_vacante().size());
                    g.getCase_vacante().get(a).setEtat_prec(g.getCellules()[i][j].getEtat_cour());
                    g.getCase_vacante().remove(a); //On enlève l'ancienne case vacante qui ne l'est plus maintenant
                    g.getCase_vacante().add(g.getCellules()[i][j]); //On ajoute la nouvelle case vacante (anciennement l'habitation en i,j) dans la liste des cases vacantes
                }


            }
        }

        for (int i = 0; i < g.getHeight(); i++) {
            for (int j = 0; j < g.getWidth(); j++) {
                // On met à jour etat_cour
                g.getCellules()[i][j].setEtat_cour(g.getCellules()[i][j].getEtat_prec());
                gui.addGraphicalElement(new Rectangle(i*taille_cellule + 50, j*taille_cellule + 50, new Color((255/n)*g.getCellules()[i][j].getEtat_cour(),(255/n)*g.getCellules()[i][j].getEtat_cour(), (255/n)*g.getCellules()[i][j].getEtat_cour()), new Color((255/n)*g.getCellules()[i][j].getEtat_cour(),(255/n)*g.getCellules()[i][j].getEtat_cour(), (255/n)*g.getCellules()[i][j].getEtat_cour()), taille_cellule, taille_cellule));

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
        g = new GrilleSeg(g.getHeight(), g.getWidth(), g.getN(), g.getHabitations_vacantes());
        for (int i = 0; i < g.getHeight(); i++) {
            for (int j = 0; j < g.getWidth(); j++) {
                int etat_cour_cell = g.getCellules()[i][j].getEtat_cour();
                int n = g.getN();
                gui.addGraphicalElement(new Rectangle(i*taille_cellule + 50, j*taille_cellule + 50, new Color((255/n)*etat_cour_cell,(255/n)*etat_cour_cell, (255/n)*etat_cour_cell), new Color((255/n)*etat_cour_cell,(255/n)*etat_cour_cell, (255/n)*etat_cour_cell), taille_cellule, taille_cellule));

            }
        }
    }
}
