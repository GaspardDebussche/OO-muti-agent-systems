import java.util.*;
import java.awt.*;
/**
  Module pour une balle afin d'effectuer les premiers tests basiques.
  */

class Ball {
    /**
      une position x,y et une direction dx,dy
      */
    private int x;
    private int y;
    private int dx;
    private int dy;

    public Ball(int x, int y){
        this.x = x;
        this.y = y;
        this.dx = 5;
        this.dy = 5;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getdX() {
        return this.dx;
    }

    public int getdY() {
        return this.dy;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setdX(int dx) {
        this.dx = dx;
    }

    public void setdY(int dy) {
        this.dy = dy;
    }

}
