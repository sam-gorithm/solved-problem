import java.util.Scanner;

public class q숫자의갯수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        //세 숫자의 곱 string으로 변환
        String num = A * B * C + "";

        //숫자의 갯수 카운트 및 저장
        int[] arr = new int[10];
        for (int i = 0; i < num.length(); i++) {
            int n = Integer.parseInt(num.charAt(i)+"");
            arr[n]++;
        }

        for (int i = 0; i < 10;i++){
            System.out.println(arr[i]);
        }
    }
}
