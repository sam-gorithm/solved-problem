import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 세 개의 자연수 입력받음
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        // 세 수의 곱
        int val = A * B * C;

        // 숫자 0부터 9의 등장 횟수를 저장할 배열
        int[] arr = new int[10];

        // 계산 결과를 자릿수별로 
        while (val != 0) {
            int num = val % 10;   // 마지막 자릿수
            arr[num]++;           // 해당 숫자 카운트 증가
            val /= 10;              // 다음 자릿수로 
        }

        // 0부터 9까지의 등장 횟수 
        for (int count : arr) {
            System.out.println(count);
        }
    }
}