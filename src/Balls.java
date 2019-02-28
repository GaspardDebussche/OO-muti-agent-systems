import java.util.*;
import java.awt.*;
import gui.*;
/**
  Module pour un ensemble de plusieurs balles implémenté avec
  */
class Balls {

    /**
      deux listes chaînées :
      une courante et une uniquement afin de stocker les positions initiales des balles
      afin d'implémenter la méthode reInit()
      */

    private  LinkedList<Ball> mes_balles;
    private LinkedList<Ball> mes_balles_init;


    public Balls(LinkedList<Ball> b) {

        this.mes_balles = new LinkedList<Ball>();
        this.mes_balles_init = new LinkedList<Ball>();
        for (Ball p : b) {
            this.mes_balles.add(p);
            Ball p1 = new Ball(p.getX(), p.getY());
            this.mes_balles_init.add(p1);
        }
    }

    public void translate(GUISimulator gui) {
        /**
          calcule les nouvelles positions des balles
          */
        int height = gui.getPanelHeight();
        int width = gui.getPanelWidth();

        for (Ball p : this.mes_balles) {
            if (p.getX() + p.getdX() >= height) {
                p.setX(2*height - p.getX() - p.getdX());
                p.setdX(-p.getdX());

            }
            if (p.getY() + p.getdY()>= width) {
                p.setY(2*width - p.getY() - p.getdY());
                p.setdY(-p.getdY());
            }

            if (p.getX() + p.getdX() <= 0) {
                p.setX(-p.getdX() - p.getX());
                p.setdX(-p.getdX());

            }
            if (p.getY() + p.getdY() <= 0) {
                p.setY(-p.getdY() - p.getY());
                p.setdY(-p.getdY());
            }

            else {
                p.setX(p.getX() + p.getdX());
                p.setY(p.getY() + p.getdY());
            }
        }
    }

    public void reInit() {
        /**
          repositionne toutes les balles à leur position initiale
          */
        int n = mes_balles.size();
        for (int i = 0; i < n; i++) {
            this.mes_balles.get(i).setX(this.mes_balles_init.get(i).getX());
            this.mes_balles.get(i).setY(this.mes_balles_init.get(i).getY());
        }
    }

    public String toString() {
        if (this.mes_balles == null) {
            System.out.println("Balles null");
        }
        String res = "";
        for (Ball p : this.mes_balles) {
            res = res + "(" + p.getX() + ", " + p.getY() + ") ";
        }
        return res;
    }

    public LinkedList<Ball> getMesballes() {
        return this.mes_balles;
    }

}
