package N15663;

import java.util.Arrays;
import java.util.Scanner;

public class N15663_jeongun {
    static int N, M;
    static int[] arr, pick;
    static boolean[] check;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        pick = new int[M];
        check = new boolean[N];
        dfs(0);
    }

    static void dfs(int num) {
        if (num == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(pick[i]);
                if (i < M - 1) {
                    System.out.print(' ');
                }
            }
            System.out.println();
            return;
        }
        int prev = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            if (check[i] || arr[i] == prev) { //중복 XX
                continue;
            }

            check[i] = true;
            pick[num] = arr[i];
            dfs(num+1);
            check[i] = false;
            prev = arr[i];
        }
    }
}