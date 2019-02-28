import java.util.*;
import java.lang.*;

/**
  Classe de vecteurs avec constructeurs permettant
  de créer des vecteurs aléatoires
  */
class Vector {
    private double x;
    private double y;



    double getX() {
        return this.x;
    }
    void setX(double x) {
        this.x = x;
    }


    double getY() {
        return this.y;
    }
    void setY(double y) {
        this.y = y;
    }


    public Vector() {
        Random r = new Random();
        this.x = r.nextInt(TestBoidsSimulator.HEIGHT - 1) + 1;
        this.y = r.nextInt(TestBoidsSimulator.WIDTH - 1) + 1;

    }

    public Vector(int n){
        /**
          L'entier entré est arbitraire, il permet juste de
          différencier ce constucteur du précédant.
          Ici, on accepte des nombres négatifs (pour la direction)
          */
        Random r = new Random();
        this.x = r.nextInt(500) - 252 ;
        this.y = r.nextInt(500) - 252;
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public void add(Vector vector) {
        this.x += vector.x;
        this.y += vector.y;
    }

    public double norme() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public Vector normer() {
        this.x /= this.norme();
        this.y /= this.norme();
        return this;
    }

    @Override
    public String toString() {
        String res = "";
        res += "(" + this.x + "," + this.y + ")";
        return res;
    }
}
