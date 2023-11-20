package boraldan;

import java.util.ArrayList;
import java.util.List;

public class FoodCord extends Thread {
    @Override
    public void run() {
        List<Fork> forks = new ArrayList<>();
        forks.add(new Fork());
        forks.add(new Fork());
        forks.add(new Fork());
        forks.add(new Fork());
        forks.add(new Fork());

        Mudra mudra1 = new Mudra(forks, 4, 0, 1);
        Mudra mudra2 = new Mudra(forks, 0, 1, 2);
        Mudra mudra3 = new Mudra(forks, 1, 2, 3);
        Mudra mudra4 = new Mudra(forks, 2, 3, 4);
        Mudra mudra5 = new Mudra(forks, 3, 4, 5);

        mudra1.setNextMudra(mudra2);
        mudra2.setNextMudra(mudra3);
        mudra3.setNextMudra(mudra4);
        mudra4.setNextMudra(mudra5);
        mudra5.setNextMudra(mudra1);

        mudra1.start();
    }
}
