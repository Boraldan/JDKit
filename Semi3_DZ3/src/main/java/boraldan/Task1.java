package boraldan;


public class Task1 {
    public static void main(String[] args) {

        double a = 2;
        double b = 0.001;
        int i = 0;
        float f = 0.001f;

        try {
            System.out.println(subtract(a, i));
        } catch (NullPointerException e) {
            System.out.println("Делить на ноль нельзя");
        }
    }

    public static double sum(Number num1, Number num2) {
        return num1.doubleValue() + num2.doubleValue();
    }

    public static double multiply(Number num1, Number num2) {
        return num1.doubleValue() * num2.doubleValue();
    }

    public static double divide(Number num1, Number num2) {
        return num1.doubleValue() - num2.doubleValue();
    }

    public static double subtract(Number num1, Number num2) throws NullPointerException {
        if (num2.equals(0)) throw new NullPointerException();
        return num1.doubleValue() / num2.doubleValue();
    }

}
