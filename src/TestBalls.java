import java.util.*;
import java.awt.*;

class TestBalls {
    public static void main(String[] args) {
        LinkedList<Ball> l = new LinkedList<Ball>();
        Ball p1 = new Ball(2, 3);
        Ball p2 = new Ball(1, 9);
        Ball p3 = new Ball(4, 4);
        l.add(p1); l.add(p2); l.add(p3);
        Balls balles = new Balls(l);
        System.out.println(balles.toString());
        balles.translate(10, 10);
        System.out.println(balles.toString());
        balles.reInit();
        System.out.println(balles.toString());
    }
}
