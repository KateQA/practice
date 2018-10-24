package etc;

import java.util.ArrayList;
import java.util.List;

public class FiboClass {

    public static void main(String[] args) {
        int num1 = 0;
        int num2 = 1;
        int num3 = num1 + num2;
        int maxNumber = 150;

        List<Integer> result = new ArrayList<>();
        result.add(num1);
        //result.add(num2);
        result.add(num3);

        while (num3 < maxNumber) {
            System.out.println(num3);
            num1 = num2;
            num2 = num3;
            num3 = num1 + num2;
            result.add(num3);
        }

        System.out.println(result.toString());
    }
}
