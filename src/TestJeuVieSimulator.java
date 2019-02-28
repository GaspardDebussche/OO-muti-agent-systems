import gui.GUISimulator;
import gui.*;
import java.util.*;
import java.awt.Color;

/**
  TestJeuVieSimulator permet l'execution de JeuVieSimulator 
  */

public class TestJeuVieSimulator {
    public static void main(String [] args) {
        int width = 1000;
        int height = 1000;
        int n = width*height;
        GUISimulator gui = new GUISimulator(width, height, Color.WHITE);
        JeuVieSimulator jeuvie = new JeuVieSimulator(130, 100, gui, 2);

        jeuvie.getGui().setSimulable(jeuvie);

    }
}
