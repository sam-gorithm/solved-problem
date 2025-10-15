package N6603;

import java.util.Scanner;

public class N6603_jeongun {
    static int K;
    static int[] S;
    static int[] pick;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        pick = new int[6];

        while (true) {
            K = sc.nextInt();

            if(K == 0) {
                break;
            }

            S = new int[K];

            for (int i = 0; i < K; i++) {
                S[i] = sc.nextInt();
            }

            dfs(0, 0);
            System.out.println();
        }
    }

    static void dfs(int num, int count) {
        if (count == 6) {
            for (int i = 0; i < 6; i++) {
                System.out.print(pick[i]);

                if (i < 5) System.out.print(' ');
            }

            System.out.println();
            return;
        }

        if (num == K) {
            return;
        }

        if (K-num < 6-count) {
            return;
        }
        pick[count] = S[num];
        dfs(num+1, count+1);
        dfs(num+1, count);
    }
}