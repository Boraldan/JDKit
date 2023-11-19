package boraldan;

import java.util.List;

public class Mudra extends Thread {

    private int numMudra;
    private List<Fork> forks;
    private Mudra nextMudra;

    private int f1, f2;


    public Mudra(List<Fork> forks, int f1, int f2, int numMudra) {
        this.numMudra = numMudra;
        this.forks = forks;
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            synchronized (forks.get(f1)) {
                synchronized (forks.get(f2)) {
                    System.out.printf("Mудрец %d поел  -  %d\n", numMudra, i + 1);
                    try {
                        Mudra.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }

            if (numMudra == 5 && i == 0) {
                nextMudra.wakeupMudra();
            } else if (i == 0) {
                nextMudra.start();
            } else {
                nextMudra.wakeupMudra();
            }

            System.out.printf("Mудрец %d заснул\n", numMudra);
            synchronized (this){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.printf("Mудрец %d закончил\n", numMudra);
        nextMudra.wakeupMudra();
    }

    public  void wakeupMudra() {
        synchronized (this){
            notify();
        }
    }

    public void setNextMudra(Mudra nextMudra) {
        this.nextMudra = nextMudra;
    }
}
