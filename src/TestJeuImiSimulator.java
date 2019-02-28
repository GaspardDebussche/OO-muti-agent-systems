import gui.GUISimulator;
import gui.*;
import java.util.*;
import java.awt.Color;

/**
  TestJeuImiSimulator permet l'execution de JeuImiSimulator
  */

public class TestJeuImiSimulator {
    public static void main(String [] args) {
        int width = 500;
        int height = 500;
        int n = 5;
        GUISimulator gui = new GUISimulator(width, height, Color.WHITE);
        JeuImiSimulator jeuimi = new JeuImiSimulator(130, 100, gui, n);

        jeuimi.getGui().setSimulable(jeuimi);

    }
}
