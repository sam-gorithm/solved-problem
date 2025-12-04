import java.util.Scanner;

public class N1003_jeongun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int N = sc.nextInt();

            if (N == 0) {
                System.out.println("1 0");
                continue;
            }
            if (N == 1) {
                System.out.println("0 1");
                continue;
            }
            int count0[] = new int[N+1];
            int count1[] = new int[N+1];

            count0[0] = 1;
            count1[0] = 0;
            count0[1] = 0;
            count1[1] = 1;

            //fib(n) -> fib(n-1), fib(n-2) 호출
            for (int i = 2; i <= N; i++) {
                count0[i] = count0[i-1] + count0[i-2];
                count1[i] = count1[i-1] + count1[i-2];
            }
            System.out.println(count0[N] + " " + count1[N]);
        }
    }
}