package Main.Java.dubois.prog_conc;

import Main.Java.dubois.prog_conc.baignoire.Baignoire;
import Main.Java.dubois.prog_conc.robinet.Robinet;

public class Main {
    public static void main (String[] args) {
        Baignoire bath = new Baignoire(1500, 50, 1400);
        Robinet tap = new Robinet(100,bath);

        Thread threadBath = new Thread(bath);
        Thread threadTap = new Thread(tap);

        threadBath.start();
        threadTap.start();
    }
}
