import gui.GUISimulator;
import gui.*;
import java.util.*;
import java.awt.Color;

/**
  TestJeuSegSimulator permet l'execution de JeuSegSimulator
  */

public class TestJeuSegSimulator {
    public static void main(String [] args) {
        int width = 500;
        int height = 500;
        int n = 2;
        int seuil = 2;
        int habitations_vacantes = 5000;
        GUISimulator gui = new GUISimulator(width, height, Color.WHITE);
        //gui.addGraphicalElement(new Rectangle(50, 50, Color.BLUE, Color.BLUE, 100, 100));
        JeuSegSimulator jeuseg = new JeuSegSimulator(150, 150, gui, n, seuil, habitations_vacantes);

        jeuseg.getGui().setSimulable(jeuseg);

    }
}
