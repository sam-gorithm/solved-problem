//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //투포인터 써라 ..
        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int x = sc.nextInt();

        Arrays.sort(arr); //오름차순 정렬 (양 끝에서 시작해야하니까 ..)

        int L = 0;
        int R = n-1;
        int count = 0;

        while(L < R) {
            if(arr[L] + arr[R] == x) {
                L++;
                R--;
                count++;
            } else if(arr[L] + arr[R] < x) {
                L++;
            } else {
                R--;
            }
        }
        System.out.println(count);
    }
}
