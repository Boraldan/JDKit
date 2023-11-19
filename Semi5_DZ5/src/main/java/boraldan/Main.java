package boraldan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 Вилки лежат на столе между каждой парой ближайших философов.
 Каждый философ может либо есть, либо размышлять.
 Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
 Философ может взять только две вилки сразу, то есть обе вилки должны быть свободны

 Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза

 */
public class Main {
    public static void main(String[] args) {

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