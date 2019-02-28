import gui.*;
import java.util.*;
import java.awt.Color;

public class JeuVie {
    public static void main(String [] args) {
        int init = 5;
        int longueur = 6;
        int largeur = 6;
        int TAILLE = 200;
        GUISimulator gui = new GUISimulator(TAILLE, TAILLE, Color.WHITE);


        Grille g = new Grille(longueur, largeur, gui);
        //g.dessiner(gui);





    }
}
