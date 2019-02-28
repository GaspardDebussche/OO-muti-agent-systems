import gui.*;
import java.util.*;
import java.awt.Color;

public class EventManager {
    /*
       Classe EventManager du sujet
       */
    private long currentDate;
    private LinkedList<Event> events;

    public EventManager() {
        events = new LinkedList<Event>();
        this.currentDate = 0;
    }

    public long getCurrentDate() {
        return this.currentDate;
    }

    public void setCurrentDate(long currentDate) {
        this.currentDate = currentDate;
    }

    public void addEvent(Event e) {
        this.events.add(e);
    }

    public void next() {
        /**
          La méthode enregistre le nombre n d'événements inital de la liste d'événements
          et execute ce nombre d'événements en les supprimant de la liste.
          */
        int n = this.events.size();
        int compt = 0;
        while (compt < n) {
            Event e = this.events.removeFirst();
            if (e.getDate() == this.currentDate) {
                e.execute();
                compt ++;
            }
        }
        this.currentDate = this.currentDate + 1;
    }

    public boolean isFinished() {
        for (Event e : this.events) {
            if (e.getDate() >= this.currentDate) {
                return false;
            }
        }
        return true;
    }


    public void restart() {

        int n = this.events.size();
        int compt = 0;
        while (compt < n) {
            Event e = this.events.removeFirst();
            if (e.getDate() == this.currentDate) {
                e.executeInit();
                compt ++;
            }
        }
        this.currentDate = 0;
    }

}
