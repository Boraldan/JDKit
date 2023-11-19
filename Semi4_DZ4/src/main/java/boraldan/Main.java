package boraldan;

public class Main {
    public static void main(String[] args) {
        CameHere base = new CameHere();

        System.out.println(base.findPasha("Tom"));

        base.addPasha();
        System.out.println(base.getWorkerList());

        System.out.println(base.findId(2));
    }
}
