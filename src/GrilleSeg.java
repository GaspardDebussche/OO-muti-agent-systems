import java.util.*;

/**
  Classe representant la grille sur laquelles les differentes
  Cellules vont interagir pour TestJeuSegSimulator
  */
public class GrilleSeg extends Grille {
    private int habitations_vacantes;
    private LinkedList<Cellule> case_vacante;


    public GrilleSeg(int height, int width, int m, int habitations_vacantes) {
        super(height, width, m);
        this.cellules = new Cellule[height][width];
        this.case_vacante = new LinkedList<Cellule>();
        this.habitations_vacantes = habitations_vacantes;
        Random r = new Random();
        int ir = r.nextInt(height);
        int jr = r.nextInt(width);
        for (int i = 0; i < height; i ++) {
            for (int j = 0; j < width; j ++) {
                // Initialisation :
                this.cellules[i][j] = new Cellule(super.getN(), true);
            }
        }
        for (int k = 0; k< this.habitations_vacantes; k++){
            // Ajout de case_vacante de façon aléatoire :
            while (this.cellules[ir][jr].getEtat_cour() == 0) {
                ir = r.nextInt(height);
                jr = r.nextInt(width);
            }
            this.cellules[ir][jr].setEtat_cour(0);
            this.cellules[ir][jr].setEtat_prec(0);
            this.case_vacante.add(cellules[ir][jr]);
        }
    }

    public void setHabitations_vacantes( int habitations_vacantes) {
        this.habitations_vacantes = habitations_vacantes;
    }

    public int getHabitations_vacantes() {
        return this.habitations_vacantes;
    }

    public void setCase_vacante( LinkedList<Cellule> case_vacante) {
        this.case_vacante = case_vacante;
    }

    public LinkedList<Cellule> getCase_vacante() {
        return this.case_vacante;
    }

}
