package boraldan;


import java.util.Arrays;

public class Task2 {
    public static void main(String[] args) {

        int [] A1 = {1,2,3,4,5};
        int [] A2 = {1,2,3,4,5};

        Integer[] A11 = Arrays.stream( A1 ).boxed().toArray( Integer[]::new );
        Integer[] A22 = Arrays.stream( A2 ).boxed().toArray( Integer[]::new );

        Task1[] T0 = {new Task1()};
        Task2[] T1 = {new Task2(), new Task2()};
        Task2[] T2 = {new Task2(), new Task2()};

        System.out.println(compareArrays(T1, T2));
        System.out.println(compareArrays2(T0, T2));
        System.out.println(compareArrays(A11, A22));

    }
    public static <Ta, Tb> boolean compareArrays(Ta[] arr1, Tb[] arr2) {
        if( !arr1.getClass().equals(arr2.getClass())) return false;
        if (arr1.length != arr2.length) return false;
        return true;
    }

    public static boolean compareArrays2(Object[] arr1, Object [] arr2) {
        if( !arr1.getClass().equals(arr2.getClass())) return false;
        if (arr1.length != arr2.length) return false;
        return true;
    }

}
