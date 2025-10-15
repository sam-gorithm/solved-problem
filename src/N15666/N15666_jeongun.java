package N15666;

import java.util.Arrays;
import java.util.Scanner;

public class N15666_jeongun {
    static int N, M;
    static int[] arr, pick;

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
        dfs(0, 0);
    }

    static void dfs(int start, int num) {
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
        for (int i = start; i < N; i++) {
            if (arr[i] == prev) { //중복 XX
                continue;
            }
            pick[num] = arr[i];
            dfs(i, num+1); //비내림 -> 같은 값 가능
            prev = arr[i];
        }
    }
}
