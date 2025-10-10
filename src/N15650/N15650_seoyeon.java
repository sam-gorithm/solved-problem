package N15650;

import java.io.*;
import java.util.*;

public class N15650_seoyeon {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];

        backTrack(0, 1);

    }

    private static void backTrack(int depth, int next) {
        if (depth == M) {
            for (int a : arr) {
                System.out.print(a + " ");
            }
            System.out.println();
            return;
        }

        // 재귀부
        for (int i = next; i <= N; i++) {
            // 전진
            // 2. 수 채우기
            arr[depth] = i;
            // 3. 다음 자리 채우기
            backTrack(depth + 1, i+1);
        }
    }
}

