import java.util.*;

/**
  Class representant la grille sur laquelles les differentes Cellules vont interagir
  */
public class Grille {
    private int height;
    private int width;
    protected Cellule[][] cellules; //Tableau comportant toutes les cellules differentes
    private int n;

    public Grille(int height, int width, int m) {
        this.height = height;
        this.width = width;
        this.n = m;
        /* Initialisation */
        this.cellules = new Cellule[height][width];
        for (int i = 0; i < height; i ++) {
            for (int j = 0; j < width; j ++) {
                this.cellules[i][j] = new Cellule(n, false);
            }
        }
    }

    public void setCellules(Cellule[][] cellules) {
        this.cellules = cellules;
    }

    public Cellule[][] getCellules() {
        return this.cellules;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return this.height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getN() {
        return this.n;
    }

}
