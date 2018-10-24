package etc;

public class ForeachExample {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4, 5 };
        int sum = 0;

        for (int i = 0; i < 5; i++) { //условие для продвижения, 5 в этьом слуучаем это последнее значчение последнего элемента массива целых чисел
            sum += nums[i];
            System.out.println(sum);
        }

        int[] numbs = {1, 2, 3, 4, 5 };
        int summ = 0;

        for(int i : numbs){ //эта запись эквивалента тому, то в записи выше, но это foreach
            summ += i;      //при прохождении цикла переменной i автоматом присваивается значение, которое равно знаению след. эл. массива numbs (для каждого i присвоить значение из numbs)
            System.out.println(summ); //при таком способе исклюается возможность ошибок выхода за пределы массива
        }

        int[] numbers = {1, 2, 3, 4, 5};
        int summm = 0;

        for(int i : numbers){
            summm += i;
            if (i == 3) break;// останавливает цикл, если значение равно 3
        }

        int[] number = {3, 1, 6, 4, 9, 5, 8, 2};
        int val = 5;
        boolean found = false;

        for (int x : number){
            if (x == val){
                found = true;
                break;
            }
        }

        if (found){
            System.out.println("значение найдено!");
        }
    }
}
