
public abstract class Event {
    /**
      Classe Event du sujet
      */
    private long date;

    public Event(long date) {
        this.date = date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getDate() {
        return this.date;
    }

    public void execute() {
        System.out.println("date : " + this.date);
    }

    public void executeInit() {
        System.out.println("init");
    }

}
