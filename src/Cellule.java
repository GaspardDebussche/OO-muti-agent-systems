import java.util.Random;

public class Cellule {
    /**
      Class representant une case sur la grille, sous la forme d'une cellule
      Cette Cellule comprend un etat_prec et un etat_cour
      */
    private int etat_prec;
    private int etat_cour;

    public Cellule(int n, boolean seg) {
        Random r = new Random();
        int a;
        if (seg){
            // creation d'un etat aleatoire compris entre 1 et n (pour TestJeuSegSimulator) :
            a = r.nextInt(n) + 1;
        }
        else{
            // creation d'un etat aleatoire compris entre 0 et n (pour TestJeuVieSimulator et TestJeuImiSimulator) :
            a = r.nextInt(n);
        }
        this.etat_prec = a;
        this.etat_cour = a;
    }

    public void setEtat_cour(int etat_cour) {
        this.etat_cour = etat_cour;
    }

    public int getEtat_cour() {
        return this.etat_cour;
    }

    public void setEtat_prec(int etat_prec) {
        this.etat_prec = etat_prec;
    }

    public int getEtat_prec() {
        return this.etat_prec;
    }

}
