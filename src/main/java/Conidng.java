import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.List;

public class Conidng {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("값을 입력하세요 : ");

        int n = Integer.parseInt(scanner.nextLine());

        System.out.println("숫자 리스트를 공백으로 구분하여 입력하세요.: ");
        String[] inputNumbers = scanner.nextLine().split(" ");
        List<Integer> mylist = new ArrayList<>();

        for(String number : inputNumbers){
            mylist.add(Integer.parseInt(number));
        }

        int mymax = Collections.max(mylist);

        int sum = 0;
        for(int num : mylist){
            sum += num;
        }


        double result = (sum  * 100.0 / mymax / n);
        System.out.println(result);

        scanner.close();
    }
}
