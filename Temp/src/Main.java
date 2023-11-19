import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>(Arrays.asList(1,2,3,4,5));

        MyIter<Integer> myIter = new MyIter<>(intList);
        while (myIter.hasNext()){
            Integer temp = myIter.next();
            if(temp == 3) myIter.remove();

            System.out.println(intList);
        }

    }
}