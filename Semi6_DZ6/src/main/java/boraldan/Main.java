package boraldan;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MontyHall montyHall = new MontyHall();

        Map<Integer, Box> res1 = montyHall.gameOne();
        Map<Integer, Box> res2=  montyHall.gameWithChang();

    }
}