package boraldan;

import lombok.Data;

import java.util.*;

@Data
public class MontyHall {

    Random r = new Random();
    private Box player;
    private List<Box> boxes = new ArrayList<>(Arrays.asList(Box.CAR, Box.Beee, Box.Beee));
    private Map<Integer, Box> resalt;
    private int win;

    public Map<Integer, Box> gameOne() {
        win = 0;
        resalt =  new HashMap<>();
        for (int i = 1; i < 1001; i++) {
            int x = r.nextInt(3);
            resalt.put(i, boxes.get(x));
            if (boxes.get(x) == Box.CAR) win++;
        }
        System.out.println("Выиграно:  " + win);
        return resalt;
    }

    public  Map<Integer, Box> gameWithChang() {
        win = 0;
        resalt =  new HashMap<>();
        for (int i = 1; i < 1001; i++) {
            int beee = 0;
            int x = r.nextInt(3);
            for (int j = 0; j < 100; j++) {
                int y = r.nextInt(3);
                if (y != x && boxes.get(y) == Box.Beee) {
                    beee = y;
                    break;
                }
            }
            for (int j = 0; j < 100; j++) {
                int z = r.nextInt(3);
                if (z != x && z != beee) {
                    resalt.put(i, boxes.get(z));
                    if (boxes.get(z) == Box.CAR) win++;
                    break;
                }
            }
        }
        System.out.println("Выиграно меняя коробку:  " + win);
        return resalt;
    }
}